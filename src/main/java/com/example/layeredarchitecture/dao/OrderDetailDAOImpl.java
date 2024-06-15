package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDitailDAO{
    public   int saveOrderDitals(String orderId, String itemCode, BigDecimal unitPrice,int qty) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement  stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        stm.setString(1, orderId);
        stm.setString(2,itemCode);
        stm.setBigDecimal(3, unitPrice);
        stm.setInt(4,qty);
        return stm.executeUpdate();

    }
}
