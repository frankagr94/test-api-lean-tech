package com.leantech.test.persistence.implementation;

import com.leantech.test.context.domain.PositionMother;
import com.leantech.test.context.service.IPositionService;
import com.leantech.test.persistence.entity.PositionEntity;
import com.leantech.test.persistence.entity.PositionEntityMother;
import com.leantech.test.persistence.repository.IPositionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Position Service Implementation Tests
 */
class PositionServiceImplementationTests {

    private final IPositionService positionService;

    @Captor
    private ArgumentCaptor<PositionEntity> positionEntityArgumentCaptor;

    @Mock
    private IPositionRepository positionRepository;

    PositionServiceImplementationTests() {
        MockitoAnnotations.openMocks(this);
        this.positionService = new PositionServiceImplementation(positionRepository);
    }

    @Test
    void Save_Position(){
        var position = PositionMother.valid();
        var positionEntity = PositionEntityMother.generate();

        when(positionRepository.save(Mockito.any(PositionEntity.class))).thenReturn(positionEntity);
        positionService.save(position);
        verify(positionRepository, times(1)).save(positionEntityArgumentCaptor.capture());

        var response = positionEntityArgumentCaptor.getValue();

        assertThat(response.getName()).isEqualTo(position.getName());
    }

    @Test
    void Exist_By_Id(){
        var positionId = 1;

        when(positionRepository.existsById(positionId)).thenReturn(Boolean.TRUE);
        var result = positionService.existById(positionId);
        verify(positionRepository, times(1)).existsById(positionId);

        assertTrue(result);
    }

    @Test
    void Find_By_Id(){
        var positionId = 1;
        var positionName = "dev";
        var positionEntity = PositionEntityMother.generate();

        when(positionRepository.findById(positionId)).thenReturn(Optional.of(positionEntity));
        var result = positionService.findById(positionId);
        verify(positionRepository,times(1)).findById(positionId);

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(positionId);
        assertThat(result.get().getName()).isEqualTo(positionName);
    }

    @Test
    void Find_All(){
        var positionEntities = PositionEntityMother.generateList();

        when(positionRepository.findAll()).thenReturn(positionEntities);
        var result = positionService.findAll();
        verify(positionRepository,times(1)).findAll();

        assertNotNull(result);
        assertThat(result).isNotEmpty();
    }
}
