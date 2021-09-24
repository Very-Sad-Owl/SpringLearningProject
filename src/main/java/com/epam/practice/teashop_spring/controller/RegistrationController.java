package com.epam.practice.teashop_spring.controller;

import com.epam.practice.teashop_spring.domain.User;
import com.epam.practice.teashop_spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean isPasswordDifferent = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Подтверждение пароля не может быть пустым");
        }

        if (isPasswordDifferent) {
            model.addAttribute("passwordError", "Пароли не совпадают");
        }

        if (isConfirmEmpty || isPasswordDifferent || bindingResult.hasErrors()
        ) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        try {
            if (!userService.addUser(user)) {
                model.addAttribute("usernameError", "Пользователь существует!");
                return "registration";
            }
        } catch (Exception e){
//            userService.deleteUser(user);
            model.addAttribute("passwordError", "Код не отправлен");
            return "registration";
        }

        log.debug("User {} registered", user.getUsername());

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activateEmailCode(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "alert-success");
            model.addAttribute("message", "Пользователь успешно активирован");

        } else {
            model.addAttribute("messageType", "alert-danger");
            model.addAttribute("message", "Код активации не найден");

            log.error("Cant find activation code.");
        }

        return "login";
    }
}