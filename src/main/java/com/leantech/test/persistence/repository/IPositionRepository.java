package com.leantech.test.persistence.repository;

import com.leantech.test.persistence.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Position repository interface
 */
public interface IPositionRepository extends JpaRepository<PositionEntity, Integer> {
}
