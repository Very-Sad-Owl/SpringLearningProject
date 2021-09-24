package com.epam.practice.teashop_spring.controller;

import com.epam.practice.teashop_spring.domain.Tea;
import com.epam.practice.teashop_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductService productService;

    @Autowired
    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String mainMenu(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable, Model model) {
        Page<Tea> page = productService.findAll(pageable);
        List<String> brands = productService.getBrands();
        int[] pagination = ControllerUtils.computePagination(page);
        getMinMaxTeaPrice(model);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu");
        model.addAttribute("page", page);
        model.addAttribute("brands", brands);

        return "menu";
    }

    @GetMapping("{brand}")
    public String findByBrand(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @PathVariable String brand,
            Model model
    ) {
        Page<Tea> brandList = productService.findByBrand(brand, pageable);
        int[] pagination = ControllerUtils.computePagination(brandList);
        getMinMaxTeaPrice(model);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/" + brand);
        model.addAttribute("page", brandList);

        return "menu";
    }

    @GetMapping("search")
    public String searchByParameters(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @RequestParam(value = "brands", required = false, defaultValue = "") List<String> brands,
            @RequestParam(value = "startingPrice", required = false, defaultValue = "0") Integer startingPrice,
            @RequestParam(value = "endingPrice", required = false, defaultValue = "0") Integer endingPrice,
            Model model
    ) {
        StringBuilder urlBuilder = new StringBuilder();
        Page<Tea> brandsSearch = null;
        getMinMaxTeaPrice(model);

        if (brands.isEmpty()) {
            Page<Tea> priceRange = productService.findByPriceBetween(startingPrice, endingPrice, pageable);
            int[] pagination = ControllerUtils.computePagination(priceRange);

            model.addAttribute("pagination", pagination);
            model.addAttribute("url", "/menu/search?startingPrice=" + startingPrice + "&endingPrice=" + endingPrice);
            model.addAttribute("page", priceRange);

            return "menu";
        }

        int[] pagination = ControllerUtils.computePagination(brandsSearch);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu/search" + urlBuilder);
        model.addAttribute("page", brandsSearch);

        return "menu";
    }

    private void getMinMaxTeaPrice(Model model) {
        BigDecimal minTeaPrice = productService.minTeaPrice();
        BigDecimal maxTeaPrice = productService.maxTeaPrice();

        model.addAttribute("minTeaPrice", minTeaPrice);
        model.addAttribute("maxTeaPrice", maxTeaPrice);
    }
}