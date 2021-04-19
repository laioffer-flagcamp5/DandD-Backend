package com.practice.my_jupiter.db;

public class MySQLDBUtil {

    private static final String INSTANCE = "crystal-database-1.cia6rz5s4gvd.us-east-2.rds.amazonaws.com";
    private static final String PORT_NUM = "3306";
    public static final String DB_NAME = "laiproject";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "Crystalhu2001";//  getEnv("my-db-password")
    public static final String URL = "jdbc:mysql://"
            + INSTANCE + ":" + PORT_NUM + "/" + DB_NAME
            + "?user=" + USERNAME + "&password=" + PASSWORD
            + "&autoReconnect=true&serverTimezone=UTC";
}

