package com.elevateHome.model;

public class CategoryModel {
    private int category_id;
    private String category_name;
    private String product_list;
    private int total_product_items;

    public CategoryModel(int category_id, String category_name, String product_list, int total_product_items) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.product_list = product_list;
        this.total_product_items = total_product_items;
    }

    // Getters and Setters
    public int getCategory_id() { 
    	return category_id; 
    }
    public void setCategory_id(int category_id) {
    	this.category_id = category_id;
    }

    public String getCategory_name() {
    	return category_name; 
    	
    }
    public void setCategory_name(String category_name) { 
    	this.category_name = category_name; 
    	
    }
    public String getProduct_list() { 
    	return product_list; 
    }
    public void setProduct_list(String product_list) { 
    	this.product_list = product_list; 
    }

    public int getTotal_product_items() { 
    	return total_product_items; 
    }
    public void setTotal_product_items(int total_product_items) { 
    	this.total_product_items = total_product_items; 
    	
    }
}