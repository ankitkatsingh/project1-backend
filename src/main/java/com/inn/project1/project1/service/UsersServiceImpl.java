package com.inn.project1.project1.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.project1.project1.dao.IRolesDao;
import com.inn.project1.project1.dao.IUsersDao;
import com.inn.project1.project1.dao.IWorkSpaceDao;
import com.inn.project1.project1.models.Roles;
import com.inn.project1.project1.models.Users;
import com.inn.project1.project1.models.WorkSpace;

@Service
public class UsersServiceImpl {
	
	@Autowired
	private IUsersDao iUsersDao;
	
	@Autowired
	private IWorkSpaceDao iWorkSpaceDao;
	
	@Autowired
	private IRolesDao iRolesDao;
	
	public Users createNewUser(Users user) throws Exception{
		System.out.println("user object==="+user);
		Users existingUser = null;
		Users retunedUser = new Users();
		try {
			existingUser = iUsersDao.findUserByUserName(user.getUserName());
			if(existingUser != null) {
				//User with userName already exist
				throw new Exception("User With userName "+ user.getUserName() + "already Exists");
			}else {
				//create New User
				if(user.getWorkSpace() != null) {
					WorkSpace workSpace = iWorkSpaceDao.findById(user.getWorkSpace().getId()).orElse(null);
					if(workSpace != null) {
						iWorkSpaceDao.save(workSpace);
					}else {
						throw new Exception("WorkSpace Does not exist");
					}
				}
				if(CollectionUtils.isNotEmpty(user.getRoles())) {
					for(Roles role : user.getRoles()) {
						Roles roles = iRolesDao.findById(role.getId()).orElse(null);
						if(roles != null) {
							iRolesDao.save(roles);
						}else {
							throw new Exception("Role Does Not Exist");
						}
					}
				}
				retunedUser = iUsersDao.save(user);
			}
		}catch(Exception e) {
			
		}
		return retunedUser;
	}

	public Boolean checkUserCreditians(String userName, String password) {
		try {
			Boolean returnValue = false;
			Users user = iUsersDao.findUserByUserNameAndPassword(userName, password);
			System.out.println("user==="+user);
			if(user != null) {
				returnValue = true;
			}
			return returnValue;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Users> getAllExistingUserList(){
		try {
			return iUsersDao.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
