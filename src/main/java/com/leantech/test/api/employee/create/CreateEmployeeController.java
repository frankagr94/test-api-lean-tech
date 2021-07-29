package com.leantech.test.api.employee.create;

import com.leantech.test.context.domain.Employee;
import com.leantech.test.context.domain.Person;
import com.leantech.test.context.service.IEmployeeService;
import com.leantech.test.context.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create Employee Controller
 */
@Api(tags = "employees")
@RestController
@Slf4j
public class CreateEmployeeController {

    private final IEmployeeService employeeService;
    private final IPositionService positionService;

    /**
     * Constructor
     *
     * @param employeeService the employee service parameter
     * @param positionService the position service parameter
     */
    public CreateEmployeeController(IEmployeeService employeeService, IPositionService positionService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    /**
     * Create Employee
     *
     * @param positionId            the position id parameter
     * @param createEmployeeRequest the create employee request parameter
     * @return a response entity
     */
    @ApiOperation(value = "Create an employee",
            notes = "By passing in the appropriate options, you can create an employee and assign to an existing position ",
            nickname = "createEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Employee created", response = CreateEmployeeResponse.class),
            @ApiResponse(code = 404, message = "Position Not Found", response = CreateEmployeeResponse.class),
            @ApiResponse(code = 409, message = "Employee creation error", response = CreateEmployeeResponse.class)
    })
    @PostMapping(value = "positions/{positionId}/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateEmployeeResponse> createEmployee(@PathVariable("positionId") Integer positionId,
                                                                 @RequestBody CreateEmployeeRequest createEmployeeRequest) {
        log.info("CreateEmployeeController - POST - createEmployee");

        var positionOptional = positionService.findById(positionId);

        if (positionOptional.isEmpty()) {
            log.info("CreateEmployeeController - POST - createEmployee");
            return new ResponseEntity<>(CreateEmployeeResponse.builder()
                    .error(true)
                    .status(HttpStatus.NOT_FOUND.name())
                    .message(String.format("The position with ID %s is not found in data base...", positionId))
                    .build(), HttpStatus.NOT_FOUND);
        }

        var employeeCreated = employeeService.save(Employee.fromCreateEmployeeRequestToEmployee(createEmployeeRequest,
                positionOptional.get(), Person.fromCreatePersonRequestToPerson(createEmployeeRequest.getPerson())));

        if (employeeCreated == null || employeeCreated.getId() == null) {
            return new ResponseEntity<>(CreateEmployeeResponse.builder()
                    .error(true)
                    .status(HttpStatus.CONFLICT.name())
                    .message("An error ocurred trying to save the new Employee, please check the fields and try again...")
                    .build(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(CreateEmployeeResponse.builder()
                .error(false)
                .status(HttpStatus.CREATED.name())
                .message("Employee created succesfully!")
                .data(employeeCreated)
                .build(), HttpStatus.CREATED);
    }
}
