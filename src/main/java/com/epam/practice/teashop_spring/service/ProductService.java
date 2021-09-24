package com.epam.practice.teashop_spring.service;

import com.epam.practice.teashop_spring.domain.Tea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;


public interface ProductService {

    List<Tea> findAll();

    Page<Tea> findAll(Pageable pageable);

    Page<Tea> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable);

    Page<Tea> findByBrand(String brand, Pageable pageable);

    Page<Tea> findByBrandOrTeaTitle(String brand, String teaTitle, Pageable pageable);

    Page<Tea> findByBrandIn (List<String> brands, Pageable pageable);

    BigDecimal minTeaPrice();

    BigDecimal maxTeaPrice();

    List<String> getBrands();

    void saveProductInfoById(String teaTitle, String brand, String country,
                             String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);

    Tea save(Tea tea);
}