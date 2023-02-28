package com.zhen777.www.aop.clazz.controller;

// DemoController.java

import com.zhen777.www.aop.clazz.anno.NeedCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@NeedCheck
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/l1")
    @NeedCheck
    public void l1() {
        logger.info("l1");
    }

    @GetMapping("/l2")
    public void l2() {
        logger.debug("l2");
    }

}
