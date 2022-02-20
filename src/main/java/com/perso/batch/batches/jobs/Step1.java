package com.perso.batch.batches.jobs;

import com.perso.batch.batches.steps.EmailWriter;
import com.perso.batch.batches.steps.UserTemplateReader;
import com.perso.batch.batches.steps.UserTemplatesToEmailProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Step1 {

  @Autowired
  public UserTemplateReader userTemplateReader;
  @Autowired
  public UserTemplatesToEmailProcessor userTemplatesToEmailProcessor;
  @Autowired
  public EmailWriter emailWriter;
}
