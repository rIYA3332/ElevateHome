package com.elevateHome.service;

import com.elevateHome.config.DBConfig;

import com.elevateHome.model.CategoryModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDatabaseService {

    public static List<CategoryModel> getAllCategories() throws Exception {
        List<CategoryModel> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoryModel category = new CategoryModel(
                    rs.getInt("category_id"),
                    rs.getString("category_name"),
                    rs.getString("product_list"),
                    rs.getInt("total_product_items")
                );
                categories.add(category);
            }
        }
        return categories;
    }

    public static void addCategory(CategoryModel category) throws Exception {
        String sql = "INSERT INTO category (category_name, product_list, total_product_items) VALUES (?, ?, ?)";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category.getCategory_name());
            ps.setString(2, category.getProduct_list());
            ps.setInt(3, category.getTotal_product_items());
            ps.executeUpdate();
        }
    }

    public static void updateCategory(CategoryModel category) throws Exception {
        String sql = "UPDATE category SET category_name=?, product_list=?, total_product_items=? WHERE category_id=?";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category.getCategory_name());
            ps.setString(2, category.getProduct_list());
            ps.setInt(3, category.getTotal_product_items());
            ps.setInt(4, category.getCategory_id());
            ps.executeUpdate();
        }
    }

    public static void deleteCategory(int categoryId) throws Exception {
        String sql = "DELETE FROM category WHERE category_id=?";

        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            ps.executeUpdate();
        }
    }
}