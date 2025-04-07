package com.examly.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Order;
import com.examly.springapp.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("post")
    public ResponseEntity<List<Order>> addOrder(@RequestBody List<Order> order) {
        List<Order> createdOrder = orderService.addOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("put/{id}")
    public ResponseEntity<Order> editOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.editOrder(id, order.getProductname(), order.getQuantity());
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Order> post(@RequestBody Order order) {
        return ResponseEntity.status(201).body(orderService.post(order));
    }

    @GetMapping("order/{field}")
    public ResponseEntity<List<Order>> sort(@PathVariable String field) {
        List<Order> sortedOrders = orderService.sort(field);
        return new ResponseEntity<>(sortedOrders, HttpStatus.OK);
    }

    @GetMapping("order/{offset}/{pagesize}")
    public ResponseEntity<List<Order>> paginate(@PathVariable int offset, @PathVariable int pagesize) {
        List<Order> paginatedOrders = orderService.page(pagesize, offset);
        return new ResponseEntity<>(paginatedOrders, HttpStatus.OK);
    }

    @GetMapping("order/{offset}/{pagesize}/{field}")
    public ResponseEntity<List<Order>> paginateAndSort(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        List<Order> paginatedSortedOrders = orderService.pagesort(pagesize, offset, field);
        return new ResponseEntity<>(paginatedSortedOrders, HttpStatus.OK);
    }
}
