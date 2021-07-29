package com.leantech.test.api.position.create;

import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * Create position API Response.
 */
@AllArgsConstructor
@Getter
@Builder
@ApiModel(value = "Create position response")
public class CreatePositionResponse {

    private final boolean error;
    private final String status;
    private final String message;
    private final Object data;
}
