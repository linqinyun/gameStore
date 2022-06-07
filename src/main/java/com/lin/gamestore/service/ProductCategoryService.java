package com.lin.gamestore.service;

import com.lin.gamestore.dto.ProductCategoryExecution;
import com.lin.gamestore.entity.ProductCategory;
import com.lin.gamestore.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList();

    ProductCategoryExecution bindProductCategory(ProductCategory productCategory) throws ProductCategoryOperationException;

    ProductCategoryExecution removeProductCategory(Long categoryId) throws ProductCategoryOperationException;
}
