package com.tankclean.TankClean.controller;

import com.tankclean.TankClean.dto.RegisterRequest;
import com.tankclean.TankClean.entity.Users;
import com.tankclean.TankClean.security.SecurityUtil;
import com.tankclean.TankClean.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id:\\d+}")
    public Users getUserById(@PathVariable Long id){
            return userService.findByID(id);
    }

    @GetMapping("/me")
    public Users getMyDetails() {
        return userService.findByEmail(SecurityUtil.getCurrentUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public Users addUser(@Valid @RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PutMapping("/me")
    public Users updateMyDetails(@RequestBody Users user) {
        return userService.updateCurrentUser(SecurityUtil.getCurrentUserEmail(), user);
    }

    @PutMapping("/{id:\\d+}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user){
        return userService.Update(id, user);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }


}
