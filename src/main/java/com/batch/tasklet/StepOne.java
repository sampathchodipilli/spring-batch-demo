package com.batch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.batch.listeners.StepResultListener;

@Component
public class StepOne implements Tasklet {
	
	private Logger logger = LoggerFactory.getLogger(StepResultListener.class);
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		logger.info("Inside step 1");
		// other logic in step1 goes here
		return RepeatStatus.FINISHED;
	}

}
