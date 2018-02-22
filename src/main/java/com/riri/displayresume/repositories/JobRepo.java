package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Job;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface JobRepo extends CrudRepository<Job, Long>{
//    Iterable<Job> findJobsBySkillsets();

}
