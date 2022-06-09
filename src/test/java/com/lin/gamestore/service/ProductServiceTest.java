package com.lin.gamestore.service;


import com.lin.gamestore.BaseTest;
import com.lin.gamestore.dto.ProductExecution;
import com.lin.gamestore.entity.Product;
import com.lin.gamestore.entity.ProductCategory;
import com.lin.gamestore.enums.ProductStateEnum;
import com.lin.gamestore.util.ImageHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testBindProduct() throws FileNotFoundException {
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2l);
        product.setProductCategory(productCategory);
        product.setProductName("只狼·影逝二度");
        product.setNormalPrice(new BigDecimal("249.67"));
        product.setPromotionPrice(new BigDecimal("149.34"));
        product.setProductDesc("不死斩与狼的故事");
        product.setTotal(200);
        product.setPriority(100);
        File img = new File("D:\\www\\images\\pp.png");
        InputStream is = new FileInputStream(img);
        ImageHolder thumbnail = new ImageHolder(img.getName(), is);
        File img1 = new File("D:\\ZHR\\Pictures\\Camera Roll\\p1.jpg");
        InputStream is1 = new FileInputStream(img1);
        ImageHolder thumbnail1 = new ImageHolder(img.getName(), is1);
        File img2 = new File("D:\\ZHR\\Pictures\\Camera Roll\\p2.jpg");
        InputStream is2 = new FileInputStream(img2);
        ImageHolder thumbnail2 = new ImageHolder(img.getName(), is2);
        File img3 = new File("D:\\ZHR\\Pictures\\Camera Roll\\p3.jpg");
        InputStream is3 = new FileInputStream(img3);
        ImageHolder thumbnail3 = new ImageHolder(img.getName(), is3);
        List<ImageHolder> files = new ArrayList<>();
        files.add(thumbnail1);
        files.add(thumbnail2);
        files.add(thumbnail3);
        ProductExecution pe = productService.bindProduct(product, thumbnail, files);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }
}
