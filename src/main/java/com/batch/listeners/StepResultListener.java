package com.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepResultListener implements StepExecutionListener {
	
	private Logger logger = LoggerFactory.getLogger(StepResultListener.class);
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("before step");
		String stepName = stepExecution.getStepName();
		logger.info("Step name :: "+stepName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.info("after step");
		
		return ExitStatus.COMPLETED;
	}

}
