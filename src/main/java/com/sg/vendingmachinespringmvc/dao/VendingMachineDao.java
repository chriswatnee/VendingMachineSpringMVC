/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.util.List;

/**
 *
 * @author christopherwatnee
 */
public interface VendingMachineDao {
    
    Item getItem(long id);
    
    Item addItem(Item item);
    
    Item removeItem(long id);
    
    Item purchaseItem(long id);
    
    List<Item> getAllItems();
    
}
