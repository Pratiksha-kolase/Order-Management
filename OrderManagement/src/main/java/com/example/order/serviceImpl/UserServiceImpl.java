package com.example.order.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.order.dao.UserDao;
import com.example.order.dto.UserDto;
import com.example.order.model.UserModel;
import com.example.order.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<String> register(UserDto userDto) {
	
		UserModel userModel = new UserModel();
		Optional<UserModel> firstname = userDao.findByFirstname(userDto.getFirstname());
		Optional<UserModel> lastname = userDao.findByLastname(userDto.getLastname());
		if (!firstname.isPresent() && !lastname.isPresent()) {

			userModel.setFirstname(userDto.getFirstname());
			userModel.setLastname(userDto.getLastname());
		}

		Optional<UserModel> username = userDao.findByUsername(userDto.getUsername());

		if (!username.isPresent()) {
			userModel.setUsername(userDto.getUsername());
		} else {
			return new ResponseEntity<>("User Already Exists...", HttpStatus.OK);
		}

		Optional<UserModel> mobile = userDao.findByPhone(userDto.getPhone());
		if (!mobile.isPresent()) {
			userModel.setPhone(userDto.getPhone());
		} else {
			return new ResponseEntity<>("Mobile number Already Exists...", HttpStatus.OK);
		}

		Optional<UserModel> email = userDao.findByEmail(userDto.getEmail());
		if (!email.isPresent()) {
			BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			String encrypt=bCryptPasswordEncoder.encode(userDto.getPassword());
			userModel.setPassword(encrypt);
			userModel.setEmail(userDto.getEmail());
			userModel.setAddress(userDto.getAddress());
			
			userDao.save(userModel);
			return new ResponseEntity<>("User Registered........!", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("Email Already Registered...", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> login(UserDto userDto) {
	     	BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			Optional<UserModel> username1 = userDao.findByUsername(userDto.getUsername());
			String pass = userDao.findByPassword(userDto.getPassword());
			String uname = userDto.getUsername();
			
			if (username1.isPresent()) {
                  UserModel user=username1.get();
                  
				if(bCryptPasswordEncoder.matches( userDto.getPassword(), user.getPassword())) {
				return new ResponseEntity<>("Log-in Successful...!", HttpStatus.OK);
				}
				else {
					return new ResponseEntity<>("Wrong Login credentials....", HttpStatus.BAD_REQUEST);
				}
			}
			else {
			return new ResponseEntity<>("Failed to login....!",HttpStatus.OK); 
			}
			
			

		}

	@Override
	public Optional<UserModel> getByUser_id(Long user_id) {
	
		return userDao.findById(user_id);
	}

	@Override
	public Optional<UserModel> getByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public ResponseEntity<String> updateUser(Long user_id, UserDto userDto) {
	
		Optional<UserModel> user = userDao.findById(user_id);
		if (user.isPresent()) {
			UserModel userModel = userDao.getById(user_id);
			userModel.setFirstname(userDto.getFirstname());
			userModel.setLastname(userDto.getLastname());
			userModel.setPhone(userDto.getPhone());
			userModel.setAddress(userDto.getAddress());
			userDao.save(userModel);
			return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("Not found", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> deleteById(Long user_id) {
		userDao.deleteById(user_id);
		return new ResponseEntity<>("User Deleted Successfully....", HttpStatus.OK);
	}

	@Override
	public ArrayList<String> getAllEmail( ) {
	
		return userDao.getEmail();
	}

	@Override
	public List<UserModel> allUserData() {
		
		return userDao.findAll();
	}

	@Override
	public List<String> getNewUsers() {
		
		return userDao.getNew();
	}
	

}

	
	


