package com.leantech.test.context.domain;

import com.leantech.test.persistence.entity.EmployeeEntityMother;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee Mother
 */
public class EmployeeMother {

    public static Employee valid(){
        return Employee.load(EmployeeEntityMother.valid());
    }

    public static List<Employee> generateList(){
        var employees = new ArrayList<Employee>();
        employees.add(EmployeeMother.valid());

        return employees;
    }
}
