
package com.demo1.services;

import com.demo1.dto.OrderResponseDTO;
import com.demo1.dto.ProductDTO;
import com.demo1.models.Order;
import com.demo1.repositories.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(
            OrderRepository orderRepository,
            RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
        return order;
    }

    public Order updateOrder(Long id, Order updateOrder) {
        Order order = getOrderById(id);

        order.setOrderAmount(updateOrder.getOrderAmount());
        order.setProductId(updateOrder.getProductId());
        order.setQuantity(updateOrder.getQuantity());
        order.setStatus(updateOrder.getStatus());

        return orderRepository.save(order);
    }

    public OrderResponseDTO getOrderWithProduct(Long id) {
        Order order = getOrderById(id);
        String url = "http://localhost:8080/api/product/{productId}";

        ProductDTO product = restTemplate.getForObject(
                url,
                ProductDTO.class,
                order.getProductId()
        );
        OrderResponseDTO response = new OrderResponseDTO();

        response.setOrderId(order.getOrderId());
        response.setProductId(order.getProductId());
        response.setProductName(product.getProductName());
        response.setQuantity(order.getQuantity());
        response.setOrderAmount(order.getOrderAmount());
        response.setStatus(order.getStatus());

        return response;
    }
}
