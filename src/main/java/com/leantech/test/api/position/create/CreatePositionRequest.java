package com.leantech.test.api.position.create;

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
@ApiModel(value = "Create position request")
public class CreatePositionRequest {

    private String name;
}
