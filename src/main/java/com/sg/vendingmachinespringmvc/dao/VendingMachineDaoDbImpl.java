/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chris
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao {
    
    private static final String SQL_SELECT_ITEM
            = "select * from items where item_id = ?";
    private static final String SQL_INSERT_ITEM
            = "insert into items "
            + "(name, price, quantity) "
            + "values (?, ?, ?)";
    private static final String SQL_DELETE_ITEM
            = "delete from items where item_id = ?";
    private static final String SQL_UPDATE_ITEM
            = "update items set "
            + "quantity = ? "
            + "where item_id = ?";
    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from items";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item getItem(long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ItemMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given item id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item addItem(Item item) {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                item.getName(),
                item.getPrice(),
                item.getQuantity());

        // query the database for the id that was just assigned to the new
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                                                Integer.class);
        // set the new id value on the item object and return it
        item.setId(newId);
        return item;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item removeItem(long id) {
        try {
            Item removedItem = jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ItemMapper(), id);
            jdbcTemplate.update(SQL_DELETE_ITEM, id);
            return removedItem;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item purchaseItem(long id) {
        try {
            Item purchasedItem = jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ItemMapper(), id);
            purchasedItem.setQuantity(purchasedItem.getQuantity() - 1);
            jdbcTemplate.update(SQL_UPDATE_ITEM,
                    purchasedItem.getQuantity(),
                    purchasedItem.getId());
            return purchasedItem;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, 
                                  new ItemMapper());
    }
    
    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setId(rs.getLong("item_id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getBigDecimal("price"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        }
    }
    
}
