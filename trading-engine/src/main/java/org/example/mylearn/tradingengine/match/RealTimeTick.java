package org.example.mylearn.tradingengine.match;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RealTimeTick {
    Integer id;
    BigDecimal price;
    BigDecimal amount;
    Timestamp time;

    public RealTimeTick() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
