package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Experience;
import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepo extends CrudRepository<Experience, Long> {
}
