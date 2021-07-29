package com.leantech.test.persistence.repository;

import com.leantech.test.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Employee Repository Interface
 */
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    List<EmployeeEntity> findByPosition_NameContainingIgnoreCaseOrPerson_NameContainingIgnoreCase(String position, String name);
}
