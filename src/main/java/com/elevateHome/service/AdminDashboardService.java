package com.elevateHome.service;

import com.elevateHome.config.DBConfig;

import java.sql.*;

public class AdminDashboardService {
    
    // Getting total user count using user table
    public static int getTotalUserCount() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM user";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            return rs.next() ? rs.getInt("total") : 0;
        }
    }
    
    // Getting total product count using product table
    public static int getTotalProductCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM product";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
    
    // Getting low stock count using product_stock column
    public static int getLowStockCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM product WHERE product_stock < 5";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
    
    // Getting total revenue, calculated from product sales
    public static double getTotalRevenue() throws Exception {
        String sql = "SELECT SUM(product_price * product_sales) FROM product";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }
    
    // Getting top selling products for dashboard table
    public static String[][] getTopSellingProducts(int limit) throws Exception {
        String sql = "SELECT product_name, product_sales FROM product ORDER BY product_sales DESC LIMIT ?";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            
            String[][] results = new String[limit][2];
            int i = 0;
            while (rs.next() && i < limit) {
                results[i][0] = rs.getString("product_name");
                results[i][1] = String.valueOf(rs.getInt("product_sales"));
                i++;
            }
            return results;
        }
    }
    
    // Getting low stock products for dashboard table
    public static String[] getLowStockProductNames(int limit) throws Exception {
        String sql = "SELECT product_name FROM product WHERE product_stock < 5 ORDER BY product_stock ASC LIMIT ?";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            
            String[] results = new String[limit];
            int i = 0;
            while (rs.next() && i < limit) {
                results[i] = rs.getString("product_name");
                i++;
            }
            return results;
        }
    }
}