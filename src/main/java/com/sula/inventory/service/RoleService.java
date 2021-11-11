package com.sula.inventory.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sula.inventory.dao.RoleDAO;
import com.sula.inventory.model.Role;
import com.sula.inventory.spec.RoleSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class RoleService extends BaseService {

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    RoleSpec roleSpec;

    @Autowired
    UserRoleService userRoleService;


    public Map<String, Object> addRole(Role role) {
        Map<String, Object> result = new HashMap<>();
        List<Role> list = roleDAO.findAll(roleSpec.findByIdAndName(role));
        if (list == null || list.size() < 1) {
            roleDAO.saveAndFlush(role);
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    public Map<String, Object> editRole(Role role) {
        Map<String, Object> result = new HashMap<>();
        Role oldRole = roleDAO.findById(role.getId()).get();
        List<Role> list = roleDAO.findAll(roleSpec.findByIdAndName(role));
        if (oldRole != null && list == null || list.size() < 1) {
            roleDAO.saveAndFlush(role);
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    public Map<String, Object> deleteRole(Role role) {
        Map<String, Object> result = new HashMap<>();
        Role oldRole = roleDAO.findById(role.getId()).get();
        if (oldRole != null) {
            roleDAO.deleteById(role.getId());
            userRoleService.deleteUserRole(role);
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    public Role getRole(Long roleId) {
        return roleDAO.findById(roleId).get();
    }

    public Map<String, Object> getRoleList(Role role, PageRequest page) {
        Map<String, Object> result = new HashMap<>();
        List<Role> list = roleDAO.findAll(roleSpec.findByAndCriteria(role), page).getContent();
        List<Role> allList = roleDAO.findAll(roleSpec.findByAndCriteria(role));
        result.put("data", list);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public Map<String, Object> getRoleList(Role role) {
        Map<String, Object> result = new HashMap<>();
        List<Role> allList = roleDAO.findAll(roleSpec.findByAndCriteria(role));
        result.put("data", allList);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public Map<String, Object> getAssignedRoleList(List<Long> roleIds, String roleName, PageRequest page) {
        Map<String, Object> result = new HashMap<>();
        List<Role> list = roleDAO.findAll(roleSpec.findByInCriteria(roleIds, roleName), page).getContent();
        List<Role> allList = roleDAO.findAll(roleSpec.findByInCriteria(roleIds, roleName));
        result.put("data", list);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public Map<String, Object> getAssignedRoleList(List<Long> roleIds, String roleName) {
        Map<String, Object> result = new HashMap<>();
        List<Role> allList = roleDAO.findAll(roleSpec.findByInCriteria(roleIds, roleName));
        result.put("data", allList);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public Map<String, Object> getAssignableRoleList(List<Long> roleIds, String roleName, PageRequest page) {
        Map<String, Object> result = new HashMap<>();
        List<Role> list = roleDAO.findAll(roleSpec.findByNotInCriteria(roleIds, roleName), page).getContent();
        List<Role> allList = roleDAO.findAll(roleSpec.findByNotInCriteria(roleIds, roleName));
        result.put("data", list);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public Map<String, Object> getAssignableRoleList(List<Long> roleIds, String roleName) {
        Map<String, Object> result = new HashMap<>();
        List<Role> allList = roleDAO.findAll(roleSpec.findByNotInCriteria(roleIds, roleName));
        result.put("data", allList);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

}
