package com.elevateHome.service;

import com.elevateHome.config.DBConfig;

import com.elevateHome.model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDatabaseService {

    public static List<ProductModel> getAllProducts() throws Exception {
        List<ProductModel> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductModel product = new ProductModel(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_brand"),
                        rs.getDouble("product_price"),
                        rs.getInt("product_sales"),
                        rs.getInt("product_stock"),
                        rs.getString("product_status"),
                        rs.getString("product_image") // Added
                );
                productList.add(product);
            }
        } catch (Exception e) {
            System.err.println("Error fetching products: " + e.getMessage());
            throw e;
        }

        return productList;
    }
    
    // search
    

    public static void addProduct(ProductModel product) throws Exception {
        String sql = "INSERT INTO product (product_name, product_brand, product_price, product_sales, product_stock, product_status, product_image) VALUES (?, ?, ?, ?, ?, ?,?)";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, product.getProduct_name());
            ps.setString(2, product.getProduct_brand());
            ps.setDouble(3, product.getProduct_price());
            ps.setInt(4, product.getProduct_sales());
            ps.setInt(5, product.getProduct_stock());
            ps.setString(6, product.getProduct_status());
            ps.setString(7, product.getProduct_image());

            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " new product(s).");
        } catch (Exception e) {
            System.err.println("Error inserting product: " + e.getMessage());
            throw e;
        }
    }

    public static void updateProduct(ProductModel product) throws Exception {
        String sql = "UPDATE product SET product_name = ?, product_brand = ?, product_price = ?, product_sales = ?, product_stock = ?, product_status = ?, product_image = ? WHERE product_id = ?";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, product.getProduct_name());
            ps.setString(2, product.getProduct_brand());
            ps.setDouble(3, product.getProduct_price());
            ps.setInt(4, product.getProduct_sales());
            ps.setInt(5, product.getProduct_stock());
            ps.setString(6, product.getProduct_status());
            ps.setString(7, product.getProduct_image());
            ps.setInt(8, product.getProduct_id());

            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " product(s) with ID " + product.getProduct_id());
        } catch (Exception e) {
            System.err.println("Error updating product: " + e.getMessage());
            throw e;
        }
    }

    public static void deleteProduct(int productId) throws Exception {
        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productId);

            int rows = ps.executeUpdate();
            System.out.println("Deleted " + rows + " product(s) with ID " + productId);
        } catch (Exception e) {
            System.err.println("Error deleting product: " + e.getMessage());
            throw e;
        }
    }
}
