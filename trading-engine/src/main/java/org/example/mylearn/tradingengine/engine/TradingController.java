package org.example.mylearn.tradingengine.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.mylearn.common.Result;
import org.example.mylearn.tradingengine.match.QuotationInfo;
import org.example.mylearn.tradingengine.order.OrderEntity;
import org.example.mylearn.tradingengine.order.TradeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@Tag(name = "交易管理", description = "有关交易的接口，如BUY、SELL") // For Swagger UI (SpringDOC) 展示信息
@RestController
@RequestMapping(value = "/trade", produces = MediaType.APPLICATION_JSON_VALUE)
class TradingController {
    @Autowired
    TradingService tradingService;
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Operation(summary = "购买股票", description = "购买股票的接口") // For Swagger UI(SpringDOC)
    @GetMapping(value = "/buy")
    public Result<OrderEntity> buy(
            @RequestParam String uid,
            @RequestParam String price,
            @RequestParam String amont) {

        return tradingService.buy(uid, new BigDecimal(price), new BigDecimal(amont));
    }

    @GetMapping("/sell")
    public Result<OrderEntity> sell(@RequestParam String uid,
                                    @RequestParam String price,
                                    @RequestParam String amont) {
        return tradingService.sell(uid, new BigDecimal(price), new BigDecimal(amont));
    }

    @GetMapping("/cancel")
    public Result<OrderEntity> cancel(
            @RequestParam String uid,
            @RequestParam Integer orderId ) {
        return tradingService.cancel(uid, orderId);
    }

    @GetMapping("/orderStatus")
    public Result<OrderEntity> getOrderStatus(
            @RequestParam String uid,
            @RequestParam Integer orderId
    ) {
        return tradingService.orderStatus(uid, orderId);
    }

    @GetMapping("/listQuotation")
    public Result<Map<TradeType, TreeSet<QuotationInfo>>> listQuotation() {
        return tradingService.getQuotations();
    }

    @GetMapping("/testBuy")
    public ArrayList<OrderEntity> testBuy(@RequestParam Integer num) {
        Random random = new Random();
        var uids = Arrays.asList("1", "2", "3", "4");
        var prices = Arrays.asList("1", "5", "10", "15");
        var amonts = Arrays.asList("1", "2", "3");
        ArrayList<OrderEntity> orders = new ArrayList<>();
        while (num > 0) {
            Result<OrderEntity> result = buy(uids.get(random.nextInt(uids.size())), prices.get(random.nextInt(prices.size())), amonts.get(random.nextInt(amonts.size())));
            orders.add(result.getData());
            System.out.println("submit BUY order: " + GSON.toJson(result));
            num -= 1;
        }
        return orders;
    }
    @GetMapping("/testSell")
    public ArrayList<Result<OrderEntity>> testSell(@RequestParam Integer num) {
        Random random = new Random();
        var uids = Arrays.asList("1", "2", "3", "4");
        var prices = Arrays.asList("2", "3", "10", "12");
        var amonts = Arrays.asList("1", "2", "3");
        ArrayList<Result<OrderEntity>> orders = new ArrayList<>();
        while (num > 0) {
            Result<OrderEntity> result = sell(uids.get(random.nextInt(uids.size())), prices.get(random.nextInt(prices.size())), amonts.get(random.nextInt(amonts.size())));
            orders.add(result);
            System.out.println("submit SELL order: " + GSON.toJson(result));
            num -= 1;
        }
        return orders;
    }
}
