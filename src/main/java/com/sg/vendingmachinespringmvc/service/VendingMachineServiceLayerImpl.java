/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author christopherwatnee
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    
    private BigDecimal balance = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
    private long itemIdSelected;
    
    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    
    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public long getItemIdSelected() {
        return itemIdSelected;
    }

    @Override
    public void setItemIdSelected(long itemIdSelected) {
        this.itemIdSelected = itemIdSelected;
    }

    @Override
    public BigDecimal insertMoney(BigDecimal money) {
        money = money.setScale(2, RoundingMode.HALF_UP);
        balance = balance.add(money);
        return money;
    }

    @Override
    public Item getItem(long id) {
        return dao.getItem(id);
    }

    @Override
    public Item purchaseItem(long id) throws InsufficientFundsException, NoItemInventoryException {
        Item item = dao.getItem(id);
        BigDecimal itemPrice = item.getPrice();
        
        // Validate user has enough money
        validateFunds(itemPrice);
        // Validate item is in stock
        validateInventory(item.getQuantity());
        
        // Passed all business rules checks so proceed
        
        // Update balance
        balance = balance.subtract(itemPrice);
        // Purchase Item
        dao.purchaseItem(item.getId());
        
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Change getChange(BigDecimal money) {
        balance = balance.subtract(money);
        BigDecimal balanceInPennies = money.multiply(new BigDecimal("100"));
        return new Change(balanceInPennies.intValue());
    }
    
    private void validateFunds(BigDecimal itemPrice) throws InsufficientFundsException {
        if (balance.compareTo(itemPrice) == -1) {
            throw new InsufficientFundsException("Please deposit: $" + itemPrice.subtract(balance));
        }
    }
    
    private void validateInventory(int itemQuantity) throws NoItemInventoryException {
        if (itemQuantity == 0) {
            throw new NoItemInventoryException("SOLD OUT!!!");
        }
    }
    
}
