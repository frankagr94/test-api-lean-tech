package com.leantech.test.persistence.implementation;

import com.leantech.test.context.domain.Position;
import com.leantech.test.context.service.IPositionService;
import com.leantech.test.persistence.entity.PositionEntity;
import com.leantech.test.persistence.repository.IPositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Position Service Implementation
 */
@Service
public class PositionServiceImplementation implements IPositionService {

    private final IPositionRepository positionRepository;

    /**
     * Constructor
     *
     * @param positionRepository the position repository interface parameter
     */
    public PositionServiceImplementation(IPositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position save(Position position) {
        return PositionEntity.fromPositionEntityToPosition(positionRepository.save(PositionEntity.fromPositionToPositionEntity(position)));
    }

    @Override
    public boolean existById(Integer positionId) {
        return positionRepository.existsById(positionId);
    }

    @Override
    public Optional<Position> findById(Integer positionId) {
        return positionRepository.findById(positionId).map(PositionEntity::fromPositionEntityToPosition);
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll().stream().parallel()
                .map(PositionEntity::fromPositionEntityToPosition)
                .collect(Collectors.toList());
    }
}
