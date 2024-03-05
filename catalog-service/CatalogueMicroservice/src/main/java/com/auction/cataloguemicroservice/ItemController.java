package com.auction.cataloguemicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/items")
    public class ItemController {
        @Autowired
        private ItemService itemService;

        @GetMapping
        public List<Item> getAllItems() {
            return itemService.readAll();
        }

        @GetMapping(value = "/{id}")
        public Item getItemById(@PathVariable int id) {
            return itemService.getItemById(id);
        }

//        @GetMapping(value = "/price-between")
//        public List<Item> findByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
//            return itemService.findByPriceBetween(minPrice, maxPrice);
//        }

        @GetMapping(value = "/price-less-than}")
        public List<Item> findItemsByStartingPriceLessThan(double maxPrice) {
            return itemService.findItemsByStartingPriceLessThan(maxPrice);
        }

        @GetMapping(value = "/status/{status}")
        public List<Item> findItemsByStatus(@PathVariable Item.ItemStatus status) {
            return itemService.findItemsByStatus(status);
        }

        @GetMapping(value = "/search")
        public List<Item> findByTitleIgnoreCase(@RequestParam String title) {
            return itemService.findByTitleIgnoreCase(title);
        }


        @PostMapping
        public void addItem(@RequestBody int id){
            itemService.addItem(id);
        }

        @PutMapping("/{id}")
        public void updateItem(@PathVariable int id, @RequestBody Item item) {
            itemService.updateItem(id, item);
        }

        @DeleteMapping("/{id}")
        public void deleteItem(@PathVariable int id) {
            itemService.deleteItem(id);
        }
    }
