package com.lin.gamestore.dao;

import com.lin.gamestore.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 产品分类列表
     * @return
     */
    List<ProductCategory> queryProductCategoryList();

    /**
     * 添加产品分类
     * @param productCategory
     * @return
     */
    int insertProductCategory(ProductCategory productCategory);


    /**
     * 删除产品分类
     * @param productCategoryId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId);
}
