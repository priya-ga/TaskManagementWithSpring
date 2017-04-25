package com.todoSpring.dao;

import com.todoSpring.model.RegistrationForm;

public interface IUserDao {
    
    void save(RegistrationForm user);

    boolean checkUser(String uname, String pass);

    RegistrationForm getUserByUserId(int userId);

    RegistrationForm getUserByuserName(String userName);
}
