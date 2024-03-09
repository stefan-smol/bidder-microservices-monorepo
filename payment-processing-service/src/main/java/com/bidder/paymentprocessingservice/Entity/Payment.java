package com.bidder.paymentprocessingservice.Entity;


import jakarta.persistence.*;


@Entity
public class Payment {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String creditCard;

    @Column(nullable = false)
    private Boolean expressShipping;

    @Column(nullable = false)
    private Long auctionId;

    @Column(nullable = false)
    private Double amount;

    // Constructors, getters, and setters

    public Payment() {
    }

    public Payment(Long userId, String creditCard, Boolean expressShipping, Long auctionId, Double amount) {
        this.userId = userId;
        this.creditCard = creditCard;
        this.expressShipping = expressShipping;
        this.auctionId = auctionId;
        this.amount = amount; // Initialize amount
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Boolean getExpressShipping() {
        return expressShipping;
    }

    public void setExpressShipping(Boolean expressShipping) {
        this.expressShipping = expressShipping;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    // getters and setters including for amount
}
