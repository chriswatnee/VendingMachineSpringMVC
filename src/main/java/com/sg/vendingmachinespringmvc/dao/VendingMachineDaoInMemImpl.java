/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author christopherwatnee
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {
    
    // Holds all of our Item objects
    private Map<Long, Item> itemMap = new HashMap<>();
    // Used to assign ids to Items
    private static long itemIdCounter = 1;
    
    public VendingMachineDaoInMemImpl() {
        // Populate vending machine with initial inventory
        addItemsToInventory();
    }

    @Override
    public Item getItem(long id) {
        return itemMap.get(id);
    }

    @Override
    public Item addItem(Item item) {
        // Assign the current counter values as the itemId
        item.setId(itemIdCounter);
        // Increment the counter so it is ready for use for the 
        // next item
        itemIdCounter++;
        // Put item into the map using the name as the key
        return itemMap.put(item.getId(), item);
    }

    @Override
    public Item removeItem(long id) {
        return itemMap.remove(id);
    }

    @Override
    public Item purchaseItem(long id) {
        Item item = itemMap.get(id);
        item.setQuantity(item.getQuantity() - 1);
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        Collection<Item> c = itemMap.values();
        return new ArrayList(c);
    }
    
    private void addItemsToInventory() { 
        // Create a new items and add to inventory
        Item item1 = new Item();
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.85"));
        item1.setQuantity(9);

        addItem(item1);
        
        Item item2 = new Item();
        item2.setName("M&Ms");
        item2.setPrice(new BigDecimal("1.50"));
        item2.setQuantity(2);

        addItem(item2);
        
        Item item3 = new Item();
        item3.setName("Pringles");
        item3.setPrice(new BigDecimal("2.10"));
        item3.setQuantity(5);

        addItem(item3);
        
        Item item4 = new Item();
        item4.setName("Reese's");
        item4.setPrice(new BigDecimal("1.85"));
        item4.setQuantity(4);

        addItem(item4);
        
        Item item5 = new Item();
        item5.setName("Pretzels");
        item5.setPrice(new BigDecimal("1.25"));
        item5.setQuantity(9);

        addItem(item5);
        
        Item item6 = new Item();
        item6.setName("Twinkies");
        item6.setPrice(new BigDecimal("1.95"));
        item6.setQuantity(3);

        addItem(item6);
        
        Item item7 = new Item();
        item7.setName("Doritos");
        item7.setPrice(new BigDecimal("1.75"));
        item7.setQuantity(11);

        addItem(item7);
        
        Item item8 = new Item();
        item8.setName("Almond Joy");
        item8.setPrice(new BigDecimal("1.85"));
        item8.setQuantity(0);

        addItem(item8);
        
        Item item9 = new Item();
        item9.setName("Trident");
        item9.setPrice(new BigDecimal("1.95"));
        item9.setQuantity(6);

        addItem(item9);
    }
    
}
