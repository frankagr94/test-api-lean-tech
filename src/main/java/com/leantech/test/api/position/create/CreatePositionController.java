package com.leantech.test.api.position.create;


import com.leantech.test.context.domain.Position;
import com.leantech.test.context.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create Position Controller
 */
@Api(tags = "positions")
@RestController
@Slf4j
public class CreatePositionController {

    private final IPositionService positionService;

    /**
     * Constructor
     *
     * @param positionService the position servide parameter
     */
    public CreatePositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @ApiOperation(value = "Create a position",
            notes = "By passing in the appropriate options, you can create a position ",
            nickname = "createPosition")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Position created", response = CreatePositionResponse.class),
            @ApiResponse(code = 409, message = "Position creation error", response = CreatePositionResponse.class)
    })
    @PostMapping(value = "positions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePositionResponse> createPosition(@RequestBody CreatePositionRequest createPositionRequest){
        log.info("CreatePositionController - POST - createPosition");

        var positionCreated = positionService.save(Position.fromCreatePositionRequestToPosition(createPositionRequest));

        if(positionCreated == null || positionCreated.getId() == null){
            return new ResponseEntity<>(CreatePositionResponse.builder()
                    .error(true)
                    .status(HttpStatus.CONFLICT.name())
                    .message("An error ocurred saving the position, please check the fields and trying again...")
                    .build(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(CreatePositionResponse.builder()
                .error(false)
                .status(HttpStatus.CREATED.name())
                .message("Position created succesfully")
                .build(), HttpStatus.CREATED);
    }
}
