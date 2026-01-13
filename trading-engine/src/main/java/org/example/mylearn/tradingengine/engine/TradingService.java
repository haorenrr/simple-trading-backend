package org.example.mylearn.tradingengine.engine;

import org.example.mylearn.common.Result;
import org.example.mylearn.tradingengine.match.QuotationInfo;
import org.example.mylearn.tradingengine.match.RealTimeTick;
import org.example.mylearn.tradingengine.match.TradingDetail;
import org.example.mylearn.tradingengine.order.OrderEntity;
import org.example.mylearn.tradingengine.order.TradeType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface TradingService {

    Result<OrderEntity> buy(String uid, BigDecimal price, BigDecimal amont);
    Result<OrderEntity> sell(String uid, BigDecimal price, BigDecimal amont);
    Result<OrderEntity> cancel(String uid, Integer orderId);

    Result<OrderEntity> orderStatus(String uid, Integer orderId);
    Result<Map<TradeType, TreeSet<QuotationInfo>>> getQuotations();

    Result<List<TradingDetail>> getTradingDetails(int lastNumItems);
    Result<List<RealTimeTick>> getRealTimeTicks(Timestamp start, int numItems);
}
