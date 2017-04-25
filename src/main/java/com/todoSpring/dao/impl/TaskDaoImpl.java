package com.todoSpring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.todoSpring.dao.ITaskDao;
import com.todoSpring.model.Task;
import com.todoSpring.model.UserTask;
import com.todoSpring.util.DBUtil;

@Repository
public class TaskDaoImpl implements ITaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> getUserTaskList() {
        String sql = "SELECT * FROM task";
        List<Task> listTask = jdbcTemplate.query(sql, new RowMapper<Task>() {

            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                Task aTask = new Task();

                aTask.setTaskId(rs.getInt("taskId"));
                aTask.setTaskName(rs.getString("taskName"));
                aTask.setTaskDescription(rs.getString("taskDescription"));
                aTask.setUserId(rs.getInt("userId"));
                return aTask;
            }
        });
        return listTask;

    }

    @Override
    public void delete(int taskId) {
        String sql = "DELETE FROM task WHERE taskId=?";
        jdbcTemplate.update(sql, taskId);

    }

    @Override
    public Task get(int taskId) {

        String sql = "SELECT * FROM task WHERE taskId=" + taskId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Task>() {

            public Task extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    Task task = new Task();
                    task.setTaskId(rs.getInt("taskId"));
                    task.setTaskName(rs.getString("taskName"));
                    task.setTaskDescription(rs.getString("taskDescription"));
                    task.setTaskCreationDate(rs.getString("taskCreationDate"));
                    task.setUserId(rs.getInt("userId"));
                    return task;
                }

                return null;
            }

        });
    }

    @Override
    public void saveOrUpdate(Task task) {
        System.out.println("Dao Implementation invoked !!");
        if (task.getTaskId() > 0) {
            // update
            String sql = "UPDATE task SET taskName=?, taskDescription=?, taskCreationDate=?, "
                    + "userId=? WHERE taskId=?";
            jdbcTemplate.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskCreationDate(),
                    task.getUserId(), task.getTaskId());
        } else {
            // insert
            String sql = "INSERT INTO task (taskName, taskDescription, taskCreationDate, userId)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, task.getTaskName(), task.getTaskDescription(), task.getTaskCreationDate(),
                    task.getUserId());
        }

    }

    @Override
    public List<UserTask> getAllUserTaskList(int taskId) {
        System.out.println("Inside DAO::" + taskId);
        List<UserTask> userList = new ArrayList<UserTask>();
        ResultSet resultSet = null;

        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from usertask where taskId=?");
            preparedStatement.setInt(1, taskId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserTask userTask = new UserTask();
                userTask.setUserId(resultSet.getInt("userId"));
                userTask.setTaskId(resultSet.getInt("taskId"));
                userTask.setLogStartTime(resultSet.getString("logStartTime"));
                userTask.setLogEndTime(resultSet.getString("logEndTime"));
                userTask.setLogDescription(resultSet.getString("logDescription"));
                userTask.setTotalDuration(resultSet.getString("totalDuration"));
                userList.add(userTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void addUserTask(UserTask usertask) {
        System.out.println("usertask dao :" + usertask.toString());
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement preparedStatement = conn
                    .prepareStatement("insert into usertask (userId, taskId, logStartTime,logEndTime,logDescription,totalDuration) values (?,?,?,?,?,?)");
            preparedStatement.setInt(1, usertask.getUserId());
            preparedStatement.setInt(2, usertask.getTaskId());
            preparedStatement.setString(3, usertask.getLogStartTime());
            preparedStatement.setString(4, usertask.getLogEndTime());
            preparedStatement.setString(5, usertask.getLogDescription());
            preparedStatement.setString(6, usertask.getTotalDuration());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
