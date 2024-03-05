package com.auction.cataloguemicroservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String title;

    public Category(int categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    }

    public Category() {
    }

    public int getCategoryIdId() {
        return categoryId;
    }

        public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

        public String getTitle() {
        return title;
    }

        public void setTitle(String title) {
        this.title = title;
    }

}
