package com.example.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.order.model.UserModel;

@Repository
public interface UserDao extends JpaRepository<UserModel,Long>{
	
	
	@Query("select email from userData order by (user_id) desc")
	public ArrayList<String> getEmail();
	
	@Query("select user_id,username,firstname,lastname,email,password,address,phone from userData order by (user_id) desc")
	public List<String> getNew();

	Optional<UserModel> findByFirstname(String firstname);

	Optional<UserModel> findByLastname(String lastname);

	Optional<UserModel> findByUsername(String username);

	Optional<UserModel> findByPhone(long phone);

	Optional<UserModel> findByEmail(String email);

	String findByPassword(String password);

	
 
}
