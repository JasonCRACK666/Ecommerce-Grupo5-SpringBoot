package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.color.CreateColorDto;
import com.group5.ecommerce.dto.color.UpdateColorDto;
import com.group5.ecommerce.response.MessageResponse;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.response.color.ColorResponse;
import com.group5.ecommerce.response.color.DetailColorResponse;

import com.group5.ecommerce.service.colors.ColorService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/colors")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<SendListResponse<ColorResponse>> getAllColor() {
        return new ResponseEntity<>(this.colorService.getAllColor(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ColorResponse> create(@RequestBody CreateColorDto colorData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colorService.save(colorData));
    }

    @DeleteMapping("/{colorId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long colorId) {
        return new ResponseEntity<>(this.colorService.deleteColor(colorId), HttpStatus.OK);
    }

    @PutMapping(path = "/{colorId}")
    public ResponseEntity<DetailColorResponse> updateColor(
            @PathVariable("colorId") Long colorId,
            @Valid @RequestBody UpdateColorDto colorData
    ) {
        return new ResponseEntity<>(this.colorService.updateColor(colorId, colorData), HttpStatus.OK);
    }

}
