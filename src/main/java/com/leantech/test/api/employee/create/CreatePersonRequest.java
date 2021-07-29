package com.leantech.test.api.employee.create;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create person API Request.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class CreatePersonRequest {

    private String name;
    private String lastName;
    private String address;
    private String cellphone;
    private String cityName;
}
