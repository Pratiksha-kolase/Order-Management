package com.example.order.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.order.dto.OrderDto;
import com.example.order.model.OrderModel;

public interface OrderService {


	ResponseEntity<String> deleteOrder(Long order_id);

	ResponseEntity<String> order(OrderDto orderDto);

	ResponseEntity<String> updateByOId(Long oId, OrderDto orderDto);

	Optional<OrderModel> getByOId(Long oId);

	

}
