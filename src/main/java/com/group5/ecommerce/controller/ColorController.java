package com.group5.ecommerce.controller;

import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.service.colors.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/colors")
public class ColorController {

    @Autowired
    private ColorServiceImp colorServiceImp;

    @GetMapping
    public ResponseEntity<SendListResponse<Color>> getAllColor(){
        SendListResponse<Color> color = new SendListResponse<>(this.colorServiceImp.getAllColor());
        return new ResponseEntity<>(color, HttpStatus.OK);
    }
}
