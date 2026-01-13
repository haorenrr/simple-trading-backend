package org.example.mylearn.tradingengine.match;

import org.example.mylearn.tradingengine.order.OrderEntity;

public class QuotationReq {

    public ReqType reqType;
    public OrderEntity order;

    public QuotationReq() {
    }

    public void setAdd(){
        setReqType(ReqType.ADD);
    }
    public void setRmove(){
        setReqType(ReqType.REMOVE);
    }

    private ReqType getReqType() {
        return reqType;
    }

    private void setReqType(ReqType reqType) {
        this.reqType = reqType;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public static enum ReqType{
        ADD,
        REMOVE
    }
}
