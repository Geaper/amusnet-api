package com.amusnet.controller;

import com.amusnet.dto.PlayerActivityResponse;
import com.amusnet.service.PlayerActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity/player")
public class PlayerActivityController {

    @Autowired
    private PlayerActivityService playerActivityService;

    @ApiOperation(value = "Get Player Game Activity")
    @GetMapping("/{playerId}")
    public ResponseEntity<Page<PlayerActivityResponse>> getPlayerActivity(@PathVariable String playerId, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending());
        Page<PlayerActivityResponse> response = playerActivityService.getPlayerActivity(playerId, pageable);
        return ResponseEntity.ok(response);
    }
}
