package com.bidder.auctionmanagementservice.Repository;


import com.bidder.auctionmanagementservice.Entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {

}