package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.String.*;

public class ItemDAOImpl implements  ItemDAO {
    public ArrayList<ItemDTO> getAllItems()throws SQLException,ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(
                                rst.getString(1),
                                rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4))

            ;
            allItems.add(itemDTO);
        }
        return allItems;
    }
    public  boolean exitItem(String code)throws  SQLException,ClassNotFoundException{  Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code from  item where code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }
    public void saveItems(String code, String des, int qty, BigDecimal price) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO item (code, description, qtyOnHand,unitPrice) VALUES (?, ?, ?,?)");
        pstm.setString(1, code);
        pstm.setString(2, des);
        pstm.setBigDecimal(3, price);
        pstm.setInt(4, qty);
        pstm.executeUpdate();
        pstm.executeUpdate();
    }
public  void  updateItem(String code, String description, int qtyOnHand, BigDecimal unitPrice) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
    pstm.setString(1, description);
    pstm.setBigDecimal(2, unitPrice);
    pstm.setInt(3, qtyOnHand);
    pstm.setString(4, code);
    pstm.executeUpdate();

}
public  ItemDTO getItems(String  code) throws SQLException, ClassNotFoundException {
    System.out.println(code);
    Connection connection= DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item WHERE code=?");
    pstm.setString(1, code + "");
    ResultSet rst = pstm.executeQuery();
    rst.next();
    System.out.println(rst);
    return new ItemDTO(code + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

}
    public String newItemId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<String> codes = new ArrayList<>();

        while (rst.next()){
            String code = rst.getString("code");

            codes.add(code);
        }
        return codes;
    }
    public ItemDTO findItems(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code);
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }

    public int updateItem2(String description, BigDecimal unitPrice, int qtyOnHand, String code) throws SQLException, ClassNotFoundException {
        try (Connection connection = DBConnection.getDbConnection().getConnection();
             PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?")) {
            pstm.setString(1, description);
            pstm.setBigDecimal(2, unitPrice);
            pstm.setInt(3, qtyOnHand);
            pstm.setString(4, code);
            return pstm.executeUpdate();
        }
    }
    public void deleteItem(String Code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM item WHERE code=?");
        pstm.setString(1, Code);
        pstm.executeUpdate();
    }

}
