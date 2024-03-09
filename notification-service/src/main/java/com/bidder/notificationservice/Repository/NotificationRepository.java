package com.bidder.notificationservice.Repository;

import com.bidder.notificationservice.Entities.AuctionSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<AuctionSubscription,Long> {
    List<AuctionSubscription> findAllByAuctionId(Long auctionId);
    void deleteAllByAuctionId(Long auctionId);
}
