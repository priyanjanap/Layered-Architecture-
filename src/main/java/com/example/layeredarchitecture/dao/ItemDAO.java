package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO  {
    public ArrayList<ItemDTO> getAllItems()throws SQLException,ClassNotFoundException ;
    public  boolean exitItem(String code)throws  SQLException,ClassNotFoundException;
    public void saveItems(String code, String des, int qty, BigDecimal price) throws SQLException, ClassNotFoundException;
    public  void  updateItem(String code, String description, int qtyOnHand, BigDecimal unitPrice) throws SQLException, ClassNotFoundException ;
    public  ItemDTO getItems(String  code) throws SQLException, ClassNotFoundException ;
    public String newItemId() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
    public ItemDTO findItems(String code) throws SQLException, ClassNotFoundException ;
    public int updateItem2(String description, BigDecimal unitPrice, int qtyOnHand, String code) throws SQLException, ClassNotFoundException ;
    public void deleteItem(String Code) throws SQLException, ClassNotFoundException;
}
