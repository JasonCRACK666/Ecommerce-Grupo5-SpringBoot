package com.group5.ecommerce.service.colors;

import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColorServiceImp implements  colorsService{
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> getAllColor() { return this.colorRepository.findAll(); }
}
