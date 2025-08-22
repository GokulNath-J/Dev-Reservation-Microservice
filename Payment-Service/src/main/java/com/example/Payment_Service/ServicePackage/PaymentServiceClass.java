package com.example.Payment_Service.ServicePackage;


import com.example.Payment_Service.Entity.EWalletDetails;
import com.example.Payment_Service.Repository.EWalletDetailsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PaymentServiceClass {


    private final Logger logger = LoggerFactory.getLogger(PaymentServiceClass.class);


    @Autowired
    private EWalletDetailsRepo eWalletDetailsRepo;

//    private EWalletDetailsRepo eWalletDetailsRepo;
//
//    public String createNewEWallet(EWallet eWallet) {
//        EWalletDetails eWalletDetails = new EWalletDetails(eWallet.getEWalletNumber()
//                ,eWallet.getUserName(),eWallet.getPassword(),eWallet.getAmount());
//        eWalletDetailsRepo.save(eWalletDetails);
//        return "EWallet is Sucessfully Added";
//    }



    public String createNewEWallet(String username, String userId, String password) {
        EWalletDetails eWalletDetails = new EWalletDetails(username,userId,password);
        eWalletDetailsRepo.save(eWalletDetails);
        return "EWallet Created";
    }

    public String addMoneyToEWallet(String userId, double amount) {
        EWalletDetails walletDetails = eWalletDetailsRepo.findByUserId(userId);
        if (walletDetails != null){
            walletDetails.setAmount(amount);
            eWalletDetailsRepo.save(walletDetails);
            return "Amount Added Successfully";
        }else {
            return "Wallet Not Found";
        }
    }

    public String paymentRequest(String userName, double totalTicketAmount) {
        logger.info("username:{},totalTicketAmount:{}",userName,totalTicketAmount);
        EWalletDetails eWalletDetails = eWalletDetailsRepo.findByUserName(userName);
        logger.info("eWalletDetails:{}",eWalletDetails);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Password:");
        String password = scanner.nextLine();
        if (eWalletDetails.getPassword().equals(password)){
            logger.info("Password correct");
            double eWalletAmount = eWalletDetails.getAmount();
            if (totalTicketAmount <= eWalletAmount){
                eWalletDetails.setAmount(eWalletAmount-totalTicketAmount);
                eWalletDetailsRepo.save(eWalletDetails);
                return "Payment Success";
            }else {
                logger.info("Insuffient Amount:{}",totalTicketAmount);
                System.out.println("Insuffient Amount");
            }
        }else {
            logger.info("Password Incorrect:{}",password);
        }
        return "Payment Failed";
    }
}

