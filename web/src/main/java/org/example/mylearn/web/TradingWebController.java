package org.example.mylearn.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/web")
class TradingWebController {

    @GetMapping("/")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/assets")
    public String getAssets(
            @RequestParam(name = "uid") String userId
            ) {
        return "{userId:" + userId +"}";
    }

    @GetMapping(value = "/orderBook")
    public String getOrderBook(){

        //TODO
        return "{d:orderBook}";
    }

    @GetMapping("/creatOrder")
    public String creatOrder(
            @RequestParam(name = "uid")  String userId,
            @RequestParam(name = "direction")  Integer direction,
            @RequestParam(name = "price")  BigDecimal price,
            @RequestParam(name = "quantity")  Integer quantity
    ){
        // TODO1: orderid = orderservice.creatOder()
        // TODO2: return orderid
        return "{d:orderBook,d:price,d:quantity: " + userId + "," + direction + "," + price + "," + quantity + "}";
    }

    @GetMapping("/orderStatus")
    public String getOrderStatus(
            @RequestParam(name = "uid") String uid,
            @RequestParam(name = "order_id") String orderId){

        return "{d:uid,d:orderId:" + uid + "," +orderId + "}";
    }

    @GetMapping("/getOrder")
    public String getOrders(
            @RequestParam(name = "uid") String uid
    ){
        return "{d:orders:" + uid +"," +"}";

    }
}
