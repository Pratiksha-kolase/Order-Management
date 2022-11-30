package com.example.order.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.model.OrderModel;

public interface OrderDao extends JpaRepository<OrderModel,Long>{







}
