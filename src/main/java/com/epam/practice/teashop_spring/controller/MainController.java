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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Tea> teas = productService.findAll();
        model.addAttribute("brand", teas);

        return "main";
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }

    @GetMapping("/cabinet")
    public String userCabinet() {
        return "user/userCabinet";
    }

    @GetMapping("/search")
    public String search(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 12) Pageable pageable,
            @RequestParam String filter,
            Model model
    ) {
        Page<Tea> page = productService.findByBrandOrTeaTitle(filter, filter, pageable);
        int[] pagination = ControllerUtils.computePagination(page);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/menu");
        model.addAttribute("page", page);

        return "menu";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Tea tea, Model model) {
        model.addAttribute("tea", tea);

        return "product";
    }
}