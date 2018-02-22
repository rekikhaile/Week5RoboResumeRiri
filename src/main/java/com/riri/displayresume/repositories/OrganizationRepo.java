package com.riri.displayresume.repositories;

import com.riri.displayresume.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepo extends CrudRepository<Organization, Long> {
}
