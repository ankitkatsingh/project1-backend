package com.inn.project1.project1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inn.project1.project1.models.Users;

public interface IUsersDao extends JpaRepository<Users,Integer>{

	@Query(value="select user from Users user where user.userName=:userName")
	public Users findUserByUserName(@Param("userName") String userName);
	
	@Query(value="select user from Users user where user.userName=:userName and user.password=:password")
	public Users findUserByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);
}
