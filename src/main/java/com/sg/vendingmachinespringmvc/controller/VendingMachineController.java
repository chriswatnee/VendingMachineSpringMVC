/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author christopherwatnee
 */
@Controller
public class VendingMachineController {
    
    private VendingMachineServiceLayer service;
    
    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayingVendingMachinePage(Model model) {
        // Get all the Items from the Service Layer
        List<Item> itemList = service.getAllItems();
        // Get balance and itemSelectd from Service Layer
        BigDecimal balance = service.getBalance();
        long itemIdSelected = service.getItemIdSelected();
        
        // Put the List of the Items on the Model
        model.addAttribute("itemList", itemList);
        model.addAttribute("balance", balance);
        model.addAttribute("itemIdSelected", itemIdSelected);
        
        // Return the logical name of our View component
        return "index";
    }
    
    @RequestMapping(value = "/insertMoney", method = RequestMethod.POST)
    public String insertMoney(HttpServletRequest request) {
        String moneyParameter = request.getParameter("money");
        BigDecimal money = new BigDecimal(moneyParameter);
        
        service.insertMoney(money);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request) {
        String idParameter = request.getParameter("id");
        long id = Long.parseLong(idParameter);
        
        service.setItemIdSelected(id);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String itemIdSelectedParameter = request.getParameter("itemIdSelected");
        long itemIdSelected = Long.parseLong(itemIdSelectedParameter);
        
        try {
            service.purchaseItem(itemIdSelected);
            BigDecimal totalBalance = service.getBalance();
            Change change = service.getChange(totalBalance);
            String changeString = change.toString();
            // Add flash atttributes
            redirectAttributes.addFlashAttribute("change", changeString);
            redirectAttributes.addFlashAttribute("message", "Thank You!!!");
        } catch (InsufficientFundsException | NoItemInventoryException e) {
            // Add flash atttribute
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/returnChange", method = RequestMethod.POST)
    public String returnChange(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        BigDecimal balance = service.getBalance();
        
        // If there is a balance great than zero than create change
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            Change change = service.getChange(balance);
            String changeString = change.toString();
            // Add flash atttributes
            redirectAttributes.addFlashAttribute("change", changeString);
        }
        
        // Set item selected to 0
        service.setItemIdSelected(0);
        
        return "redirect:/";
    }
}
