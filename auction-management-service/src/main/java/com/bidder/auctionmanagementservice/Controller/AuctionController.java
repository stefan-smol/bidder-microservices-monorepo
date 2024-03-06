package com.bidder.auctionmanagementservice.Controller;

import com.bidder.auctionmanagementservice.Entities.Auction;
import com.bidder.auctionmanagementservice.Service.AuctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auctionManagement")
public class AuctionController {


    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping
    public ResponseEntity<Auction> createAuction(@RequestBody Auction auction) {
        Auction createdAuction = auctionService.createAuction(auction);
        return new ResponseEntity<>(createdAuction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long id) {
        Auction auction = auctionService.getAuction(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction not found"));
        return ResponseEntity.ok(auction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auction> updateAuction(@PathVariable Long id, @RequestBody Auction auctionDetails) {
        Auction updatedAuction = auctionService.updateAuction(id, auctionDetails);
        return ResponseEntity.ok(updatedAuction);
    }

    @PatchMapping("/close/{id}")
    public ResponseEntity<Auction> closeAuction(@PathVariable Long id, @RequestParam float finalPrice) {
        Auction closedAuction = auctionService.closeAuction(id, finalPrice);
        return ResponseEntity.ok(closedAuction);
    }
}
