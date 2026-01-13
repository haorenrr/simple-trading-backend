package org.example.mylearn.tradingengine.order;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderEntity {
    Integer id;
    String uid;
    Integer seqId;

    BigDecimal price;
    TradeType tradeType;

    BigDecimal amount;
    BigDecimal finishedAmount;//已经成交的数量，理论上使用子订单更合理？
    BigDecimal processingAmount;

    OrderStatus status;
    String messge;

    Timestamp createdAt;
    Timestamp updatedAt;


    public OrderEntity() {
        this.id = -1;
        this.uid = "-1";
        this.seqId = -1;
        this.price = BigDecimal.ZERO;
        this.tradeType = null;
        this.amount = BigDecimal.ZERO;
        this.finishedAmount = BigDecimal.ZERO;
        this.processingAmount = BigDecimal.ZERO;
        this.status = OrderStatus.INIT;
        this.messge = "";
        this.createdAt = null;
        this.updatedAt = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public BigDecimal getProcessingAmount() {
        return processingAmount;
    }

    public void setProcessingAmount(BigDecimal processingAmount) {
        this.processingAmount = processingAmount;
    }
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFinishedAmount() {
        return finishedAmount;
    }

    public void setFinishedAmount(BigDecimal finishedAmount) {
        this.finishedAmount = finishedAmount;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
