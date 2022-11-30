package com.example.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.OrderDto;
import com.example.order.dto.UserDto;
import com.example.order.model.OrderModel;
import com.example.order.model.UserModel;
import com.example.order.service.OrderService;
import com.example.order.service.UserService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

//	.....................................order................................
	@PostMapping("/placeorder")
	public ResponseEntity<String> order(@RequestBody OrderDto orderDto) {
		return orderService.order(orderDto);
	}

	@PutMapping("/updateMyorder/{oId}")
	public ResponseEntity<String> updateByOrder_id(@PathVariable("oId") Long oId,
			@RequestBody OrderDto orderDto) {
		return orderService.updateByOId(oId, orderDto);
	}

	@GetMapping("/showMyorder/{oId}")
	public Optional<OrderModel> getByOId(@PathVariable("oId") Long oId) {
		return orderService.getByOId(oId);
	}

	@GetMapping("/getAll")
	public List<OrderModel> allData() {
		return orderService.allData();
	}

	@DeleteMapping("/deleteMyorder/{oId}")
	public ResponseEntity<String> deleteOrder(@PathVariable("oId") Long oId) {
		return orderService.deleteOrder(oId);
	}

	
//	......................................user registration...........................
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDto userDto)
	{
		return userService.register(userDto);
	}

	@GetMapping("/getAllUser")
	public List<UserModel> allUserData() {
		return userService.allUserData();
	}
	@GetMapping("/getUserById/{user_id}")
	public Optional<UserModel> getByUser_id(@PathVariable("user_id") Long user_id) {
		return userService.getByUser_id(user_id);
	}

	@GetMapping("/getByUsername/{username}")
	public Optional<UserModel> getByUsername(@PathVariable("username") String username) {
		return userService.getByUsername(username);
	}
	
	
	@GetMapping("/getEmail")
	public ArrayList<String> getAllEmail() {
		return userService.getAllEmail();
	}
	
	@GetMapping("/getNewUsers")
	public List<String> getNewUsers() {
		return userService.getNewUsers();
	}

	@PutMapping("/updatebyUserid{user_id}")
	public ResponseEntity<String> updateUser(@PathVariable("user_id") Long user_id, @RequestBody UserDto userDto) {
		return userService.updateUser(user_id, userDto);
	}

	@DeleteMapping("/deleteUser/{user_id}")
	public ResponseEntity<String> deleteById(@PathVariable("user_id") Long user_id) {
		return userService.deleteById(user_id);
	}
	
	
//	...........................login.................................................
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userDto)
	{
		return userService.login(userDto);
	}
}
