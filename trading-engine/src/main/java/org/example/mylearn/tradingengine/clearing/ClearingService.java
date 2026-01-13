package org.example.mylearn.tradingengine.clearing;

import org.example.mylearn.common.Result;
import org.example.mylearn.tradingengine.order.OrderEntity;

import java.util.List;

public interface ClearingService {

    Result<OrderEntity> prepareTrading(OrderEntity orderEntity);

    Result<Void> finishTrading(OrderEntity orderEntity, List<OrderEntity> matchedorders);

}
