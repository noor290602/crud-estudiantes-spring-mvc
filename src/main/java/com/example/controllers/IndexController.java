package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/university")
public class IndexController {
    
    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("")
    public String index() {
        LOGGER.info("Index page");
        return "index";
    }
}
