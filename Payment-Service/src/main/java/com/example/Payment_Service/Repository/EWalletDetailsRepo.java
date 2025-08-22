package com.example.Payment_Service.Repository;

import com.example.Payment_Service.Entity.EWalletDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EWalletDetailsRepo extends JpaRepository<EWalletDetails,Integer> {

    EWalletDetails findByUserId(String userId);

    EWalletDetails findByUserName(String userName);
}
