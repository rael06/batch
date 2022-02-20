package com.perso.batch.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TryService.class);

  @PostConstruct
  public void tryService(){
    LOGGER.debug("------------------try--------------------");
  }
}
