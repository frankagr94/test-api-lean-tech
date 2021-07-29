package com.leantech.test.api.employee.delete;

import com.leantech.test.context.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Delete Employee Controller
 */
@Api(tags = "employees")
@RestController
public class DeleteEmployeeController {

    private final IEmployeeService employeeService;

    /**
     * Constructor
     *
     * @param employeeService the employee service parameter
     */
    public DeleteEmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Delete Employee
     *
     * @param employeeId the employee id parameter
     * @return a response entity
     */
    @ApiOperation(value = "Delete an employee",
            notes = "By passing in the appropriate ID, you can delete an Employee ",
            nickname = "deleteEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee deleted", response = DeleteEmployeeResponse.class),
            @ApiResponse(code = 404, message = "Employee Not Found", response = DeleteEmployeeResponse.class)
    })
    @DeleteMapping(value = "employees/{employeeId}")
    public ResponseEntity<DeleteEmployeeResponse> deleteEmployee(@PathVariable("employeeId") String employeeId) {
        var existEmployee = employeeService.existById(employeeId);

        if (!existEmployee) {
            return new ResponseEntity<>(DeleteEmployeeResponse.builder()
                    .error(true)
                    .status(HttpStatus.NOT_FOUND.name())
                    .message(String.format("The employee with ID %s is not found in data base...", employeeId))
                    .build(), HttpStatus.NOT_FOUND);
        }

        employeeService.deleteById(employeeId);

        return new ResponseEntity<>(DeleteEmployeeResponse.builder()
                .error(false)
                .status(HttpStatus.OK.name())
                .message("Employee deleted succesfully!")
                .build(), HttpStatus.OK);
    }
}
