package com.batch.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.batch.model.Response;

@RestController
@CrossOrigin(origins = "*")
public class JobStarter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Job firstJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@GetMapping("/startFirstJob")
	public ResponseEntity<Object> startFirstJob(@RequestParam("createdBy") String createdBy) {
		Response response;
		try {
			CompletableFuture.runAsync(() ->{
				try {
					JobParameters jobParameters = null;
					jobParameters = initJobParams(createdBy);
					logger.info("job parameters :: "+jobParameters.toString());
					jobLauncher.run(firstJob, jobParameters);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			response = new Response("200", "SUCCESS", "Job Execution Started !");
		} catch (Exception e) {
			logger.error("Error ::"+e);
			response = new Response("409", "ERROR", "Job Execution Failed !");
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	private JobParameters initJobParams(String createdBy) {
		Map<String, JobParameter> m = new HashMap<>();
		m.put("createdBy", new JobParameter(createdBy));
		JobParameters jobParameters = new JobParameters(m);
		return jobParameters;
	}
}
