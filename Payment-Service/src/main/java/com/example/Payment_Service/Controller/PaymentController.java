package com.example.Payment_Service.Controller;


import com.example.Payment_Service.DTO.PaymentRequest;
import com.example.Payment_Service.ServicePackage.PaymentServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    public PaymentServiceClass paymentServiceClass;

    @PostMapping("/createNewEWallet")
    public String createNewEWallet(@RequestParam String username,@RequestParam String userId, @RequestParam String password){
        return paymentServiceClass.createNewEWallet(username,userId,password);
    }

    @PostMapping("/addMoneyToEWallet")
    public String addMoneyToEWallet(@RequestParam String userId,@RequestParam double amount){
        return paymentServiceClass.addMoneyToEWallet(userId,amount);
    }
//    @PostMapping("/paymentRequest")
//    public boolean paymentRequest(@RequestBody PaymentRequest paymentRequest){
//       return paymentServiceClass.paymentRequest(paymentRequest);
//    }
    @PostMapping("/paymentRequest")
    public String paymentRequest(@RequestParam String userName,@RequestParam double totalTicketAmount){
        return paymentServiceClass.paymentRequest(userName,totalTicketAmount);
    }
}
