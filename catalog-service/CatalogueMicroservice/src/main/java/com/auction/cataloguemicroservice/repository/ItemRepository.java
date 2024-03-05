package com.auction.cataloguemicroservice.repository;
import com.auction.cataloguemicroservice.Category;
import com.auction.cataloguemicroservice.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findItemsByCategory(Category category);
    List<Item> findItemsByAuctionTypeIgnoreCase(String auctionType);
    List<Item> findByPriceLessThan(Double maxPrice);

    List<Item> findByStartingPriceBetween(Double minPrice, Double maxPrice);

    List<Item> findByTitleIgnoreCase(String title);

    List<Item> findByTitleContainingIgnoreCase(String keyword);

    List<Item> findByCategoryId(Long categoryId);

    List<Item> findByAuctionType(Item.AuctionType auctionType);

    List<Item> findByStatus(Item.ItemStatus status);}
