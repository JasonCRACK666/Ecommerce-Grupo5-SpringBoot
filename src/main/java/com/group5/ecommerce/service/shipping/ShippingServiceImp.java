package com.group5.ecommerce.service.shipping;

import com.group5.ecommerce.entity.Shipping;
import com.group5.ecommerce.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImp implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public List<Shipping> getAllShippings() {
        return this.shippingRepository.findAll();
    }
}
