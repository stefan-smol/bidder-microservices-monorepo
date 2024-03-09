package com.bidder.notificationservice.Service;

import com.bidder.notificationservice.Entities.AuctionSubscription;
import com.bidder.notificationservice.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void subscribeToAuction(Long userId, Long auctionId) {
        AuctionSubscription subscription = new AuctionSubscription(userId, auctionId);
        notificationRepository.save(subscription);
    }

    public void notifySubscribers(Long auctionId, boolean isWinner, Long winnerUserId) {
        List<AuctionSubscription> subscribers = notificationRepository.findAllByAuctionId(auctionId);
        subscribers.forEach(subscriber -> {
            if (subscriber.getUserId().equals(winnerUserId) && isWinner) {
                // Logic to send the winner notification to FRONTEND
            } else {
                // Logic to send the loser notification to FRONTEND
            }
        });
    }

    public void closeAuction(Long auctionId) {
        notificationRepository.deleteAllByAuctionId(auctionId);
    }
}
