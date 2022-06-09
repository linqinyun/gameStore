package com.lin.gamestore.service;

import com.lin.gamestore.dto.ProductExecution;
import com.lin.gamestore.entity.Product;
import com.lin.gamestore.exceptions.ProductOperationException;
import com.lin.gamestore.util.ImageHolder;

import java.io.File;
import java.util.List;

public interface ProductService {
    ProductExecution getProductList();

    ProductExecution getProductById(Long productId) throws ProductOperationException;

    ProductExecution bindProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;

    ProductExecution modifProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;

    ProductExecution removeProduct(Long productId) throws ProductOperationException;
}
