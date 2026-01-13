package org.example.mylearn.tradingengine.match;

import org.example.mylearn.tradingengine.order.OrderEntity;
import org.example.mylearn.tradingengine.order.TradeType;

import java.math.BigDecimal;
import java.util.List;

public class QuotationItem {
    public BigDecimal price;
    public BigDecimal volume;
    public TradeType tradeType;
    public List<OrderEntity> orders;

    public QuotationItem() {
    }

    public QuotationItem(BigDecimal price, BigDecimal volume, TradeType tradeType, List<OrderEntity> orders) {
        this.price = price;
        this.volume = volume;
        this.tradeType = tradeType;
        this.orders = orders;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
