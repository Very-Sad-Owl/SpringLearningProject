package com.epam.practice.teashop_spring.controller;

import com.epam.practice.teashop_spring.domain.Tea;
import com.epam.practice.teashop_spring.domain.User;
import com.epam.practice.teashop_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final UserService userService;

    @Autowired
    public CartController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String getCart(@AuthenticationPrincipal User userSession, Model model) {
        User userFromDB = userService.findByUsername(userSession.getUsername());
        model.addAttribute("tea", userFromDB.getTeaList());

        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("add") Tea tea,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());
        user.getTeaList().add(tea);
        userService.save(user);

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(
            @RequestParam(value = "teaId") Tea tea,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.findByUsername(userSession.getUsername());

        if (tea != null) {
            user.getTeaList().remove(tea);
        }

        userService.save(user);

        return "redirect:/cart";
    }
}