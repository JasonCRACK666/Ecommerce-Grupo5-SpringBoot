package com.group5.ecommerce.service.colors;

import com.group5.ecommerce.entity.Color;

import java.util.List;
import java.util.Optional;

public interface colorsService {

    public Color save(Color color);

    public Iterable<Color> findAll();

    public Optional<Color> findById(Long id);
    public void deleteById(Long Id);

    List<Color> getAllColor();
}
