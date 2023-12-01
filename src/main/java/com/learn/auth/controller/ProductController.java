package com.learn.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private Logger log = Logger.getLogger(this.getClass().getName());
    @GetMapping
    public String test(){
        log.info("Masuk");
        return "ok";
    }
}
