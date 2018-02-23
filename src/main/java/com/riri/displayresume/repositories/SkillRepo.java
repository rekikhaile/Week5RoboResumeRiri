package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Job;
import com.riri.displayresume.model.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SkillRepo extends CrudRepository<Skill, Long> {

    Skill findSkillById(Long id);
   //Set<Skill> findAllByJobs(Job job);


}

