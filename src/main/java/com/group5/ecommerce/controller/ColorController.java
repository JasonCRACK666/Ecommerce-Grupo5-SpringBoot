package com.group5.ecommerce.controller;

import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.service.colors.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Color color){
        return ResponseEntity.status(HttpStatus.CREATED).body(colorServiceImp.save(color));
    }
    @GetMapping("/{id}")
    public  ResponseEntity<?> read(@PathVariable Long id){
        Optional<Color> colors= colorServiceImp.findById(id);
        if(!colors.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!colorServiceImp.findById(id).isPresent()){
            return  ResponseEntity.notFound().build();
        }
        colorServiceImp.deleteById(id);
        return  ResponseEntity.ok().build();
    }
}
