package com.todoSpring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoSpring.dao.ITaskDao;
import com.todoSpring.model.Task;
import com.todoSpring.model.UserTask;
import com.todoSpring.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    ITaskDao taskDaoImpl;

    @Override
    public List<Task> getUserTaskList() {
        return taskDaoImpl.getUserTaskList();
    }

    @Override
    public void saveOrUpdate(Task task) {
       taskDaoImpl.saveOrUpdate(task);
        
    }

    @Override
    public Task get(int taskId) {
       
        return taskDaoImpl.get(taskId);
    }

    @Override
    public void delete(int taskId) {
       taskDaoImpl.delete(taskId);
        
    }

    @Override
    public int edit(int taskId) {
       
        return 0;
    }

    @Override
    public List<UserTask> getAllUserTaskList(int taskId) {
       
        return taskDaoImpl.getAllUserTaskList(taskId);
    }

    @Override
    public void addUserTask(UserTask usertask) {
        taskDaoImpl.addUserTask(usertask);
        
    }


}
