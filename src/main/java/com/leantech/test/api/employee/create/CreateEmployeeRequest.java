package com.leantech.test.api.employee.create;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create position API Request.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel(value = "Create employee request")
public class CreateEmployeeRequest {

    private double salary;
    private CreatePersonRequest person;
}
