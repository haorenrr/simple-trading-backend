package org.example.mylearn.tradingengine.clearing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.mylearn.common.ErrorCode;
import org.example.mylearn.common.Result;
import org.example.mylearn.tradingengine.asset.AssetService;
import org.example.mylearn.tradingengine.asset.AssetTransferType;
import org.example.mylearn.tradingengine.asset.AssetType;
import org.example.mylearn.tradingengine.order.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ClearingServiceImpl implements ClearingService {
    @Autowired
    AssetService assetService;
    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Result<OrderEntity> prepareTrading(OrderEntity orderEntity) {
        // 检查并并冻结对应账户的USD、或APPL
        String uid = orderEntity.getUid();
        switch (orderEntity.getTradeType()){
            case BUY -> {
                var result = assetService.tryFreeze(uid, AssetType.USD, orderEntity.getAmount().multiply(orderEntity.getPrice()) );
                return new Result<>(result.isSuccess(), orderEntity, result.getErrorCode(), result.getMessage());
            }
            case SELL ->{
                var result = assetService.tryFreeze(uid, AssetType.APPL, orderEntity.getAmount());
                return new Result<>(result.isSuccess(), orderEntity, result.getErrorCode(), result.getMessage());
            }
            default -> {
                var msg = String.format("invald TradeType: %s ?!", orderEntity.getTradeType());
                throw new IllegalStateException(msg);
            }
        }
    }

    @Override
    public Result<Void> finishTrading(OrderEntity orderFrom, List<OrderEntity> matchedOrders) {

        matchedOrders.forEach(order -> Assert.notNull(order, "order is null in matchedOrders"));

        BigDecimal tradingvalue = matchedOrders.stream().filter(Objects::nonNull).map(OrderEntity::getProcessingAmount)
                .filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        if(tradingvalue.compareTo(orderFrom.getProcessingAmount()) != 0){
            String msg = "Trading value not consistent! 'from' side {%s}, 'to' side {%s}. Detailed info: orderFrom=%s, matchedOrders=%s"
                    .formatted(orderFrom.getProcessingAmount(), tradingvalue, GSON.toJson(orderFrom), GSON.toJson(matchedOrders));
            return Result.fail(null, ErrorCode.INTERNAL_ERROR, msg);
        }

        matchedOrders.forEach(orderTo -> {
            switch (orderFrom.getTradeType()) {
                case BUY -> {
                    //按照卖家的价格成交
                    assetService.transferBetweenUsers(AssetTransferType.FROZEN_TO_AVAILABLE, orderFrom.getUid(), orderTo.getUid(),
                            AssetType.USD, orderTo.getProcessingAmount().multiply(orderTo.getPrice()));
                    assetService.transferBetweenUsers(AssetTransferType.FROZEN_TO_AVAILABLE, orderTo.getUid(), orderFrom.getUid(),
                            AssetType.APPL, orderTo.getProcessingAmount());
                    //因为以卖家价格成交，因此买单冻结的资金可能没用完，对于买单，挂单价和成交价之间的差价解冻
                    Assert.isTrue(orderFrom.getPrice().compareTo(orderTo.getPrice()) >= 0,
                            "BUY price must be greater than sell price. buyOrder=%s, sellOrder=%s".formatted(GSON.toJson(orderFrom), GSON.toJson(orderTo)));
                    assetService.unfreeze(orderFrom.getUid(), AssetType.USD,
                            orderTo.getProcessingAmount().multiply(orderFrom.getPrice().subtract(orderTo.getPrice())));
                }
                case SELL -> {
                    assetService.transferBetweenUsers(AssetTransferType.FROZEN_TO_AVAILABLE, orderFrom.getUid(), orderTo.getUid(),
                            AssetType.APPL, orderTo.getProcessingAmount());
                    assetService.transferBetweenUsers(AssetTransferType.FROZEN_TO_AVAILABLE, orderTo.getUid(), orderFrom.getUid(),
                            AssetType.USD, orderTo.getProcessingAmount().multiply(orderFrom.getPrice()));
                    // 按照卖单价成交，买单可能还有额外的冻结，解冻
                    Assert.isTrue(orderTo.getPrice().compareTo(orderFrom.getPrice()) >= 0,
                            "BUY price must be greater than SELL price. buyOrder=%s, sellOrder=%s".formatted(GSON.toJson(orderTo), GSON.toJson(orderFrom)));
                    assetService.unfreeze(orderTo.getUid(), AssetType.USD,
                            orderTo.getProcessingAmount().multiply(orderTo.getPrice().subtract(orderFrom.getPrice())));
                }
                default -> {
                    var msg = "invald TradeType: %s, order=%s".formatted(orderFrom.getTradeType(), GSON.toJson(orderFrom));
                    throw new IllegalStateException(msg);
                }
            }
        });
        return Result.ok(null);
    }
}
