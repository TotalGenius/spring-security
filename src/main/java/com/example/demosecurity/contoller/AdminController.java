package com.example.demosecurity.contoller;

import com.example.demosecurity.entity.Role;
import com.example.demosecurity.entity.User;
import com.example.demosecurity.service.RoleService;
import com.example.demosecurity.service.UserService;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String getAdminPage( Model model) {
        model.addAttribute("users", userService.getAll());
        return "/admin/users";
    }

    @GetMapping("/admin/new")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);

        return "/admin/new";
    }

    @PostMapping("/admin/create")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "adminCheck", required = false) String admin,
                           @RequestParam(name = "userCheck", required = false) String usr) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            user.setRoles(roles);
        }
        if (admin != null) {
            System.out.println(admin);
            user.getRoles().add(roleService.getByRoleName(admin));
        }
        if (usr != null) {
            System.out.println(usr);
            user.getRoles().add(roleService.getByRoleName(usr));
        }
        user.setPassword(passwordEncoder.encode(user.getpassword()));
        userService.save(user);
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
