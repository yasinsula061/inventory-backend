package com.sula.inventory.controller;

import com.sula.inventory.config.ApplicationPathsConfig;
import com.sula.inventory.dao.UserDAO;
import com.sula.inventory.model.User;
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

    }

}