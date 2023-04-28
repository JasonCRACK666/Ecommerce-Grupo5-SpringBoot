package com.group5.ecommerce.service.colors;

import com.group5.ecommerce.entity.Color;
import com.group5.ecommerce.repository.ColorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImp implements  colorsService{

    @Autowired
    private ColorRepository colorRepository;

    @Override
    @Transactional
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    @Transactional
    public Iterable<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Color> findById(Long id) {
        return colorRepository.findById(id);
    }

    public void deleteById(Long Id){
        colorRepository.deleteById(Id);
    }
    @Override
    public List<Color> getAllColor() { return this.colorRepository.findAll(); }
}
