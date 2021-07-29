package com.leantech.test.api.position.find;

import com.leantech.test.context.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Find Position Controller
 */
@Api(tags = "positions")
@RestController
@Slf4j
public class FindPositionController {

    private final IPositionService positionService;

    /**
     * Constructor
     *
     * @param positionService the position servide parameter
     */
    public FindPositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @ApiOperation(value = "Find all positions",
            notes = "You can find all the positions saved in data base ",
            nickname = "findAllPositions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Positions found", response = ResponseEntity.class)
    })
    @GetMapping(value = "positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PositionResponse>> findAllPositions(){
        log.info("FindPostionController - GET - findAllPositions");
        var positionResponses = positionService.findAll().stream().parallel()
                .map(PositionResponse::positionToPositionResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(positionResponses, HttpStatus.OK);
    }
}
