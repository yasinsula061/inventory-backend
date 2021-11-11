package com.sula.inventory.controller;

import com.sula.inventory.helper.Helper;
import com.sula.inventory.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public abstract class BaseController extends Helper {

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() {
        log.info("[OK]    " + getClass().getSimpleName());
    }
}