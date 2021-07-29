package com.leantech.test.context.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leantech.test.api.employee.create.CreateEmployeeRequest;
import com.leantech.test.api.employee.update.UpdateEmployeeRequest;
import com.leantech.test.persistence.entity.EmployeeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class that represents the employee domain
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {

    private final String id;
    private final double salary;
    private final Person person;
    @JsonIgnore
    private Position position;

    /**
     * Custom Constructor
     *
     * @param id     the id parameter
     * @param salary the salary parameter
     * @param person the person parameter
     */
    public Employee(String id, double salary, Person person) {
        this.id = id;
        this.salary = salary;
        this.person = person;
    }

    /**
     * Load method
     *
     * @param employeeEntity the employee entity parameter
     * @return an employee domain class
     */
    public static Employee load(EmployeeEntity employeeEntity) {
        return new Employee(employeeEntity.getId(), employeeEntity.getSalary(),
                Person.fromPersonEntityToPerson(employeeEntity.getPerson()));
    }

    /**
     * From Create Employee Request To Employee
     *
     * @param createEmployeeRequest the create employee request parameter
     * @param position              the position parameter
     * @param person                the person parameter
     * @return an Employee
     */
    public static Employee fromCreateEmployeeRequestToEmployee(CreateEmployeeRequest createEmployeeRequest, Position position, Person person) {
        return new Employee(null, createEmployeeRequest.getSalary(), person, position);
    }

    /**
     * From Update Employee Request To Employee
     *
     * @param updateEmployeeRequest the update employee request parameter
     * @return an Employee
     */
    public static Employee fromUpdateEmployeeRequestToEmployee(UpdateEmployeeRequest updateEmployeeRequest) {
        return new Employee(null, updateEmployeeRequest.getSalary(),
                Person.fromUpdatePersonRequestToPerson(updateEmployeeRequest.getPerson()));
    }
}
