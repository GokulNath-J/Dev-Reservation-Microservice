package com.example.User_Service.OpenFeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Payment-Service")
public interface PaymentFeign {

    @PostMapping("payment/createNewEWallet")
    public String createNewEWallet(@RequestParam String username, @RequestParam String userId,@RequestParam String password);

    @PostMapping("payment/addMoneyToEWallet")
    public String addMoneyToEWallet(@RequestParam String userId,@RequestParam double amount);
}
