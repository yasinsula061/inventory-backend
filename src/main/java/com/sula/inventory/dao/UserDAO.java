package com.sula.inventory.dao;

import com.sula.inventory.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseDAO<User, Long> {
}
