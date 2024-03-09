package com.bidder.paymentprocessingservice.Repository;

import com.bidder.paymentprocessingservice.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByUserId(Long userId);
    List<Payment> findByAuctionId(Long auctionId);
}
