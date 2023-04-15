package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.PingPongDto;
import com.group5.ecommerce.response.PongResponse;

import com.group5.ecommerce.service.base.BaseServiceImp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/base")
public class BaseController {
    @Autowired
    private BaseServiceImp baseService;

    @GetMapping(path = "ping")
    private ResponseEntity<PongResponse> pingPong() {
        return new ResponseEntity<>(this.baseService.getPong(), HttpStatus.OK);
    }

    @PostMapping(path = "ping-message")
    private ResponseEntity<PongResponse> pingPongMessage(
            @Valid @RequestBody PingPongDto pingPongDto
    ) {
        return new ResponseEntity<>(
                this.baseService.sendMessage(pingPongDto.getMessage()),
                HttpStatus.OK
        );
    }
}
