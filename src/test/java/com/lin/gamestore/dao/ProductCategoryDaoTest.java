package com.lin.gamestore.dao;

import com.lin.gamestore.BaseTest;
import com.lin.gamestore.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testAquerylist() {
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList();
        assertEquals(0, productCategories.size());
    }

    @Test
    public void testBInsert() {
        String productCategoryName = "test";
        int priority = 1;
        Date createTime = new Date();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setPriority(priority);
        productCategory.setProductCategoryName(productCategoryName);
        productCategory.setCreateTime(createTime);
        int i = productCategoryDao.insertProductCategory(productCategory);
        assertEquals(1, i);
    }

    @Test
    public void testDquerylist() {
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList();
        assertEquals(1, productCategories.size());
    }

    @Test
    public void testEDelete() {
        Long productCategoryId = 1l;
        int i = productCategoryDao.deleteProductCategory(productCategoryId);
        assertEquals(1, i);
    }
}
