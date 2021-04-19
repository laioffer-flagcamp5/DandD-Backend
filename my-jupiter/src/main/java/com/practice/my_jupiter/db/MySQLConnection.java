package com.practice.my_jupiter.db;

import com.practice.my_jupiter.entity.Order;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MySQLConnection {
    private Connection conn;

    public MySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(MySQLDBUtil.URL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOrder(Order order) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return;
        }
        // insert orders into order table, using placeholders
        String insertOrderSql = "INSERT IGNORE INTO orders VALUES (?, ?, ?, ?, ?, ?, ?)";
        // use try-catch
        try {
            // 固定行 + use real items to replace placeholders
            // MySQL start with 1 not 0
            PreparedStatement statement = conn.prepareStatement(insertOrderSql);
            statement.setString(1, order.getId());
            statement.setString(2, order.getSenderName());
            statement.setString(3, order.getReceiverName());
            statement.setString(4, order.getShippingAddress());
            statement.setString(5, order.getBillingAddress());
            statement.setString(6, order.getContactNumber());
            statement.setInt(7, order.getPackageNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // use saveItem to save item to db, then insert it into history
    // IGNORE -> skip if there's duplicate
    public void setOrderHistory(String userId, Order order) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return;
        }
        saveOrder(order);
        String sql = "INSERT IGNORE INTO orders (user_id, order_id) VALUES (?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete from history
    public void unsetFavoriteItems(String userId, String orderId) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return;
        }
        String sql = "DELETE FROM orders WHERE user_id = ? AND order_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // reads data from MySQL and find your fav item ids
    public Set<String> getOrderHistoryIds(String userId) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return new HashSet<>();
        }

        Set<String> orderHistory = new HashSet<>();

        try {
            String sql = "SELECT order_id FROM orders WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String itemId = rs.getString("item_id");
                orderHistory.add(itemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderHistory;
    }

    public Set<Order> getOrderHistory(String userId) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return new HashSet<>();
        }

        Set<Order> orderHistory = new HashSet<>();
        Set<String> orderHistoryIds = getOrderHistoryIds(userId);

        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            for (String orderId : orderHistoryIds) {
                statement.setString(1, orderId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    orderHistory.add(new Order(rs.getString("order_id")
                            ,rs.getString("sender_name")
                            ,rs.getString("receiver_name")
                            ,rs.getString("shipping_address")
                            ,rs.getString("billing_address")
                            ,rs.getString("contact_number")
                            ,rs.getInt("package_number")));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }
}
