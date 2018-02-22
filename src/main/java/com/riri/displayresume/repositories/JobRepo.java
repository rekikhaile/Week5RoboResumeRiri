package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepo extends CrudRepository<Job, Long>{
    Iterable<Job> findJobsBySkillsets();
}
