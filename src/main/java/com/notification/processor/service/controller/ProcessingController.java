package com.notification.processor.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ext_IdanF on 25/12/2014.
 */
@RestController
public class ProcessingController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessingController.class);

    @RequestMapping("/process")
    public String testProcess()
    {
        return "hello";
    }


}
