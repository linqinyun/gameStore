package com.lin.gamestore.dto;

import com.lin.gamestore.entity.ProductCategory;
import com.lin.gamestore.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    private int state;
    private String stateInfo;
    private int count;
    private ProductCategory productCategory;
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {
    }
    //失败的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //成功的构造器-1
    public ProductCategoryExecution(int state, String stateInfo, ProductCategory productCategory) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.productCategory = productCategory;
    }
    //成功的构造器-2
    public ProductCategoryExecution(int state, String stateInfo, List<ProductCategory> productCategoryList) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.productCategoryList = productCategoryList;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
