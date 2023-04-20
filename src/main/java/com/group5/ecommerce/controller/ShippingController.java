package com.group5.ecommerce.controller;

import com.group5.ecommerce.entity.Shipping;
import com.group5.ecommerce.response.SendListResponse;
import com.group5.ecommerce.service.shipping.ShippingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/shippings")
public class ShippingController {

    @Autowired
    private ShippingServiceImp shippingService;

    @GetMapping
    public ResponseEntity<SendListResponse<Shipping>> getAllShippings() {
        SendListResponse<Shipping> shippings = new SendListResponse<>(this.shippingService.getAllShippings());
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

}
