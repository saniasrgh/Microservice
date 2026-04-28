package com.sania.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sania.order.model.Order;
import com.sania.order.service.OrderService;
import com.sania.order.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }
    
    @GetMapping(path = "{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping(path = "/produk/{id}")
    public List<ResponseTemplate> getOrderWithProdukById(@PathVariable("id") Long id){
        return orderService.getOrderWithProdukById(id);
    }

    @PutMapping(path = "/{id}")
    public void updateOrder(@PathVariable("id") Long id,
            @RequestParam(required = false) int jumlah,
            @RequestParam(required = false) String tanggal,
            @RequestParam(required = false) String status) {
        orderService.update(id, jumlah, tanggal, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
