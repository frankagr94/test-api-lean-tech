package com.leantech.test.api.employee.find;

import com.leantech.test.context.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Find Employee Controller
 */
@Api(tags = "employees")
@RestController
public class FindEmployeeController {

    private final IEmployeeService employeeService;

    /**
     * Constructor
     *
     * @param employeeService the employee service parameter
     */
    public FindEmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Find All Employees
     *
     * @param position the position parameter
     * @param name     the name parameter
     * @return a response entity
     */
    @ApiOperation(value = "Find all employees",
            notes = "By passing in the appropriate parameters, you can find one or more employees ",
            nickname = "findAllEmployees")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employees found", response = List.class)
    })
    @GetMapping(value = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeResponse>> findAllEmployees(@RequestParam(value = "position", required = false) String position,
                                                                   @RequestParam(value = "name", required = false) String name) {
        List<EmployeeResponse> employeeResponses;

        if ((position == null || position.trim().isEmpty()) || (name == null || name.trim().isEmpty())) {
            employeeResponses = employeeService.findAll().stream().parallel()
                    .map(EmployeeResponse::employeeToEmployeeResponse)
                    .collect(Collectors.toList());
        } else {
            employeeResponses = employeeService.findByParams(position, name).stream().parallel()
                    .map(EmployeeResponse::employeeToEmployeeResponse)
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(employeeResponses, HttpStatus.OK);
    }
}
