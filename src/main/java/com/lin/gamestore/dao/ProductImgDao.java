package com.lin.gamestore.dao;

import com.lin.gamestore.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    /**
     * 图片列表-产品下的
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(Long productId);

    /**
     * 批量添加图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除指定产品的图片
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(Long productId);
}
