package com.sula.inventory.controller;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sula.inventory.config.ApplicationPathsConfig;
import com.sula.inventory.model.UserRole;
import com.sula.inventory.service.RoleService;
import com.sula.inventory.service.UserRoleService;
import com.sula.inventory.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(ApplicationPathsConfig.UserRoleCtrl.CTRL)
public class UserRoleController extends BaseController {

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/add.ajax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        UserRole userRole = new UserRole();
        if (request.getParameter("user") != null && !request.getParameter("user").equals("")) {
            userRole.setUser(userService.getUser(Long.parseLong(request.getParameter("user"))));
        }
        if (request.getParameter("role") != null && !request.getParameter("role").equals("")) {
            userRole.setRole(roleService.getRole(Long.parseLong(request.getParameter("role"))));
        }
        return userRoleService.addUserRole(userRole);
    }

    @RequestMapping(value = "/delete.ajax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        UserRole userRole = new UserRole();
        if (request.getParameter("user") != null && !request.getParameter("user").equals("")) {
            userRole.setUser(userService.getUser(Long.parseLong(request.getParameter("user"))));
        }
        if (request.getParameter("role") != null && !request.getParameter("role").equals("")) {
            userRole.setRole(roleService.getRole(Long.parseLong(request.getParameter("role"))));
        }
        return userRoleService.deleteUserRole(userRole);
    }

    @RequestMapping(value = "/assigned.ajax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> assigned(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        UserRole userRole = new UserRole();
        if (request.getParameter("user") != null && !request.getParameter("user").equals("")) {
            userRole.setUser(userService.getUser(Long.parseLong(request.getParameter("user"))));
        }
        String roleName = "";
        if (request.getParameter("name") != null) {
            roleName = request.getParameter("name");
        }
        if (request.getParameter("pageable") != null && Boolean.parseBoolean(request.getParameter("pageable")) == true) {
            PageRequest pr = getPage(request.getParameter("page"), request.getParameter("limit"));
            return userRoleService.getAssignedUserRoleList(userRole, roleName, pr);
        } else {
            return userRoleService.getAssignedUserRoleList(userRole, roleName);
        }
    }

    @RequestMapping(value = "/assignable.ajax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> assignable(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        UserRole userRole = new UserRole();
        if (request.getParameter("user") != null && !request.getParameter("user").equals("")) {
            userRole.setUser(userService.getUser(Long.parseLong(request.getParameter("user"))));
        }
        String roleName = "";
        if (request.getParameter("name") != null) {
            roleName = request.getParameter("name");
        }
        if (request.getParameter("pageable") != null && Boolean.parseBoolean(request.getParameter("pageable")) == true) {
            PageRequest pr = getPage(request.getParameter("page"), request.getParameter("limit"));
            return userRoleService.getAssignableUserRoleList(userRole, roleName, pr);
        } else {
            return userRoleService.getAssignableUserRoleList(userRole, roleName);
        }
    }
}
