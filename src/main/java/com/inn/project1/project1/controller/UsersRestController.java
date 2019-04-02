package com.inn.project1.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.project1.project1.models.Users;
import com.inn.project1.project1.service.UsersServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class UsersRestController {
	
	@Autowired
	private UsersServiceImpl usersServiceImpl;
	
	@PostMapping("/api/Users/createNewUser")
	public Users createNewUser(@RequestBody Users users) throws Exception{
		System.out.println("User object in rest==="+users);
		try {
			return usersServiceImpl.createNewUser(users);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	@GetMapping("/api/Users/checkUserCreditians/{userName}/{password}")
	public Boolean checkUserCreditians(@PathVariable("userName") String userName,@PathVariable("password") String password) {
		try {
			return usersServiceImpl.checkUserCreditians(userName,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/api/Users/getAllExistingUserList")
	public List<Users> getAllExistingUserList(){
		try {
			return usersServiceImpl.getAllExistingUserList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
