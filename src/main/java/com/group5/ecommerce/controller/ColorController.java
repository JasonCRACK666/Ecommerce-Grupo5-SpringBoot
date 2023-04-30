package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.color.CreateColorDto;
import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.service.colors.ColorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/colors")
public class ColorController {

    @Autowired
    private ColorServiceImpl colorService;

    @GetMapping
    public ResponseEntity<SendListResponse<Color>> getAllColor() {
        return new ResponseEntity<>(this.colorService.getAllColor(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Color> create(@RequestBody CreateColorDto colorData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colorService.save(colorData));
    }

    @DeleteMapping("/{colorId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long colorId) {
        return new ResponseEntity<>(this.colorService.deleteColor(colorId), HttpStatus.OK);
    }
}
