package com.leantech.test.api.position.find;

import com.leantech.test.context.domain.Employee;
import com.leantech.test.context.domain.Position;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Find position response
 */
@AllArgsConstructor
@Getter
@Builder
@ApiModel(value = "Find position response")
public class PositionResponse {

    private final Integer id;
    private final String name;
    private final List<EmployeeResponse> employees;

    /**
     * Position to position response method
     *
     * @param position the position parameter
     * @return a Position Response List
     */
    public static PositionResponse positionToPositionResponse(Position position) {
        return PositionResponse.builder()
                .id(position.getId())
                .name(position.getName())
                .employees(position.getEmployees().stream().parallel()
                        .map(EmployeeResponse::employeeToEmployeeResponse)
                        .sorted(Comparator.comparingDouble(EmployeeResponse::getSalary).reversed())
                        .collect(Collectors.toList()))
                .build();
    }
}
