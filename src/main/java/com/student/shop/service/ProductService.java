/**
 * 
 */
package com.student.shop.service;

import com.student.shop.repository.ProductRepository;
import com.student.shop.repository.ProductTypeRepository;
import com.student.shop.common.Page;
import com.student.shop.model.Product;
import com.student.shop.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Student
 * @email student@test.com
 * @date 2023-3-17
 * 
 */
@Service
@Transactional
public class ProductService {

	@Autowired
    ProductTypeRepository productTypeDao;

	@Autowired
    ProductRepository productDao;

	public void saveType(ProductType type) {
		productTypeDao.save(type);
	}

	public List<ProductType> findType() {
		return productTypeDao.findAll();
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public Product findById(Integer id) {
		return productDao.getOne(id);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

	public List<Product> findNew() {
		return productDao.findByOrderByCreateTimeDesc();
	}
    public List<Product> findOld() {
        return productDao.findByOrderByCreateTimeAsc();
    }
	
	public List<Product> findPop(){
		return productDao.findPopProducts();
	}

    public List<Product> findProducts(Page<Product> page) {
        page.setResult(productDao.findAll(page.getPageable()).getContent());
        page.setTotalCount(productDao.count());
        return page.getResult();
    }
}
