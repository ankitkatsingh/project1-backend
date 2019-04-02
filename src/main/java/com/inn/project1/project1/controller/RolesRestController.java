package com.inn.project1.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inn.project1.project1.models.Roles;
import com.inn.project1.project1.service.RolesServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class RolesRestController {

	@Autowired
	private RolesServiceImpl rolesServiceImpl;
	
	@GetMapping("/api/Roles/getAllRoles")
	public List<Roles> getAllRoles(){
		try {
			return rolesServiceImpl.getAllRoles();
		}catch(Exception e) {
			
		}
		return null;
	}
	
	@GetMapping("/api/Roles/getRoleById/{id}")
	public Roles getRoleById(@PathVariable("id") Integer id) {
		try {
			return rolesServiceImpl.getRoleById(id);
		}catch(Exception e) {
			
		}
		return null;
	}
}
