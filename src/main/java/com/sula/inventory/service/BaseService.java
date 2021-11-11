package com.sula.inventory.service;

import com.sula.inventory.helper.Helper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class BaseService extends Helper {

/*    @Autowired
    private SequenceGeneratorService sgs;*/

    @PostConstruct
    public void init() {
        log.info("[OK]    " + getClass().getSimpleName());
    }
}
