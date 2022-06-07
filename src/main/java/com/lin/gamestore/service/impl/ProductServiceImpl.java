package com.lin.gamestore.service.impl;

import com.lin.gamestore.dao.ProductDao;
import com.lin.gamestore.dto.ProductExecution;
import com.lin.gamestore.entity.Product;
import com.lin.gamestore.enums.ProductStateEnum;
import com.lin.gamestore.exceptions.ProductOperationException;
import com.lin.gamestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductExecution getProductList() {
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productDao.queryProductList());
        pe.setCount(productDao.queryproductCount());
        return pe;
    }

    @Override
    public ProductExecution getProductById(Long productId) throws ProductOperationException {
        if (productId == null) {
            return new ProductExecution(ProductStateEnum.FAILED);
        }
        ProductExecution pe = new ProductExecution();
        pe.setProduct(productDao.queryProductById(productId));
        return pe;
    }

    @Override
    @Transactional
    public ProductExecution bindProduct(Product product) throws ProductOperationException {
        if (product == null) {
            return new ProductExecution(ProductStateEnum.FAILED);
        }
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setEnableStatus(1);
        try {
            int effectedNum = productDao.insertProduct(product);
            if (effectedNum <= 0) {
                throw new ProductOperationException("创建商品失败");
            }
        } catch (Exception e) {
            throw new ProductOperationException("创建商品失败:" + e.getMessage());
        }
        return new ProductExecution(ProductStateEnum.SUCCESS);
    }

    @Override
    @Transactional
    public ProductExecution modifProduct(Product product) throws ProductOperationException {
        if (product == null && product.getProductId() == null) {
            return new ProductExecution(ProductStateEnum.FAILED);
        }
        product.setUpdateTime(new Date());
        try {
            int effectedNum = productDao.insertProduct(product);
            if (effectedNum <= 0) {
                throw new ProductOperationException("更新商品失败");
            }
        } catch (Exception e) {
            throw new ProductOperationException("更新商品失败:" + e.getMessage());
        }
        return new ProductExecution(ProductStateEnum.SUCCESS);
    }

    @Override
    @Transactional
    public ProductExecution removeProduct(Long productId) throws ProductOperationException {
        if (productId == null) {
            return new ProductExecution(ProductStateEnum.FAILED);
        }
        try {
            int effectedNum = productDao.deleteProduct(productId);
            if (effectedNum <= 0) {
                throw new ProductOperationException("删除商品失败");
            }
        } catch (Exception e) {
            throw new ProductOperationException("删除商品失败:" + e.getMessage());
        }
        return new ProductExecution(ProductStateEnum.SUCCESS);
    }
}
