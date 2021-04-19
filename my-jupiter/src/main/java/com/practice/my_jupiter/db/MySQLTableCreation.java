package com.practice.my_jupiter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLTableCreation {
    public static void main(String[] args) {
        try {

            // Step 1 Connect to MySQL.
            System.out.println("Connecting to " + MySQLDBUtil.URL);
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

            if (conn == null) {
                return;
            }


            // Step 2 Drop tables in case they exist.
            // use when first implemented, delete all existing tables
            Statement statement = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS orders";
            statement.executeUpdate(sql);


            // Step 3 Create new tables
            // user_id
            sql = "CREATE TABLE users ("
                    + "user_id VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "first_name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "email VARCHAR(255),"
                    + "phone_number VARCHAR(255),"
                    + "PRIMARY KEY (user_id)"
                    + ")";
            statement.executeUpdate(sql);

            // order_id; PRIMARY KEY needs to be unique
            sql = "CREATE TABLE items ("
                    + "order_id VARCHAR(255) NOT NULL,"
                    + "sender_name VARCHAR(255),"
                    + "receiver_name VARCHAR(255),"
                    + "shipping_address VARCHAR(255),"
                    + "billing_address VARCHAR(255),"
                    + "contact_number VARCHAR(255),"
                    + "num_of_package VARCHAR(255),"
                    + "PRIMARY KEY (order_id)"
                    + ")";
            statement.executeUpdate(sql);


            // Step 4: insert fake user 1111/3229c1097c00d497a0fd282d586be050
            sql = "INSERT INTO users VALUES('1111', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith', 'johnsmith@gmail.com', '415-660-1117', 3)";
            statement.executeUpdate(sql);
            conn.close();
            System.out.println("Import done successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
