package com.bidder.notificationservice.Controller;

import com.bidder.notificationservice.DTO.NotificationDto;
import com.bidder.notificationservice.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribeToAuction(@RequestBody NotificationDto subscriptionDto) {
        notificationService.subscribeToAuction(subscriptionDto.getId(), subscriptionDto.getAuctionId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notify")
    public ResponseEntity<Void> notifyBidders(@RequestBody NotificationDto notificationDto) {
        notificationService.notifySubscribers(notificationDto.getAuctionId(), notificationDto.isWinner(), notificationDto.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/auctions/{auctionId}/close")
    public ResponseEntity<Void> closeAuction(@PathVariable Long auctionId) {
        notificationService.closeAuction(auctionId);
        return ResponseEntity.ok().build();
    }
}

