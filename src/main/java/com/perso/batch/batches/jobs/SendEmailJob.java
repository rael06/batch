package com.perso.batch.batches.jobs;

import com.perso.batch.models.Email;
import com.perso.batch.models.UserTemplates;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendEmailJob {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private Step1 step1;

  @Bean
  public Job sendEmail() {
    Step s1 = stepBuilderFactory.get("step1")
        .<UserTemplates, List<Email>>chunk(1)
        .reader(this.step1.userTemplateReader)
        .processor(this.step1.userTemplatesToEmailProcessor)
        .writer(this.step1.emailWriter)
        .build();

    return jobBuilderFactory.get("sendEmailJob")
        .incrementer(new RunIdIncrementer())
        .start(s1)
        .build();
  }
}
