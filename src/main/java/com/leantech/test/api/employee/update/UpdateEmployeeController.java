package com.leantech.test.api.employee.update;

import com.leantech.test.context.domain.Employee;
import com.leantech.test.context.service.IEmployeeService;
import com.leantech.test.context.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Update Employee Controller
 */
@Api(tags = "employees")
@RestController
public class UpdateEmployeeController {

    private final IEmployeeService employeeService;
    private final IPositionService positionService;

    /**
     * Constructor
     *
     * @param employeeService the employee service parameter
     * @param positionService the position service parameter
     */
    public UpdateEmployeeController(IEmployeeService employeeService, IPositionService positionService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    /**
     * Update Employee
     *
     * @param positionId            the position id parameter
     * @param employeeId            the employee id parameter
     * @param updateEmployeeRequest the update employee request parameter
     * @return a response entity
     */
    @ApiOperation(value = "Update an employee",
            notes = "By passing in the appropriate options, you can update an employee and change to another position ",
            nickname = "updateEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee updated", response = UpdateEmployeeResponse.class),
            @ApiResponse(code = 404, message = "Position or Employee Not Found", response = UpdateEmployeeResponse.class),
            @ApiResponse(code = 409, message = "Employee update error", response = UpdateEmployeeResponse.class)
    })
    @PutMapping(value = "positions/{positionId}/employees/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateEmployeeResponse> updateEmployee(@PathVariable("positionId") Integer positionId,
                                                                 @PathVariable("employeeId") String employeeId,
                                                                 @RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        var positionOptional = positionService.findById(positionId);

        if (positionOptional.isEmpty()) {
            return new ResponseEntity<>(UpdateEmployeeResponse.builder()
                    .error(true)
                    .status(HttpStatus.NOT_FOUND.name())
                    .message(String.format("The position with ID %s is not found in data base...", positionId))
                    .build(), HttpStatus.NOT_FOUND);
        }

        var existEmployee = employeeService.existById(employeeId);

        if (!existEmployee) {
            return new ResponseEntity<>(UpdateEmployeeResponse.builder()
                    .error(true)
                    .status(HttpStatus.NOT_FOUND.name())
                    .message(String.format("The employee with ID %s is not found in data base...", employeeId))
                    .build(), HttpStatus.NOT_FOUND);
        }

        var employeeUpdated = employeeService.update(Employee.fromUpdateEmployeeRequestToEmployee(updateEmployeeRequest),
                employeeId, positionOptional.get());

        if (employeeUpdated == null) {
            return new ResponseEntity<>(UpdateEmployeeResponse.builder()
                    .error(true)
                    .status(HttpStatus.CONFLICT.name())
                    .message("An error ocurred trying to update the existing Employee, please check the fields and try again...")
                    .build(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(UpdateEmployeeResponse.builder()
                .error(false)
                .status(HttpStatus.OK.name())
                .message("Employee updated succesfully!")
                .data(updateEmployeeRequest)
                .build(), HttpStatus.OK);
    }

}
