package com.lin.gamestore.service.impl;

import com.lin.gamestore.dao.ProductDao;
import com.lin.gamestore.dao.ProductImgDao;
import com.lin.gamestore.dto.ProductExecution;
import com.lin.gamestore.entity.Product;
import com.lin.gamestore.entity.ProductImg;
import com.lin.gamestore.enums.ProductStateEnum;
import com.lin.gamestore.exceptions.ProductOperationException;
import com.lin.gamestore.service.ProductService;
import com.lin.gamestore.util.ImageHolder;
import com.lin.gamestore.util.ImageUtil;
import com.lin.gamestore.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

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
    public ProductExecution bindProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {
        if (product == null) {
            return new ProductExecution(ProductStateEnum.FAILED);
        }
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setEnableStatus(1);
        // 添加图片缩略图
        if (thumbnail != null) {
            addProductImg(product, thumbnail);
        }
        try {
            int effectedNum = productDao.insertProduct(product);
            if (effectedNum <= 0) {
                throw new ProductOperationException("创建商品失败");
            }
        } catch (Exception e) {
            throw new ProductOperationException("创建商品失败:" + e.getMessage());
        }
        //详情图
        if (productImgList != null && productImgList.size() > 0) {
            // 添加图片列表
            addProductImgList(product, productImgList);
        }
        return new ProductExecution(ProductStateEnum.SUCCESS, product);
    }


    @Override
    @Transactional
    public ProductExecution modifProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {
        if (product == null && product.getProductId() == null) {
            return new ProductExecution(ProductStateEnum.FAILED);
        }
        product.setUpdateTime(new Date());
        // 修改图片缩略图
        if (thumbnail != null) {
            // 先获取一遍原有信息，因为原来的信息里有原图片地址
            Product tempProduct = productDao.queryProductById(product.getProductId());
            if (tempProduct.getImgAddr() != null) {
                ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
            }
            addProductImg(product, thumbnail);
        }
        //详情图
        if (productImgList != null && productImgList.size() > 0) {
            //删除原有的
            deleteProductImgList(product.getProductId());
            // 添加图片列表
            addProductImgList(product, productImgList);
        }
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

    /**
     * 图片缩略图路径
     *
     * @param product
     * @param thumbnail
     */
    private void addProductImg(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getTypeImagePath();
        String productImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(productImgAddr);
    }

    /**
     * 详情图批量添加
     *
     * @param product
     * @param productImgList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgList) {
        String dest = PathUtil.getTypeImagePath();
        List<ProductImg> productImgLists = new ArrayList<ProductImg>();
        for (ImageHolder f : productImgList) {
            String productImgAddr = ImageUtil.generateNormalImg(f, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(productImgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImg.setPriority(100);
            productImg.setImgDesc("示例描述");
            productImgLists.add(productImg);
        }
        if (productImgLists.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgLists);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图失败" + e.toString());
            }
        }
    }

    /**
     * 删除原有商品详情图
     *
     * @param productId
     */
    private void deleteProductImgList(Long productId) {
        // 根据productId获取原来的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        // 干掉原来的图片
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        // 删除数据库里原有图片的信息
        productImgDao.deleteProductImgByProductId(productId);
    }
}
