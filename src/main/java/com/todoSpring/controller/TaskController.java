package com.todoSpring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.todoSpring.model.LoginForm;
import com.todoSpring.model.RegistrationForm;
import com.todoSpring.model.Task;
import com.todoSpring.model.UserTask;
import com.todoSpring.model.UserTaskDTO;
import com.todoSpring.service.ITaskService;
import com.todoSpring.service.IUserService;

@Controller
@RequestMapping(value = "/task")
@SessionAttributes(value = "user")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ModelAndView listContact(ModelAndView model) {
        LOGGER.info("listtask called!!");
        List<Task> taskList = taskService.getUserTaskList();
        model.addObject("listTask", taskList);
        model.setViewName("listTask");
        return model;
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.GET)
    public ModelAndView newTask(ModelAndView model) {
        model.addObject("task", new Task());
        model.setViewName("task");
        return model;
    }

    @RequestMapping(value = "/saveTask.htm", method = RequestMethod.POST)
    public ModelAndView saveTask(ModelAndView model, @ModelAttribute Task task,
            @ModelAttribute("user") LoginForm userSesson) {
        LOGGER.info("-----saveTask Controller invoked");
        RegistrationForm sessionUser = userService.getUserByuserName((String) userSesson.getUserName());
        Date currentDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
        task.setTaskCreationDate(ft.format(currentDate));
        task.setUserId(sessionUser.getUserId());
        taskService.saveOrUpdate(task);
        return new ModelAndView("redirect:/task/getList.htm");
    }

    @RequestMapping(value = "/editTask", method = RequestMethod.GET)
    public ModelAndView editTask(HttpServletRequest request) {
        int taskId = Integer.parseInt(request.getParameter("id"));
        Task task = taskService.get(taskId);
        ModelAndView model = new ModelAndView("task");
        model.addObject("task", task);
        return model;
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
    public ModelAndView deleteTask(HttpServletRequest request) {
        int taskId = Integer.valueOf(request.getParameter("id"));
        System.out.println("ID is : " + taskId);
        taskService.delete(taskId);
        return new ModelAndView("redirect:/task/getList.htm");
    }

    @RequestMapping(value = "/getListForDetail", method = RequestMethod.GET)
    public ModelAndView listTask(ModelAndView model) {
        List<Task> taskList = taskService.getUserTaskList();
        System.out.println("LISTTASK" + taskList);
        model.addObject("listTask", taskList);
        model.setViewName("listTaskforWorklog");
        return model;
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public ModelAndView taskDetail(ModelAndView model, HttpServletRequest request,
            @ModelAttribute("user") LoginForm usersession) {
        int taskId = Integer.parseInt(request.getParameter("id"));

        Task task = taskService.get(taskId);
        List<UserTask> userTaskList = taskService.getAllUserTaskList(task.getTaskId());
        List<UserTaskDTO> usertaskDTOList = new ArrayList<UserTaskDTO>();

        for (UserTask usertask : userTaskList) {
            RegistrationForm user = userService.getUserByUserId(usertask.getUserId());
            UserTaskDTO usertaskDTO = new UserTaskDTO();
            usertaskDTO.setTaskId(task.getTaskId());
            usertaskDTO.setTaskName(task.getTaskName());
            usertaskDTO.setUserName(user.getUserName());
            usertaskDTO.setLogStartTime(usertask.getLogStartTime());
            usertaskDTO.setLogEndTime(usertask.getLogEndTime());
            usertaskDTO.setLogDescription(usertask.getLogDescription());
            usertaskDTO.setTotalDuration(usertask.getTotalDuration());
            usertaskDTOList.add(usertaskDTO);
        }
        model.addObject("userTaskDTOList", usertaskDTOList);
        // model.addObject("UserName", usersession.getUserName());
        model.addObject("task", task);
        model.setViewName("taskDetail");
        return model;

    }

    @RequestMapping(value = "/addWorklog", method = RequestMethod.GET)
    public String addWorklog(Model model, HttpServletRequest request, @ModelAttribute("user") LoginForm userSesson) {
        String userName = userSesson.getUserName();
        RegistrationForm sessionUser = userService.getUserByuserName(userName);
        int taskId = Integer.parseInt(request.getParameter("id"));
        Task task = taskService.get(taskId);
        model.addAttribute("task", task);
        model.addAttribute("user", sessionUser);
        model.addAttribute("worklog", new UserTask());
        return "worklog";
    }

    @RequestMapping(value = "/insertWorklog", method = RequestMethod.POST)
    public String insertWorklog(@ModelAttribute UserTask usertask, Model model) {
        String difference = calculateDiffrence(usertask);
        usertask.setTotalDuration(difference);
        taskService.addUserTask(usertask);
        Task dbtask = taskService.get(usertask.getTaskId());
        model.addAttribute("task", dbtask);
        List<UserTaskDTO> usertaskDTOList = createUserTaskList(dbtask);
        model.addAttribute("userTaskDTOList", usertaskDTOList);
        return "taskDetail";
    }

    private String calculateDiffrence(UserTask usertask) {
        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startDateObj = null;
        try 
        {
            startDateObj = curFormater.parse(usertask.getLogStartTime());
        }catch (ParseException e) {

            e.printStackTrace();
        }
        Date endDateObj = null;
        try 
        {
            endDateObj = curFormater.parse(usertask.getLogEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = endDateObj.getTime() - startDateObj.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        String difference = diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds  + " seconds.";
        return difference;
    }

    private List<UserTaskDTO> createUserTaskList(Task dbtask) {
        List<UserTask> userTaskList = taskService.getAllUserTaskList(dbtask.getTaskId());
        List<UserTaskDTO> usertaskDTOList = new ArrayList<UserTaskDTO>();
        for (UserTask userTask : userTaskList) {
            RegistrationForm newuser = userService.getUserByUserId(userTask.getUserId());
            System.out.println("trash : " + newuser);
            UserTaskDTO usertaskDTO = new UserTaskDTO();
            usertaskDTO.setTaskId(dbtask.getTaskId());
            usertaskDTO.setTaskName(dbtask.getTaskName());
            usertaskDTO.setUserName(newuser.getUserName());
            usertaskDTO.setLogStartTime(userTask.getLogStartTime());
            usertaskDTO.setLogEndTime(userTask.getLogEndTime());
            usertaskDTO.setLogDescription(userTask.getLogDescription());
            usertaskDTO.setTotalDuration(calculateDiffrence(userTask));
            usertaskDTOList.add(usertaskDTO);
        }
        return usertaskDTOList;
    }
}
