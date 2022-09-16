package com.batch.listeners;

import javax.batch.api.listener.JobListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobResultListener implements JobExecutionListener {
	
	private Logger logger = LoggerFactory.getLogger(JobResultListener.class);
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.info("before job");		
		String jobName = jobExecution.getJobInstance().getJobName();
		logger.info("Job Name :: "+jobName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("after job");		
	}

}
