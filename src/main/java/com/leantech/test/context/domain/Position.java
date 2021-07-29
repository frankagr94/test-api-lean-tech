package com.leantech.test.context.domain;

import com.leantech.test.api.position.create.CreatePositionRequest;
import com.leantech.test.persistence.entity.EmployeeEntity;
import com.leantech.test.persistence.entity.PositionEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that represents the position domain
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Position {

    private final Integer id;
    private final String name;
    private final List<Employee> employees;

    /**
     * Load method
     *
     * @param positionEntity the position entity parameter
     * @return a position domain class
     */
    public static Position load(PositionEntity positionEntity) {
        return new Position(positionEntity.getId(), positionEntity.getName(),
                positionEntity.getEmployees().stream().parallel()
                        .map(EmployeeEntity::fromEmployeeEntityToEmployee)
                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                        .collect(Collectors.toList()));
    }

    /**
     * From Create Position Request To Position
     *
     * @param createPositionRequest the create position request
     * @return a position
     */
    public static Position fromCreatePositionRequestToPosition(CreatePositionRequest createPositionRequest) {
        return new Position(null, createPositionRequest.getName(), new ArrayList<>());
    }
}
