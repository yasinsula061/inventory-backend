package com.sula.inventory.dao;

import com.sula.inventory.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends BaseDAO<Person, Long> {

}
