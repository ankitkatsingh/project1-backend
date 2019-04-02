package com.inn.project1.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inn.project1.project1.models.WorkSpace;
import com.inn.project1.project1.service.WorkSpaceServiceImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class WorkSpaceRestController {
	
	@Autowired
	private WorkSpaceServiceImpl workSpaceServiceImpl;
	
	@GetMapping("/api/WorkSpace/getAllWorkSpaceList")
	public List<WorkSpace> getAllWorkSpaceList(){
		try {
			return workSpaceServiceImpl.getAllWorkSpace();
		}catch(Exception e) {
			
		}
		return null;
	}
	
	@GetMapping("/api/WorkSpace/getWorkSpaceById/{id}")
	public WorkSpace getWorkSpaceById(@PathVariable("id") Integer id) {
		try {
			return workSpaceServiceImpl.getWorkSpaceById(id);
		}catch(Exception e) {
			
		}
		return null;
	}

}
