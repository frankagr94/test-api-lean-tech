package com.leantech.test.api.employee.update;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create position API Request.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode@ApiModel(value = "Update employee request")
public class UpdateEmployeeRequest {

    private double salary;
    private UpdatePersonRequest person;
}
