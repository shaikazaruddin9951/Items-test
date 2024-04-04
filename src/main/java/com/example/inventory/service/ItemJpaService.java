/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here

package com.example.inventory.service;

import java.util.*;
import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemJpaRepository;
import com.example.inventory.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ItemJpaService implements ItemRepository {
    @Autowired
    private ItemJpaRepository itemJpaRepository;

    @Override
    public ArrayList<Item> getItems() {
        List<Item> itemList = itemJpaRepository.findAll();
        ArrayList<Item> item = new ArrayList<>(itemList);
        return item;
    }

    @Override
    public Item getItemById(int itemId) {
        try {
            Item item = itemJpaRepository.findById(itemId).get();
            return item;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Item addItem(Item item) {
        itemJpaRepository.save(item);
        return item;
    }

    @Override
    public Item updateItem(int itemId, Item item) {
        try {
            Item newItem = itemJpaRepository.findById(itemId).get();
            if (item.getItemName() != null) {
                newItem.setItemName(item.getItemName());
            }
            if (item.getQuantity() != 0) {
                newItem.setQuantity(item.getQuantity());
            }
            if (item.getSupplier() != null) {
                newItem.setSupplier(item.getSupplier());
            }
            itemJpaRepository.save(newItem);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
		return item;
    }

    @Override
    public void deleteItem(int itemId) {
        try {
            itemJpaRepository.deleteById(itemId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}