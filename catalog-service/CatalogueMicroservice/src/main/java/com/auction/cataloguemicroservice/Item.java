package com.auction.cataloguemicroservice;

import jakarta.persistence.*;
import java.util.Date;

@Table(name="items")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Double startingPrice;
    private Double shippingPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Item() {

    }

    public Item(int id, String title, String description, Double startingPrice) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startingPrice = startingPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public enum AuctionType {
        FORWARD, DUTCH
    }

    // Enum for ItemStatus
    public enum ItemStatus {
        SOLD, IN_PROGRESS, SCHEDULED
    }
}
