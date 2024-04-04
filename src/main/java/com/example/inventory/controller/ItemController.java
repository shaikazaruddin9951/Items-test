package com.example.inventory.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.inventory.model.Item;
import com.example.inventory.service.ItemJpaService;

@RestController
public class ItemController {
    @Autowired
    public ItemJpaService itemService;

    @GetMapping("/items")
    public ArrayList<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/items/{itemId}")
    public Item getItemById(@PathVariable("itemId") int itemId) {
        return itemService.getItemById(itemId);
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PutMapping("/items/{itemId}")
    public Item updateItem(@PathVariable("itemId") int itemId, @RequestBody Item item) {
        return itemService.updateItem(itemId, item);
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable("itemId") int itemId) {
        itemService.deleteItem(itemId);
    }
}