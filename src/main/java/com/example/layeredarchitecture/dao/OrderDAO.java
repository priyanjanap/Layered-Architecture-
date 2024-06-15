package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public interface OrderDAO {
    String newOrderId() throws SQLException, ClassNotFoundException;

    boolean orderIdExists(String orderId) throws SQLException, ClassNotFoundException;


    int save(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;

}
