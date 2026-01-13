package org.example.mylearn.tradingengine.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.mylearn.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/order")
class OrderController {

    @Autowired
    OrderService orderService;
    Logger logger = LoggerFactory.getLogger(OrderController.class);
    static final Gson GSON =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();

    @GetMapping("/list")
    public Result<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.getAllOrder();
        logger.debug("getAllOrders(): {}", GSON.toJson(orders));
        return Result.ok(orders);
    }

    @GetMapping("/get")
    public Result<OrderEntity> getOrderById(@RequestParam Integer id) {
        Assert.notNull(id, "id is null");
        return orderService.getOrderById(id);
    }

    @GetMapping("/remove")
    public Result<OrderEntity> removeOrderById(@RequestParam Integer id) {
        OrderEntity order = orderService.remove(id);
        logger.debug("removeOrderById(): id={}, removed order = {}", id, GSON.toJson(order));
        return Result.ok(order);
    }
}
