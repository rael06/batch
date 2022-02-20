package com.perso.batch.batches.steps;

import com.perso.batch.models.Email;
import com.perso.batch.models.Template;
import com.perso.batch.models.User;
import com.perso.batch.models.UserTemplates;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTemplatesToEmailProcessor implements ItemProcessor<UserTemplates, List<Email>> {

  @Override
  public List<Email> process(UserTemplates userTemplates) throws Exception {
    return createEmail(userTemplates);
  }

  private List<Email> createEmail(UserTemplates userTemplates) {
    if (userTemplates == null) {
      return null;
    }

    User user = userTemplates.getUser();
    List<Template> templates = userTemplates.getTemplates();

    return templates.stream().map(template -> {
      Email email = new Email();
      email.setSubject(customize(template.getSubject(), user.getName()));
      email.setContent(customize(template.getContent(), user.getName()));
      email.setTo(userTemplates.getUser().getEmail());
      return email;
    }).toList();
  }

  private String customize(String templatePart, String name) {
    return templatePart.replace("%NOM%", name);
  }
}
