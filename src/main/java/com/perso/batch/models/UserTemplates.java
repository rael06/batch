package com.perso.batch.models;

import java.util.List;

public class UserTemplates {
  private User user;
  private List<Template> templates;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Template> getTemplates() {
    return templates;
  }

  public void setTemplates(List<Template> templates) {
    this.templates = templates;
  }
}
