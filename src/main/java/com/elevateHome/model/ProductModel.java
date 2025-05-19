package com.elevateHome.model;

/**
 * @author Riya Bhatta
 * 
 * The ProductModel class represents a product with attributes such as product ID, name, brand, price,
 * sales, stock, and status. It provides methods for getting and setting these attributes.
 * - Getter: Returns the current value of the attribute. 
 * - Setter: Sets a new value for the attribute.
 */
public class ProductModel {
    private int product_id;
    private String product_name;
    private String product_brand;
    private double product_price;
    private int product_sales;
    private int product_stock;
    private String product_status;
    private String product_image;

    /**
     * Constructor to initialize a ProductModel object with the provided details.
     * 
     * @param product_id The unique identifier for the product.
     * @param product_name The name of the product.
     * @param product_brand The brand of the product.
     * @param product_price The price of the product.
     * @param product_sales The number of units sold.
     * @param product_stock The number of units in stock.
     * @param product_status The status of the product 
     */
    public ProductModel(int product_id, String product_name, String product_brand, double product_price, int product_sales, int product_stock, String product_status, String product_image) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_brand = product_brand;
        this.product_price = product_price;
        this.product_sales = product_sales;
        this.product_stock = product_stock;
        this.product_status = product_status;
        this.product_image = product_image;
    }

    // Getters and setters for all fields

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public int getProduct_sales() {
        return product_sales;
    }

    public void setProduct_sales(int product_sales) {
        this.product_sales = product_sales;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }
    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
