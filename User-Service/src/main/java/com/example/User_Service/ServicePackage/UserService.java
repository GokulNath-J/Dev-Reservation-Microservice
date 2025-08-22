package com.example.User_Service.ServicePackage;

import com.example.User_Service.DTO.BookingRequest;
import com.example.User_Service.DTO.LoginStatus;
import com.example.User_Service.Entity.NewUserRegister;
import com.example.User_Service.Entity.UserLoginStatus;
import com.example.User_Service.OpenFeign.BookingFeign;
import com.example.User_Service.OpenFeign.PaymentFeign;

import com.example.User_Service.Repository.UserLoginStatusRepo;
import com.example.User_Service.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentFeign paymentFeign;

    @Autowired
    private BookingFeign bookingFeign;

    @Autowired
    private UserLoginStatusRepo userLoginStatusRepo;


    // Register new newUserRegister
    public String registerUser(NewUserRegister newUserRegister) {
        String value = String.valueOf(newUserRegister.getPhoneNumber());
        newUserRegister.setUserId(newUserRegister.getUserName() + value.substring(value.length() - 4));
        log.info("UserName from the PostMan:{}", newUserRegister.getUserName());
        log.info("PhoneNumber from the PostMan:{}", newUserRegister.getPhoneNumber());
//        log.info("EWalletDTO from PostMan:{}", newUserRegister.getEWallet());
        userRepository.save(newUserRegister);

        return "Success";

    }

    // Login user
    public boolean loginUser(String username, String password) {
        Optional<UserLoginStatus> loginStatus = userLoginStatusRepo.findByUserName(username);
        log.info("loginStatus:{}", loginStatus.isPresent());
        if (loginStatus.isPresent()){
            if (loginStatus.get().getLoggedInStatus().equals(LoginStatus.ACTIVE)){
                log.info("UserName:{} is Already LoggedIn", username);
                return true;
            }
        }else {
            log.info("First Else");
            Optional<NewUserRegister> user = userRepository.findByUserName(username);
            NewUserRegister newUserRegister = user.get();
            boolean result = user.isPresent() && newUserRegister.getPassword().equals(password);
            if (result) {
                log.info("Second If ");
                UserLoginStatus userLoginStatus = new UserLoginStatus
                        (newUserRegister.getUserId(), newUserRegister.getUserName(), LoginStatus.ACTIVE, LocalDateTime.now(), null);
                userLoginStatusRepo.save(userLoginStatus);
                return true;
            } else {
                log.info("Password incorrect");
                return false;
            }

//        if(loginStatus.isPresent()) {
//            if (loginStatus.get().getLoggedInStatus().equals(LoginStatus.ACTIVE)) {
//                log.info("First If ");
//                log.info("UserName:{} is Already LoggedIn", username);
//                return true;
//            } else {
//                log.info("First Else");
//                Optional<NewUserRegister> user = userRepository.findByUserName(username);
//                NewUserRegister newUserRegister = user.get();
//                boolean result = user.isPresent() && newUserRegister.getPassword().equals(password);
//                if (result) {
//                    log.info("Second If ");
//                    UserLoginStatus userLoginStatus = new UserLoginStatus
//                            (newUserRegister.getUserId(), newUserRegister.getUserName(), LoginStatus.ACTIVE, LocalDateTime.now(), null);
//                    userLoginStatusRepo.save(userLoginStatus);
//                    return true;
//                } else {
//                    log.info("Second Else");
//                    return false;
//                }
//            }
        }
        return false;
    }

    public String confirmBooking(BookingRequest request) {
        log.info("request in the UserService");
        if (checkUserIsActive(request.getUserName())){
            return bookingFeign.booking(request);
        }
        return "erro";
    }

    public boolean checkUserIsActive(String userName) {
        Optional<UserLoginStatus> loginStatus = userLoginStatusRepo.findByUserName(userName);
        if (loginStatus.get().getLoggedInStatus().equals(LoginStatus.ACTIVE)){
            return true;
        }
        return false;
    }

    public String createNewEWallet(String username, String password) {
        Optional<UserLoginStatus> loginStatus = userLoginStatusRepo.findByUserName(username);
        if (loginStatus.isPresent()){
            return paymentFeign.createNewEWallet(username,loginStatus.get().getUserId(),password);
        }else {
            return "User Not Found";
        }
    }

    public String addMoneyToEWallet(String username, double amount) {
        Optional<UserLoginStatus> loginStatus = userLoginStatusRepo.findByUserName(username);
        if (loginStatus.isPresent()) {
            return paymentFeign.addMoneyToEWallet(loginStatus.get().getUserId(),amount);
        }else {
            return "User Not Found";
        }
    }

//    public String addEWallet(String username, EWalletDTO eWallet){
//        Optional<NewUserRegister> user = userRepository.findByUserName(username);
//        user.get().setEWallet(eWallet);
//        eWalletRepo.save(eWallet);
//        return "Success";
//    }
}