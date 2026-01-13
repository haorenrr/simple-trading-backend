package org.example.mylearn.tradingengine.match;

import org.example.mylearn.common.Result;
import org.example.mylearn.tradingengine.order.OrderEntity;
import org.example.mylearn.tradingengine.order.TradeType;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public interface MatcherService {

    // Submit order to match engine
    public Result<OrderEntity> submitOrder(OrderEntity order);

    public Result<OrderEntity> cancelOrder(OrderEntity order);

    /**
     * Gte last N tradingDetails ordered by trading time
     *  lastNumItems: >0: number of items; -1: all items
    */
    public Result<List<TradingDetail>> getTradingDetails(int lastNumItems);

    // 获取实时报价信息
    public HashMap<TradeType, TreeSet<QuotationInfo>> getQuotationInfo();

    public Result<List<RealTimeTick>> getRealTimeTicks(Timestamp start, int numTicks);

}
