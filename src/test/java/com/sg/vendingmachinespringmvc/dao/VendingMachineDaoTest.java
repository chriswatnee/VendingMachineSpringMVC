/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author christopherwatnee
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Item> itemList = dao.getAllItems();
        for (Item item : itemList) {
            dao.removeItem(item.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */
    @Test
    public void testAddGetItem() {
        Item item = new Item();
        item.setId(1);
        item.setName("Snickers");
        item.setPrice(new BigDecimal("1.85"));
        item.setQuantity(9);
        
        dao.addItem(item);
        
        Item fromDao = dao.getItem(item.getId());
        
        assertEquals(item, fromDao);
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItem() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.85"));
        item1.setQuantity(9);
        
        dao.addItem(item1);
        
        Item item2 = new Item();
        item2.setId(2);
        item2.setName("M&Ms");
        item2.setPrice(new BigDecimal("1.50"));
        item2.setQuantity(2);
        
        dao.addItem(item2);
        
        dao.removeItem(item1.getId());
        assertEquals(1, dao.getAllItems().size());
        assertNull(dao.getItem(item1.getId()));
        
        dao.removeItem(item2.getId());
        assertEquals(0, dao.getAllItems().size());
        assertNull(dao.getItem(item2.getId()));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineDao.
     */
    @Test
    public void testPurchaseItem() {
        Item item = new Item();
        item.setId(1);
        item.setName("Snickers");
        item.setPrice(new BigDecimal("1.85"));
        item.setQuantity(9);
        
        dao.addItem(item);
        
        dao.purchaseItem(item.getId());
        
        Item fromDao = dao.getItem(item.getId());
        
        assertEquals(8, fromDao.getQuantity());
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.85"));
        item1.setQuantity(9);
        
        dao.addItem(item1);
        
        Item item2 = new Item();
        item2.setId(2);
        item2.setName("M&Ms");
        item2.setPrice(new BigDecimal("1.50"));
        item2.setQuantity(2);
        
        dao.addItem(item2);
        
        assertEquals(2, dao.getAllItems().size());
    }
    
}
