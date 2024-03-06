package com.bidder.auctionmanagementservice.Entities;

import com.bidder.auctionmanagementservice.Entities.AuctionType;
import com.bidder.auctionmanagementservice.Entities.Item;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private float startingPrice;
    private boolean auctionClosed; //True or false
    private float endingPrice;

    private String Description;
    private String title;
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(String auctionType) {
        this.auctionType = AuctionType.valueOf(auctionType);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }
    public void closeAuction(float finalPrice) {
        if (!this.auctionClosed) {
            this.endingPrice = finalPrice;
            this.auctionClosed = true;
        }
    }
    public float getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(float startingPrice) {
        this.startingPrice = startingPrice;
    }

    public boolean isAuctionClosed() {
        return auctionClosed;
    }

    public void setAuctionClosed(boolean auctionClosed) {
        this.auctionClosed = auctionClosed;
    }

    public float getEndingPrice() {
        return endingPrice;
    }

    public void setEndingPrice(float endingPrice) {
        this.endingPrice = endingPrice;
    }


}

