package com.leantech.test.persistence.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Position Entity Mother
 */
public class PositionEntityMother {

    public static PositionEntity generate(){
        return PositionEntity.builder()
                .id(1)
                .name("dev")
                .employees(EmployeeEntityMother.generateEmployeeEntities())
                .build();
    }

    public static PositionEntity generateSecond(){
        return PositionEntity.builder()
                .id(2)
                .name("qa")
                .employees(new HashSet<>())
                .build();
    }

    public static List<PositionEntity> generateList(){
        var positions = new ArrayList<PositionEntity>();

        positions.add(PositionEntityMother.generate());
        positions.add(PositionEntityMother.generateSecond());

        return positions;
    }
}
