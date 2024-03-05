package com.auction.cataloguemicroservice;



import com.auction.cataloguemicroservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public List<Item> readAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByTitleIgnoreCase(String title) {
        return itemRepository.findByTitleIgnoreCase(title);
    }

    public List<Item> searchItems(String keyword) {
        return itemRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Item> filterItems(Long categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    public List<Item> findItemsByStatus(Item.ItemStatus status) {
        return itemRepository.findByStatus(status);
    }

    public List<Item> findItemsByStartingPriceLessThan(Double maxPrice) {
        List<Item> items = itemRepository.findByPriceLessThan(maxPrice);
        return items.stream()
                .filter(item -> item.getStartingPrice() < maxPrice)
                .collect(Collectors.toList());

    }

    // Method to create a new item
    public Item createItem(Item item) {
        // Add any validation or business logic here
        return itemRepository.save(item);
    }

    // Method to update an existing item
    public Item updateItem(int id, Item item) {
        // Check if the item exists
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return itemRepository.save(item);
        }
        return null;
    }

    // Method to delete an item by ID
    public void deleteItem(int id) {
        // Check if the item exists
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);

        }
    }

    public void addItem(int id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        }
    }

//    public List<Item> findByPriceBetween(Double minPrice, Double maxPrice) {
//        return itemRepository.findByStartingPriceBetween(minPrice, maxPrice);
//
//    }

    public Item getItemById(int id) {
        return itemRepository.findById(id).orElse(null);
    }
}
