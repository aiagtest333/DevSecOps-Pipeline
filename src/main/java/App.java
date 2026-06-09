package com.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void getUser(Connection conn, String userId) throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + userId);
    }

    public static void main(String[] args) {
        System.out.println("DevSecOps Pipeline Demo");
    }
}
