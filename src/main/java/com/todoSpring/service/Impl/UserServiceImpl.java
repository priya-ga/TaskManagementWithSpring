package com.todoSpring.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoSpring.dao.IUserDao;
import com.todoSpring.model.RegistrationForm;
import com.todoSpring.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	public void save(RegistrationForm user) {
		System.out.println("serviceImpl user : " + user);
		userDao.save(user);
	}

	@Override
	public boolean checkUser(String uname, String pass) {
		return userDao.checkUser(uname, pass);
	}

    @Override
    public RegistrationForm getUserByUserId(int userId) {
       
        return userDao.getUserByUserId(userId);
    }

    @Override
    public RegistrationForm getUserByuserName(String userName) {
        
        return userDao.getUserByuserName(userName);
    }

}
