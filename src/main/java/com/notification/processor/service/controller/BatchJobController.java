package com.notification.processor.service.controller;

import com.notification.processor.service.service.JobServices;
import com.notification.processor.service.api.StatusResponse;
import com.notification.processor.service.batch.dto.ProcessFileRequestDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by Ext_IdanF on 30/12/2014.
 */
@RestController
@RequestMapping("/batch")
public class BatchJobController {

    @Inject
    JobServices jobServices;

    @RequestMapping(value = "/processFileJob", method = RequestMethod.POST)
    @ResponseBody
    public StatusResponse processFileJob(ProcessFileRequestDTO processFileRequestDTO) {
        return jobServices.startProcessFileJobExecution(processFileRequestDTO);
    }
}

