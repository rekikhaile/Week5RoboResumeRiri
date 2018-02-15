package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Personal;
import org.springframework.data.repository.CrudRepository;

public interface PersonalRepo extends CrudRepository<Personal, Long> {
}
