package com.example.order.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.order.dao.OrderDao;
import com.example.order.dto.OrderDto;
import com.example.order.model.OrderModel;
import com.example.order.service.OrderService;

@Service
public class OrderderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;

	@Override
	public ResponseEntity<String> order(OrderDto orderDto) {
	
		OrderModel orderModel=new OrderModel();
		orderModel.setPrice(orderDto.getPrice());
		orderModel.setAddress(orderDto.getAddress());
		orderModel.setProduct_name(orderDto.getProduct_name());
		orderModel.setUser_id(orderDto.getUser_id());
		orderDao.save(orderModel);
		return new ResponseEntity<>("Order placed...!",HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<String> updateByOId(Long oId, OrderDto orderDto) {
		Optional<OrderModel> num = orderDao.findById(oId);
		
		System.out.println(num.isPresent());
		if (num.isPresent()) {
			OrderModel orderModel=orderDao.getById(oId);
			orderModel.setUser_id(orderDto.getUser_id());
			orderModel.setPrice(orderDto.getPrice());
			orderModel.setProduct_name(orderDto.getProduct_name());
			orderDao.save(orderModel);
			return new ResponseEntity<>("Order Updated....!!!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No order number found....!!!", HttpStatus.OK);
		}
	}

	@Override
	public Optional<OrderModel> getByOId(Long oId) {
		return orderDao.findById(oId);
	}


	@Override
	public ResponseEntity<String> deleteOrder(Long oId) {

		orderDao.deleteById(oId);
		return new ResponseEntity<>("Order Deleted Successfully....", HttpStatus.OK);
	}


	@Override
	public List<OrderModel> allData() {
	
		return orderDao.findAll();
		}
	
}
