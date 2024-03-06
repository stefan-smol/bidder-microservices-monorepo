package com.bidder.auctionmanagementservice.Service;



import com.bidder.auctionmanagementservice.Repository.AuctionRepository;
import com.bidder.auctionmanagementservice.Entities.Auction;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Async
    public Auction createAuction(Auction auction) {
        return auctionRepository.save(auction);
    }

    public Optional<Auction> getAuction(Long id) {
        return auctionRepository.findById(id);
    }

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public Auction updateAuction(Long id, Auction auctionDetails) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auction not found with id " + id));
        // Update auction with details from auctionDetails
        auction.setTitle(auctionDetails.getTitle());
        auction.setDescription(auctionDetails.getDescription());
        auction.setStartTime(auctionDetails.getStartTime());
        auction.setEndTime(auctionDetails.getEndTime());
        auction.setStartingPrice(auctionDetails.getStartingPrice());
        auction.setAuctionType(auctionDetails.getAuctionType());

        return auctionRepository.save(auction);
    }

    public void deleteAuction(Long id) {
        auctionRepository.deleteById(id);
    }

    public Auction closeAuction(Long id, float finalPrice) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auction not found with id " + id));
        auction.closeAuction(finalPrice);
        return auctionRepository.save(auction);
    }


}

