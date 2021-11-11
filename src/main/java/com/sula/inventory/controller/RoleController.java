package com.sula.inventory.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sula.inventory.config.ApplicationPathsConfig;
import com.sula.inventory.model.Role;
import com.sula.inventory.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(ApplicationPathsConfig.RoleCtrl.CTRL)
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "/add.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.role.add')")
    public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Role role = new Role();
        if (request.getParameter("name") != null) {
            role.setName(request.getParameter("name"));
        }
        return roleService.addRole(role);
    }

    @ResponseBody
    //@PreAuthorize("hasAuthority('directory.role.edit')")
    @RequestMapping(value = "/edit.ajax", method = RequestMethod.GET)
    public Map<String, Object> edit(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Role role = new Role();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            role.setId(Long.parseLong(request.getParameter("id")));
        }
        if (request.getParameter("name") != null) {
            role.setName(request.getParameter("name"));
        }
        return roleService.editRole(role);
    }

    @ResponseBody
    @RequestMapping(value = "/delete.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.role.delete')")
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Role role = new Role();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            role.setId(Long.parseLong(request.getParameter("id")));
        }
        return roleService.deleteRole(role);
    }

    @ResponseBody
    @RequestMapping(value = "/list.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.role.list')")
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Role role = new Role();
        if (request.getParameter("name") != null) {
            role.setName(request.getParameter("name"));
        }
        if (request.getParameter("active") != null) {
            role.setActive(Boolean.parseBoolean(request.getParameter("active")));
        }

        if (request.getParameter("pageable") != null && Boolean.parseBoolean(request.getParameter("pageable")) == true) {
            PageRequest pr = getPage(request.getParameter("page"), request.getParameter("limit"));
            return roleService.getRoleList(role, pr);
        } else {
            return roleService.getRoleList(role);
        }
    }
}
