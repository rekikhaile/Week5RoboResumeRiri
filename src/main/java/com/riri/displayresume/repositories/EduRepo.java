package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Education;
import org.springframework.data.repository.CrudRepository;

public interface EduRepo extends CrudRepository<Education, Long> {
}
