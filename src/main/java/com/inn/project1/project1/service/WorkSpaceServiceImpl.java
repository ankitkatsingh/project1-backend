package com.inn.project1.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.project1.project1.dao.IWorkSpaceDao;
import com.inn.project1.project1.models.WorkSpace;

@Service
public class WorkSpaceServiceImpl {
	
	@Autowired
	private IWorkSpaceDao iWorkSpaceDao;
	
	public List<WorkSpace> getAllWorkSpace(){
		try {
			return iWorkSpaceDao.findAll();
		}catch(Exception e) {
			
		}
		return null;
	}
	
	public WorkSpace getWorkSpaceById(Integer id) {
		try {
			return iWorkSpaceDao.findById(id).get();
		}catch(Exception e) {
			
		}
		return null;
	}

}
