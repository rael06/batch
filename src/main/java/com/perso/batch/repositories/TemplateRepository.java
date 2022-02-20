package com.perso.batch.repositories;

import com.perso.batch.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
