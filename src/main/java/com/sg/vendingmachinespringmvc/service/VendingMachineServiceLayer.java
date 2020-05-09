/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author christopherwatnee
 */
public interface VendingMachineServiceLayer {
    
    BigDecimal getBalance();
    
    void setBalance(BigDecimal balance);
    
    long getItemIdSelected();
    
    void setItemIdSelected(long itemIdSelected);
    
    BigDecimal insertMoney(BigDecimal money);
    
    Item getItem(long id);
    
    Item purchaseItem(long id) throws InsufficientFundsException, NoItemInventoryException;
    
    List<Item> getAllItems();
    
    Change getChange(BigDecimal money);
    
}
