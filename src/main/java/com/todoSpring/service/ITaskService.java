package com.todoSpring.service;

import java.util.List;

import com.todoSpring.model.Task;
import com.todoSpring.model.UserTask;

public interface ITaskService {

    List<Task> getUserTaskList();

    public void saveOrUpdate(Task task);

    public Task get(int taskId);

    public void delete(int taskId);

    public int edit(int taskId);
    
    List<UserTask> getAllUserTaskList(int taskId);

    void addUserTask(UserTask usertask);
}
