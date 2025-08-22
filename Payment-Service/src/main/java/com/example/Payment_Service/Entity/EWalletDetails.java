package com.example.Payment_Service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EWalletDetails {

    @Id
    @SequenceGenerator(name = "ew",sequenceName = "eWalletSeq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "ew", strategy = GenerationType.SEQUENCE)
    private Integer sNo;
    private String eWalletNumber = UUID.randomUUID().toString().substring(0,14).replace("-","");
    private String userId;
    private String userName;
    private String password;
    private double amount;

    public EWalletDetails(String userName, String userId, String password) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }
}
