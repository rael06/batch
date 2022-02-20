package com.perso.batch.batches.schedulers;

import com.perso.batch.repositories.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendEmailScheduler {

  @Autowired
  private Job sendEmail;

  @Autowired
  private JobLauncher jobLauncher;

  @Scheduled(fixedDelay = 1 * 10 * 1000)
  public void startJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
    JobParameters jobParameters = new JobParametersBuilder()
        .addLong("timestamp", System.currentTimeMillis())
        .addLong("batchSize", 1L)
        .toJobParameters();
    jobLauncher.run(sendEmail, jobParameters);
  }
}
