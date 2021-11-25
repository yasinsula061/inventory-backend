
package com.sula.inventory.service;


import com.sula.inventory.dao.UserDAO;
import com.sula.inventory.model.User;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sula.inventory.spec.UserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService extends BaseService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserSpec userSpec;

    public Map<String, Object> addUser(User user) {
        Map<String, Object> result = new HashMap<>();
        List<User> list = userDAO.findAll(userSpec.findByIdAndPersonOrUser(user));
        if (list == null || list.size() < 1) {
            String password = genPassword();
            user.setPassword(encPassword(password));
            userDAO.saveAndFlush(user);
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    public Map<String, Object> editUser(User user) {
        Map<String, Object> result = new HashMap<>();
        User oldUser = userDAO.findById(user.getId()).get();
        List<User> list = userDAO.findAll(userSpec.findByIdAndPersonOrUser(user));
        if (oldUser != null && list == null || list.size() < 1) {
            user.setPassword(oldUser.getPassword());
            userDAO.saveAndFlush(user);
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    public Map<String, Object> deleteUser(User user) {
        Map<String, Object> result = new HashMap<>();
        User oldUser = userDAO.findById(user.getId()).get();
        if (oldUser != null) {
            userDAO.deleteById(user.getId());
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    public Map<String, Object> resetPassword(User user) {
        Map<String, Object> result = new HashMap<>();
        user = userDAO.findById(user.getId()).get();
        if (user != null) {
            String password = genPassword();
            user.setPassword(encPassword(password));
            userDAO.saveAndFlush(user);
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }


    public Map<String, Object> getUserList(User user) {
        Map<String, Object> result = new HashMap<>();
        List<User> allList = userDAO.findAll(userSpec.findByAndCriteria(user));

        //JSONArray jsonArr = new JSONArray();
        result.put("data", allList);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public User getUser(User user) {
        return userDAO.findOne(userSpec.findByAndCriteria(user)).get();
    }

    public List<User> getUsers(User user) {
        return userDAO.findAll(userSpec.findByAndCriteria(user));
    }

    public User getUser(Long id) {
        return userDAO.findById(id).get();
    }
}
