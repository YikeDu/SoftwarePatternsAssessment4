package com.student.shop.model;
import com.student.shop.model.Product;

import java.util.List;

public class ProductFactory {

    // 定义私有构造器，防止外部实例化
    private ProductFactory() {}

    // 静态工厂方法，返回新的 Product 实例
    public static Product createProduct(String title, Integer point, Picture masterPic, List<Picture> slavePic,
                                        String note, String code, String model, Long stock) {
        Product product = new Product();
        product.setTitle(title);
        product.setPoint(point);
        product.setMasterPic(masterPic);
        product.setSlavePic(slavePic);
        product.setNote(note);
        product.setCode(code);
        product.setModel(model);
        product.setStock(stock);
        return product;
    }


    public Product createProduct(ProductDTO productDTO) {

        return null;
    }

    public ProductDTO createProductDTO(Product product) {
        return null;
    }
}