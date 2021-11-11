
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
