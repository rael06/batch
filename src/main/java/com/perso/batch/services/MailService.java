package com.perso.batch.services;

import com.perso.batch.models.Template;
import com.perso.batch.models.User;
import com.perso.batch.repositories.TemplateRepository;
import com.perso.batch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  @Value("${email.username}")
  private String from;

  @Autowired
  private JavaMailSender emailSender;

  @Autowired
  private TemplateRepository templateRepository;

  @Autowired
  private UserRepository userRepository;

  //  @PostConstruct
  public void initDatabase() {
    Template template1 = new Template();
    template1.setSubject("Bienvenue M. %NOM%");
    template1.setContent("Bonjour,\nCher %NOM% ...");
    templateRepository.save(template1);

    Template template2 = new Template();
    template2.setSubject("Super promo");
    template2.setContent("Bonjour,\nSuper promo ...");
    templateRepository.save(template2);

    Template template3 = new Template();
    template3.setSubject("Sales");
    template3.setContent("Hello,\nDear %NOM% ...");
    templateRepository.save(template3);

    Template template4 = new Template();
    template4.setSubject("Bonne nouvelle %NOM%");
    template4.setContent("Salut %NOM%,\nJ'ai le plaisir de t'annoncer que ...");
    templateRepository.save(template4);


    User user1 = new User();
    user1.setEmail("rael06@hotmail.fr");
    user1.setName("Rael Hotmail");
    userRepository.save(user1);

    User user2 = new User();
    user2.setEmail("rael06400@gmail.com");
    user2.setName("Rael Gmail");
    userRepository.save(user2);

    User user3 = new User();
    user3.setEmail("rael.calitro@ynov.com");
    user3.setName("Rael Ynov");
    userRepository.save(user3);
  }

  public void sendSimpleMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }
}
