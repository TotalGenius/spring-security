package com.example.demosecurity.contoller;

import com.example.demosecurity.entity.User;
import com.example.demosecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage() {

        return "index";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/admin/users";
    }

    @GetMapping("/admin/new")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "/admin/new";
    }

    @PostMapping("/admin/create")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "role", required = false) String roleSelect) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user, roleSelect);
        return "redirect:/admin";
    }

    @GetMapping("admin/delete")
    public String deleteUser(@ModelAttribute("userId") Long userId) {
        userService.delete(userId);
        return "redirect:/admin";
    }

    @GetMapping("admin/update")
    public String update(@ModelAttribute("userId") Long userId, Model model) {
        model.addAttribute("user", userService.get(userId));
        return "/admin/update";
    }

}
