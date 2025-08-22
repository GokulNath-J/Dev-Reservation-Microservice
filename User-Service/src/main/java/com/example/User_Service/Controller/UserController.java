package com.example.User_Service.Controller;

import com.example.User_Service.DTO.BookingRequest;

import com.example.User_Service.Entity.NewUserRegister;
import com.example.User_Service.ServicePackage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Register new newUserRegister
    @PostMapping("/register")
    public String register(@RequestBody NewUserRegister newUserRegister) {
        return userService.registerUser(newUserRegister);
    }

    // Login user
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.loginUser(username, password);
        return success ? "Login Successful" : "Invalid Username or Password Incorrect or UserNotFound";
    }

    @PostMapping("/book")
    public String booking(@RequestBody BookingRequest request) {
        return userService.confirmBooking(request);
    }

    @PostMapping("/createNewEWallet")
    public String createNewEWallet(@RequestParam String username,@RequestParam String password){
        return userService.createNewEWallet(username,password);
    }

    @GetMapping("/gg")
    public String gen(){
        return UUID.randomUUID().toString().substring(0,14).replace("-","");
    }

    @PostMapping("/addMoneyToEWallet")
    public String addMoneyToEWallet(@RequestParam String username,@RequestParam double amount){
        return userService.addMoneyToEWallet(username,amount);
    }
}