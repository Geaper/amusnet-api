package com.amusnet.controller;

import com.amusnet.dto.GameActivityRequest;
import com.amusnet.dto.GameActivityResponse;
import com.amusnet.mapper.GameActivityMapper;
import com.amusnet.model.CustomException;
import com.amusnet.service.GameActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity/game")
public class GameActivityController {

    @Autowired
    private GameActivityService gameActivityService;
    @Autowired
    private GameActivityMapper gameActivityMapper;

    @ApiOperation(value = "Create Game Activity")
    @PostMapping
    public ResponseEntity<GameActivityResponse> createGameActivity(@RequestBody GameActivityRequest request) throws CustomException {
        GameActivityResponse response = gameActivityService.createGameActivity(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
