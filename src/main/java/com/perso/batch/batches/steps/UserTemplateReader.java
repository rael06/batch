package com.perso.batch.batches.steps;

import com.perso.batch.models.Template;
import com.perso.batch.models.User;
import com.perso.batch.models.UserTemplates;
import com.perso.batch.repositories.TemplateRepository;
import com.perso.batch.repositories.UserRepository;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserTemplateReader implements ItemReader<UserTemplates> {

  private int pageIndex = 0;
  private int pageMax = 0;
  private int batchSize = 0;
  private int userIndex = 0;
  private List<Template> templates = new ArrayList<>();

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TemplateRepository templateRepository;

  @BeforeStep
  public void beforeStep(StepExecution stepExecution) {
    loadTemplates();
    JobParameters jobParameters = stepExecution.getJobParameters();
    Long batchSizeParam = jobParameters.getLong("batchSize");
    if (batchSizeParam == null) {
      return;
    }
    batchSize = batchSizeParam.intValue();
    double usersSize = userRepository.count();
    pageMax = (int) Math.ceil(usersSize / batchSizeParam.doubleValue());
  }


  @Override
  public UserTemplates read() throws Exception, UnexpectedInputException,
      ParseException, NonTransientResourceException {


    if (pageIndex >= pageMax) {
      pageIndex = 0;
      return null;
    }

    Pageable limit = PageRequest.of(pageIndex++, batchSize);
    List<User> users = userRepository.findAll(limit).toList();

    if (userIndex >= batchSize) {
      userIndex = 0;
      return null;
    }
    User user = users.get(userIndex++);

    UserTemplates userTemplates = new UserTemplates();
    userTemplates.setUser(user);
    userTemplates.setTemplates(templates);
    return userTemplates;
  }

  private void loadTemplates() {
    templates = templateRepository.findAll();
  }
}

