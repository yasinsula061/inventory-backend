package com.sula.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseDAO<T, E> extends JpaRepository<T, E>, JpaSpecificationExecutor<T> {

}
