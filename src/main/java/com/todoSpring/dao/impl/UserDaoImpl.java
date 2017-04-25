package com.todoSpring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.todoSpring.dao.IUserDao;
import com.todoSpring.model.RegistrationForm;
import com.todoSpring.util.DBUtil;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(RegistrationForm user) {

        String sql = "INSERT INTO user (first_name, last_name, email, uname,pass)" + " VALUES (?,?,?,?,?)";

        jdbcTemplate.update(sql, user.getFisrtName(), user.getLastName(), user.getEmail(), user.getUserName(),
                user.getPassword());
        System.out.println("here");

    }

    public boolean checkUser(String uname, String pass) {

        boolean st = false;
        try {

            Connection con = DBUtil.getConnection();
            String sql = "select * from user where uname=? and pass=?";

            PreparedStatement ps = con.prepareStatement("select * from user where uname=? and pass=?");
            ps.setString(1, uname);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    @Override
    public RegistrationForm getUserByUserId(int userId) {
        boolean st = false;
        RegistrationForm dbUser = new RegistrationForm();
        try {
            Connection con = DBUtil.getConnection();

            PreparedStatement ps = con.prepareStatement("select * from user where userId=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

            dbUser.setEmail(rs.getString("email"));
            dbUser.setUserName(rs.getString("uname"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dbUser ::" + dbUser);
        return dbUser;
    }

    @Override
    public RegistrationForm getUserByuserName(String userName) {

        System.out.println("in getUser by Username");
        boolean st = false;
        RegistrationForm dbUser = new RegistrationForm();
        try {
            Connection con = DBUtil.getConnection();

            PreparedStatement ps = con.prepareStatement("select * from user where uname=?");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

            // dbUser.setFisrtName(rs.getString("fisrt_name"));
            // System.out.println("FIRSTNAME: " +dbUser.getFisrtName());
            dbUser.setUserId(rs.getInt("userId"));
            dbUser.setEmail(rs.getString("email"));
            dbUser.setUserName(rs.getString("uname"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dbUser ::" + dbUser);
        return dbUser;
    }

}
