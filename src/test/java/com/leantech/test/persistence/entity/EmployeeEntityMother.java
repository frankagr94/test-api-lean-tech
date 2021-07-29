package com.leantech.test.persistence.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Employee Entity Mother
 */
public class EmployeeEntityMother {

    public static EmployeeEntity valid(){
        return EmployeeEntity.builder()
                .salary(3000)
                .person(PersonEntityMother.generate())
                .position(new PositionEntity(1,"dev", new HashSet<>()))
                .build();
    }

    public static Set<EmployeeEntity> generateEmployeeEntities(){
        var employees = new HashSet<EmployeeEntity>();
        employees.add(EmployeeEntityMother.valid());

        return employees;
    }

    public static List<EmployeeEntity> generateEmployeeEntitiesList(){
        var employees = new ArrayList<EmployeeEntity>();
        employees.add(EmployeeEntityMother.valid());

        return employees;
    }
}
