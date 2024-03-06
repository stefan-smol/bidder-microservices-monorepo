package com.bidder.auctionmanagementservice.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
public class Item {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String title;
    private String description;
    private AuctionType auctionType;
    private Date startDate;
    private Date endDate;
    private int duration;
    private String status;
    private Double startingPrice;
    private Double shippingPrice;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}