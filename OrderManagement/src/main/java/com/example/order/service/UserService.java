package com.example.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.order.dto.UserDto;
import com.example.order.model.UserModel;

public interface UserService {

	ResponseEntity<String> register(UserDto userDto);

	ResponseEntity<String> login(UserDto userDto);

	Optional<UserModel> getByUser_id(Long user_id);

	Optional<UserModel> getByUsername(String username);

	ResponseEntity<String> deleteById(Long user_id);

	ResponseEntity<String> updateUser(Long user_id, UserDto userDto);

	ArrayList<String> getAllEmail( );

	List<UserModel> allUserData();

	List<String> getNewUsers();

}
