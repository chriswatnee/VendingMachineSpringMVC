/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author christopherwatnee
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private Item onlyItem;
    private List<Item> itemList = new ArrayList<>();
    
    public VendingMachineDaoStubImpl() {
        onlyItem = new Item();
        onlyItem.setId(1);
        onlyItem.setName("Snickers");
        onlyItem.setPrice(new BigDecimal("1.85"));
        onlyItem.setQuantity(9);
        
        itemList.add(onlyItem);
    }

    @Override
    public Item getItem(long id) {
        if (id == onlyItem.getId()) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item addItem(Item item) {
        if (item.getId() == onlyItem.getId()) {
            return onlyItem;
        } else {
            return null;
        }
    }
    
    @Override
    public Item removeItem(long id) {
        if (id == onlyItem.getId()) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item purchaseItem(long id) {
        if (id == onlyItem.getId()) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return itemList;
    }
    
}
