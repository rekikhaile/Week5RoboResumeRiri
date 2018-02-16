package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Summary;
import org.springframework.data.repository.CrudRepository;

public interface SummaryRepo extends CrudRepository<Summary, Long>{
}
