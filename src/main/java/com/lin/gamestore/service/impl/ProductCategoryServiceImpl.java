package com.lin.gamestore.service.impl;

import com.lin.gamestore.dao.ProductCategoryDao;
import com.lin.gamestore.dto.ProductCategoryExecution;
import com.lin.gamestore.entity.ProductCategory;
import com.lin.gamestore.enums.ProductCategoryStateEnum;
import com.lin.gamestore.exceptions.ProductCategoryOperationException;
import com.lin.gamestore.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList() {
        return productCategoryDao.queryProductCategoryList();
    }

    @Override
    @Transactional
    public ProductCategoryExecution bindProductCategory(ProductCategory productCategory) throws ProductCategoryOperationException {
        if (productCategory == null) {
            return new ProductCategoryExecution(ProductCategoryStateEnum.FAILED);
        }
        productCategory.setCreateTime(new Date());
        try {
            int effectedNum = productCategoryDao.insertProductCategory(productCategory);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("新增失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("insertProductCategory error: " + e.getMessage());
        }

    }

    @Override
    @Transactional
    public ProductCategoryExecution removeProductCategory(Long productCategoryId) throws ProductCategoryOperationException {
        if (productCategoryId == null) {
            return new ProductCategoryExecution(ProductCategoryStateEnum.FAILED);
        }
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("删除失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("insertProductCategory error: " + e.getMessage());
        }
    }
}
