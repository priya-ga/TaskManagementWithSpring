package com.todoSpring.service;

import com.todoSpring.model.RegistrationForm;

public interface IUserService {

    public void save(RegistrationForm user);

    boolean checkUser(String uname, String pass);

    RegistrationForm getUserByUserId(int userId);

    RegistrationForm getUserByuserName(String userName);

}
