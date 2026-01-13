package org.example.mylearn.tradingengine.match;

import org.example.mylearn.tradingengine.order.OrderStatus;
import org.example.mylearn.tradingengine.order.TradeType;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TradingDetail {
    Integer id;
    Integer fromOrderId;
    Integer toOrderId;

    BigDecimal price;
    BigDecimal amount;

    TradeType tradeType;
    OrderStatus orderStatus;
    String msg;

    Timestamp createdAt;
    Timestamp updatedAt;

    public TradingDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromOrderId() {
        return fromOrderId;
    }

    public void setFromOrderId(Integer fromOrderId) {
        this.fromOrderId = fromOrderId;
    }

    public Integer getToOrderId() {
        return toOrderId;
    }

    public void setToOrderId(Integer toOrderId) {
        this.toOrderId = toOrderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
