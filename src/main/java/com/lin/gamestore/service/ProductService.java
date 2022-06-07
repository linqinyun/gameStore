package com.lin.gamestore.service;

import com.lin.gamestore.dto.ProductExecution;
import com.lin.gamestore.entity.Product;
import com.lin.gamestore.exceptions.ProductOperationException;

public interface ProductService {
    ProductExecution getProductList();

    ProductExecution getProductById(Long productId) throws ProductOperationException;

    ProductExecution bindProduct(Product product) throws ProductOperationException;

    ProductExecution modifProduct(Product product) throws ProductOperationException;

    ProductExecution removeProduct(Long productId) throws ProductOperationException;
}
