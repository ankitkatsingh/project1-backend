package com.inn.project1.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.project1.project1.dao.IRolesDao;
import com.inn.project1.project1.models.Roles;

@Service
public class RolesServiceImpl {

	@Autowired
	private IRolesDao iRolesDao;
	
	public List<Roles> getAllRoles(){
		try {
			return iRolesDao.findAll();
		}catch(Exception e) {
			
		}
		return null;
	}
	
	public Roles getRoleById(Integer id) {
		try {
			return iRolesDao.findById(id).get();
		}catch(Exception e) {
			
		}
		return null;
	}
}
