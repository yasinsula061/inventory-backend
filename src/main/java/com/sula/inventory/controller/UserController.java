package com.sula.inventory.controller;

import com.sula.inventory.config.ApplicationPathsConfig;
import com.sula.inventory.model.User;
import com.sula.inventory.service.PersonService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@RequestMapping(ApplicationPathsConfig.UserCtrl.CTRL)
public class UserController extends BaseController {

    @Autowired
    PersonService personService;

    @ResponseBody
    @RequestMapping(value = "/add.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.user.add')")
    public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        User user = new User();
        if (request.getParameter("person") != null && !request.getParameter("person").equals("")) {
            user.setPerson(personService.getPerson(Long.parseLong(request.getParameter("person"))));
        }
        if (request.getParameter("username") != null) {
            user.setUsername(request.getParameter("username"));
        }
        if (request.getParameter("active") != null) {
            user.setActive(Boolean.parseBoolean(request.getParameter("active")));
        }
        if (request.getParameter("locked") != null) {
            user.setLocked(Boolean.parseBoolean(request.getParameter("locked")));
        }
        return userService.addUser(user);
    }

    @ResponseBody
    //@PreAuthorize("hasAuthority('directory.user.edit')")
    @RequestMapping(value = "/edit.ajax", method = RequestMethod.GET)
    public Map<String, Object> edit(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        User user = new User();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            user.setId(Long.parseLong(request.getParameter("id")));
        }
        if (request.getParameter("person") != null && !request.getParameter("person").equals("")) {
            user.setPerson(personService.getPerson(Long.parseLong(request.getParameter("person"))));
        }
        if (request.getParameter("username") != null) {
            user.setUsername(request.getParameter("username"));
        }
        if (request.getParameter("active") != null) {
            user.setActive(Boolean.parseBoolean(request.getParameter("active")));
        }
        if (request.getParameter("locked") != null) {
            user.setLocked(Boolean.parseBoolean(request.getParameter("locked")));
        }
        return userService.editUser(user);
    }

    @RequestMapping(value = "/resetpassword.ajax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> resetpassword(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        User user = new User();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            user.setId(Long.parseLong(request.getParameter("id")));
        }
        return userService.resetPassword(user);
    }

    @ResponseBody
    @RequestMapping(value = "/delete.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.user.delete')")
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        User user = new User();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            user.setId(Long.parseLong(request.getParameter("id")));
        }
        return userService.deleteUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/list.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.user.list')")
    public void list(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        User user = new User();
        if (request.getParameter("person") != null && !request.getParameter("person").equals("")) {
            user.setPerson(personService.getPerson(Long.parseLong(request.getParameter("person"))));
        }
        if (request.getParameter("username") != null) {
            user.setUsername(request.getParameter("username"));
        }
        if (request.getParameter("active") != null) {
            user.setActive(Boolean.parseBoolean(request.getParameter("active")));
        }
        if (request.getParameter("locked") != null) {
            user.setLocked(Boolean.parseBoolean(request.getParameter("locked")));
        }
    }


    /*@Autowired
    UserDAO userDAO;

    @ResponseBody
    @RequestMapping(value = "/list.ajax", method = RequestMethod.GET)
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        User user = new User();
        if (request.getParameter("username") != null) {
            user.setUsername(request.getParameter("username"));
        }
        if (request.getParameter("active") != null) {
            user.setActive(Boolean.parseBoolean(request.getParameter("active")));
        }
        if (request.getParameter("locked") != null) {
            user.setLocked(Boolean.parseBoolean(request.getParameter("locked")));
        }
        return userService.getUserList(user);

    }*/

}