package com.lin.gamestore.dao;

import com.lin.gamestore.BaseTest;
import com.lin.gamestore.entity.Product;
import com.lin.gamestore.entity.ProductCategory;
import com.lin.gamestore.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testInsertProduct(){
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2l);
        product.setProductCategory(productCategory);
        product.setProductName("只狼·影逝二度");
        product.setNormalPrice(new BigDecimal(249.67));
        product.setPromotionPrice(new BigDecimal(149.34));
        product.setProductDesc("不死斩与狼的故事");
        product.setImgAddr("https://baidu.com");
        product.setTotal(200);
        product.setPriority(100);
        product.setEnableStatus(1);
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        int i = productDao.insertProduct(product);
        //图片
        ProductImg productImg1 = new ProductImg();
        productImg1.setProductId(product.getProductId());
        productImg1.setImgDesc("test1");
        productImg1.setPriority(1);
        productImg1.setImgAddr("https://baidu.com");
        ProductImg productImg2 = new ProductImg();
        productImg2.setProductId(product.getProductId());
        productImg2.setImgDesc("test2");
        productImg2.setPriority(1);
        productImg2.setImgAddr("https://baidu.com");
        ProductImg productImg3 = new ProductImg();
        productImg3.setProductId(product.getProductId());
        productImg3.setImgDesc("test2");
        productImg3.setPriority(1);
        productImg3.setImgAddr("https://baidu.com");
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        productImgList.add(productImg3);
        productImgDao.batchInsertProductImg(productImgList);
        assertEquals(1,i);
    }
    @Test
    public void testQueryProductList(){
        List<Product> productList = productDao.queryProductList();
        for (Product pro: productList){
            System.out.println(pro.getProductCategory().getProductCategoryName());
        }
//        assertEquals(1,productList.size());
    }

    @Test
    public void testQueryproductCount(){
        int count = productDao.queryproductCount();
        System.out.println(count);
    }

    @Test
    public void testUpdateProduct(){
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2l);
        product.setProductCategory(productCategory);
        product.setProductName("城市·天际线");
        product.setNormalPrice(new BigDecimal(649.67));
        product.setPromotionPrice(new BigDecimal(249.34));
        product.setProductDesc("建立属于自己的城市");
        product.setImgAddr("https://baidu.com");
        product.setTotal(100);
        product.setPriority(50);
        product.setUpdateTime(new Date());
        product.setProductId(1l);
        int i = productDao.updateProduct(product);
        assertEquals(1,i);
    }

    @Test
    public void testDeleteProduct(){
        int i = productDao.updateProductCategoryToNull(2);
        assertEquals(2,i);
    }

}
