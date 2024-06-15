package com.example.layeredarchitecture.dao;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface OrderDitailDAO {
    int saveOrderDitals(String orderId, String itemCode, BigDecimal unitPrice, int qty) throws SQLException, ClassNotFoundException;
}
