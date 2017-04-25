package com.todoSpring.dao;

import java.util.List;

import com.todoSpring.model.Task;
import com.todoSpring.model.UserTask;

public interface ITaskDao {

    List<Task> getUserTaskList();

    public void saveOrUpdate(Task task);

    public void delete(int taskId);

    public Task get(int taskId);

    List<UserTask> getAllUserTaskList(int taskId);

    void addUserTask(UserTask usertask);
}
