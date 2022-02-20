package com.perso.batch.batches.steps;

import com.perso.batch.models.Email;
import com.perso.batch.services.MailService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailWriter implements ItemWriter<List<Email>> {

  @Autowired
  private MailService mailService;

  @Override
  public void write(List<? extends List<Email>> list) throws Exception {
    if (list.isEmpty()) {
      return;
    }

    list.forEach(emails -> emails.forEach(email -> mailService.sendSimpleMessage(email.getTo(), email.getSubject(), email.getContent())));
  }
}
