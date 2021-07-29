package com.leantech.test.api.position.find;


import com.leantech.test.context.domain.Employee;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Employee response
 */
@AllArgsConstructor
@Getter
@Builder
public class EmployeeResponse {

    private final String id;
    private final double salary;
    private final PersonResponse person;

    /**
     * Employee To Employee Response
     *
     * @param employee the employee parameter
     * @return an Employee Response
     */
    public static EmployeeResponse employeeToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .salary(employee.getSalary())
                .person(PersonResponse.personToPersonResponse(employee.getPerson()))
                .build();
    }
}
