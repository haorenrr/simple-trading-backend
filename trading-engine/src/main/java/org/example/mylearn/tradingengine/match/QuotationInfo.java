package org.example.mylearn.tradingengine.match;

import org.example.mylearn.tradingengine.order.TradeType;
import java.math.BigDecimal;

public class QuotationInfo {
    public BigDecimal price; //报价
    public BigDecimal volume; // 数量
    public TradeType tradeType; // BUY, or SELL

    public QuotationInfo() {
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
}
