package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO{
  public String newOrderId() throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    ResultSet rst = connection.createStatement().executeQuery("SELECT oid FROM orders ORDER BY oid DESC LIMIT 1;");
    if (rst.next()) {
      String id = rst.getString("oid");
      int newItemId = Integer.parseInt(id.replace("OID-", "")) + 1;
      return String.format("OID-%03d", newItemId);
    } else {
      return "OID-001";
    }
  }

  public boolean orderIdExists(String orderId) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
    stm.setString(1, orderId);
    return stm.executeQuery().next();
  }


  public int save(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement stm = connection.prepareStatement("INSERT INTO Orders (oid, date, customerID) VALUES (?,?,?)");
    stm.setString(1, orderId);
    stm.setDate(2, Date.valueOf(orderDate));
    stm.setString(3, customerId);
    return stm.executeUpdate();
  }

}
