package com.lin.gamestore.web.stroe.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.gamestore.dto.ProductExecution;
import com.lin.gamestore.entity.Product;
import com.lin.gamestore.enums.ProductCategoryStateEnum;
import com.lin.gamestore.enums.ProductStateEnum;
import com.lin.gamestore.exceptions.ProductCategoryOperationException;
import com.lin.gamestore.exceptions.ProductOperationException;
import com.lin.gamestore.service.ProductService;
import com.lin.gamestore.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getproductlist", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getProductList() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            ProductExecution pe = productService.getProductList();
            if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
                modelMap.put("productList", pe.getProductList());
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", pe.getStateInfo());
            }

        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        if (product != null) {
//            try {
//                ProductExecution pe = productService.bindProduct(product);
//                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
//                    modelMap.put("success", true);
//                } else {
//                    modelMap.put("success", false);
//                    modelMap.put("errMsg", pe.getStateInfo());
//                }
//            } catch (ProductOperationException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.toString());
//            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "信息缺失");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        if (product != null && product.getProductId() != null) {
//            try {
//                ProductExecution pe = productService.modifProduct(product);
//                if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
//                    modelMap.put("success", true);
//                } else {
//                    modelMap.put("success", false);
//                    modelMap.put("errMsg", pe.getStateInfo());
//                }
//            } catch (ProductOperationException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.toString());
//            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "信息缺失");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> removeProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long productId = HttpServletRequestUtil.getLong(request, "productId");
        if (productId != null) {
            try {
                ProductExecution pe = productService.removeProduct(productId);
                if (pe.getState() == ProductStateEnum.FAILED.getState()) {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                } else {
                    modelMap.put("success", true);
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "信息缺失");
        }
        return modelMap;
    }
}
