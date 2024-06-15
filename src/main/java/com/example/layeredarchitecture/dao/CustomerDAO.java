package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    boolean exitCustomer(String id) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllcustomer() throws SQLException, ClassNotFoundException;

    void saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException;

    void updateCustomer(String name, String address, String id) throws SQLException, ClassNotFoundException;

    void deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String genarateNewID() throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}


