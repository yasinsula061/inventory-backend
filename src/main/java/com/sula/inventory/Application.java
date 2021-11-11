package com.sula.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.SpringVersion;

import java.util.logging.Logger;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static Logger logger = Logger.getLogger(Application.class.getName());

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {

        logger.info("Spring Versiyon : " + SpringVersion.getVersion());
        logger.info("SULA-WEB");
        SpringApplication.run(Application.class, args);
    }
}
