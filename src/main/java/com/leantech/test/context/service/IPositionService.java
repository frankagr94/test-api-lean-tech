package com.leantech.test.context.service;

import com.leantech.test.context.domain.Employee;
import com.leantech.test.context.domain.Position;

import java.util.List;
import java.util.Optional;

/**
 * Position service interface
 */
public interface IPositionService {

    /**
     * Save method
     *
     * @param position the position parameter
     */
    Position save(Position position);

    /**
     * Exist By Id
     *
     * @param positionId the position id parameter
     * @return a boolean
     */
    boolean existById(Integer positionId);

    /**
     * Find By Id
     *
     * @param positionId the position id parameter
     * @return a position
     */
    Optional<Position> findById(Integer positionId);

    /**
     * Find All method
     *
     * @return a list of positions
     */
    List<Position> findAll();
}
