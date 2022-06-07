package com.lin.gamestore.service;

import com.lin.gamestore.BaseTest;
import com.lin.gamestore.dto.ProductCategoryExecution;
import com.lin.gamestore.entity.ProductCategory;
import com.lin.gamestore.enums.ProductCategoryStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryServiceTest extends BaseTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    public void getAlist(){
        List<ProductCategory> productCategories = productCategoryService.getProductCategoryList();
        assertEquals(2,productCategories.size());
    }
    @Test
    public void testBbind(){
        String productCategoryName = "test";
        int priority = 1;
        Date createTime = new Date();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setPriority(priority);
        productCategory.setProductCategoryName(productCategoryName);
        productCategory.setCreateTime(createTime);
        ProductCategoryExecution pe = productCategoryService.bindProductCategory(productCategory);
        assertEquals(ProductCategoryStateEnum.SUCCESS.getState(),pe.getState());
    }

    @Test
    public void testCremove(){
        Long productCategoryId = 3l;
        ProductCategoryExecution pe = productCategoryService.removeProductCategory(productCategoryId);
        assertEquals(ProductCategoryStateEnum.SUCCESS.getState(),pe.getState());
    }
}
