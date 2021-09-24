package com.epam.practice.teashop_spring.service.impl;

import com.epam.practice.teashop_spring.domain.Tea;
import com.epam.practice.teashop_spring.repos.ProductRepository;
import com.epam.practice.teashop_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Tea> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Tea> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Tea> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(startingPrice, endingPrice, pageable);
    }

    @Override
    public Page<Tea> findByBrand(String perfumer, Pageable pageable) {
        return productRepository.findByBrand(perfumer, pageable);
    }

    @Override
    public Page<Tea> findByBrandOrTeaTitle(String perfumer, String perfumeTitle, Pageable pageable) {
        return productRepository.findByBrandOrTeaTitle(perfumer, perfumeTitle, pageable);
    }

    @Override
    public Page<Tea> findByBrandIn(List<String> perfumers, Pageable pageable) {
        return productRepository.findByBrandIn(perfumers, pageable);
    }
    @Override
    public BigDecimal minTeaPrice() {
        return productRepository.minTeaPrice();
    }
    @Override
    public BigDecimal maxTeaPrice() {
        return productRepository.maxTeaPrice();
    }

    @Override
    public List<String> getBrands() {
        return productRepository.getBrands();
    }


    @Override
    public void saveProductInfoById(String perfumeTitle, String perfumer, String country,
                                    String fragranceBaseNotes, String description, String filename,
                                    Integer price, String volume, String type, Long id
    ) {
        productRepository.saveProductInfoById(perfumeTitle, perfumer, country,
                fragranceBaseNotes, description, filename, price, volume, type, id);
    }

    @Override
    public Tea save(Tea tea) {
        return productRepository.save(tea);
    }
}
