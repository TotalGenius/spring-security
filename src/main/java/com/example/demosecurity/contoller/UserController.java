package com.example.demosecurity.contoller;

import com.example.demosecurity.entity.User;
import com.example.demosecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserInfo(Principal principal, Model model) {
        User user = userService.getByUserName(principal.getName()).get();
        model.addAttribute("user", user);
        return "user/info";
    }
}
