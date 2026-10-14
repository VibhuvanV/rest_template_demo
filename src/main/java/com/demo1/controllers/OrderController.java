package com.demo1.controllers;

import com.demo1.dto.OrderResponseDTO;
import com.demo1.models.Order;
import com.demo1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderByid(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.FOUND);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> addOrders(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id,@RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.deleteOrder(id), HttpStatus.OK);
    }


    @GetMapping("/orders/{id}/details")
    public ResponseEntity<OrderResponseDTO> getOrderDetails(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrderWithProduct(id)
        );
    }

}
