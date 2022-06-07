package com.lin.gamestore.dao;

import com.lin.gamestore.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 产品列表
     *
     * @return
     */
    List<Product> queryProductList();

    /**
     * 产品数量
     *
     * @return
     */
    int queryproductCount();

    /**
     * 单条查询产品
     *
     * @param productId
     * @return
     */
    Product queryProductById(@Param("productId") long productId);

    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 修改产品
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 清空产品中已删除的分类
     *
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(@Param("productCategoryId") long productCategoryId);

    /**
     * 删除产品分类
     *
     * @param productId
     * @return
     */
    int deleteProduct(@Param("productId") long productId);
}
