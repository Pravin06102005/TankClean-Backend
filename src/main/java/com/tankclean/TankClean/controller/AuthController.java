package com.tankclean.TankClean.controller;

import com.tankclean.TankClean.dto.LoginRequest;
import com.tankclean.TankClean.dto.RegisterRequest;
import com.tankclean.TankClean.entity.Users;
import com.tankclean.TankClean.security.JwtUtil;
import com.tankclean.TankClean.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Users register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/register-admin")
    public Users registerAdmin(@Valid @RequestBody RegisterRequest request) {
        return userService.registerAdmin(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            Users user = userService.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return jwtUtil.generateToken(user.getEmail(), user.getRole());
        }catch (Exception e) {
            return "Invalid Email or password";
        }


    }
}
