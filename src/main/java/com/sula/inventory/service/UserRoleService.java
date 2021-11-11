package com.sula.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sula.inventory.dao.UserRoleDAO;
import com.sula.inventory.model.Role;
import com.sula.inventory.model.User;
import com.sula.inventory.model.UserRole;
import com.sula.inventory.spec.UserRoleSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class UserRoleService extends BaseService {

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    UserRoleSpec userRoleSpec;

    @Autowired
    RoleService roleService;

    public List<UserRole> getUserRoleList(User user) {
        return userRoleDAO.findAll(userRoleSpec.findByUser(user));
    }

    public List<UserRole> getUserRoleList(Role role) {
        return userRoleDAO.findAll(userRoleSpec.findByRole(role));
    }

    public Map<String, Object> addUserRole(UserRole userRole) {
        Map<String, Object> result = new HashMap<>();
        List<UserRole> list = userRoleDAO.findAll(userRoleSpec.findByAndCriteria(userRole));
        if (list == null || list.size() < 1) {
            userRoleDAO.saveAndFlush(userRole);
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
    
    public void deleteUserRole(User user) {
        List<UserRole> list = getUserRoleList(user);
        if (list != null && list.size() > 0) {
            list.stream().map((userRole) -> {
                userRoleDAO.deleteById(userRole.getId());
                return userRole;
            });
        }
    }

    public void deleteUserRole(Role role) {
        List<UserRole> list = getUserRoleList(role);
        if (list != null && list.size() > 0) {
            list.stream().map((userRole) -> {
                userRoleDAO.deleteById(userRole.getId());
                return userRole;
            });
        }
    }

    public Map<String, Object> deleteUserRole(UserRole userRole) {
        Map<String, Object> result = new HashMap<>();
        List<UserRole> list = userRoleDAO.findAll(userRoleSpec.findByAndCriteria(userRole));
        if (list != null && !list.isEmpty()) {
            list.forEach((oldUserRole) -> {
                userRoleDAO.deleteById(oldUserRole.getId());
            });
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    public Map<String, Object> getAssignedUserRoleList(UserRole userRole, String roleName, PageRequest page) {
        Map<String, Object> result = new HashMap<>();
        if (userRole.getUser() != null) {
            List<UserRole> list = getUserRoleList(userRole.getUser());
            List<Long> roleIds = new ArrayList<>();

            if (list != null && list.size() > 0) {
                list.forEach((newUserRole) -> {
                    addListUnified(roleIds, newUserRole.getRole().getId());
                });
            } else {
                roleIds.add(0L);
            }
            result = roleService.getAssignedRoleList(roleIds, roleName, page);
        } else {
            result.put("data", null);
            result.put("success", true);
            result.put("total", 0);
        }
        return result;
    }

    public Map<String, Object> getAssignedUserRoleList(UserRole userRole, String roleName) {
        Map<String, Object> result = new HashMap<>();
        if (userRole.getUser() != null) {
            List<UserRole> list = getUserRoleList(userRole.getUser());
            List<Long> roleIds = new ArrayList<>();

            if (list != null && list.size() > 0) {
                list.forEach((newUserRole) -> {
                    addListUnified(roleIds, newUserRole.getRole().getId());
                });
            } else {
                roleIds.add(0L);
            }
            result = roleService.getAssignedRoleList(roleIds, roleName);
        } else {
            result.put("data", null);
            result.put("success", true);
            result.put("total", 0);
        }
        return result;
    }

    public Map<String, Object> getAssignableUserRoleList(UserRole userRole, String roleName, PageRequest page) {
        Map<String, Object> result = new HashMap<>();
        if (userRole.getUser() != null) {
            List<UserRole> list = getUserRoleList(userRole.getUser());
            List<Long> roleIds = new ArrayList<>();

            if (list != null && list.size() > 0) {
                list.forEach((newUserRole) -> {
                    addListUnified(roleIds, newUserRole.getRole().getId());
                });
            } else {
                roleIds.add(0L);
            }
            result = roleService.getAssignableRoleList(roleIds, roleName, page);
        } else {
            result.put("data", null);
            result.put("success", true);
            result.put("total", 0);
        }
        return result;
    }

    public Map<String, Object> getAssignableUserRoleList(UserRole userRole, String roleName) {
        Map<String, Object> result = new HashMap<>();
        if (userRole.getUser() != null) {
            List<UserRole> list = getUserRoleList(userRole.getUser());
            List<Long> roleIds = new ArrayList<>();

            if (list != null && list.size() > 0) {
                list.forEach((newUserRole) -> {
                    addListUnified(roleIds, newUserRole.getRole().getId());
                });
            } else {
                roleIds.add(0L);
            }
            result = roleService.getAssignableRoleList(roleIds, roleName);
        } else {
            result.put("data", null);
            result.put("success", true);
            result.put("total", 0);
        }
        return result;
    }
}
