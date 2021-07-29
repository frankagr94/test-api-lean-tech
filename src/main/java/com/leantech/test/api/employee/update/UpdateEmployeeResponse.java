package com.leantech.test.api.employee.update;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Create position API Response.
 */
@AllArgsConstructor
@Getter
@Builder@ApiModel(value = "Update employee response")
public class UpdateEmployeeResponse {

    private final boolean error;
    private final String status;
    private final String message;
    private final Object data;
}
