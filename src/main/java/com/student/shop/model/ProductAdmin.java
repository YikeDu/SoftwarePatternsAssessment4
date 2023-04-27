package com.student.shop.model;

import com.student.shop.common.Page;
import com.student.shop.service.PictureService;
import com.student.shop.service.ProductService;
import com.student.shop.util.AdminUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.student.shop.model.ProductFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping(value = "/admin/product")
public class ProductAdmin{

    public String uploadingDir;

    private final ProductService productService;
    private final PictureService pictureService;
    private final ProductFactory productFactory;

    public ProductAdmin(ProductService productService, PictureService pictureService, ProductFactory productFactory) {
        this.productService = productService;
        this.pictureService = pictureService;
        this.productFactory = productFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView admin(ModelAndView model, HttpSession session, HttpServletRequest request) {
        Page<Product> page = new Page<Product>(request);
        productService.findProducts(page);
        model.addObject("page", page);
        model.setViewName("product/productAdmin");
        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newForm(HttpSession session) {
        if (AdminUtil.getAdminFromSession(session) == null) {
            return "redirect:/admin/login?error=true";
        }
        return "product/productNew";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String doNew(ProductDTO productDTO, HttpSession session, @RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            uploadImage(productDTO, session, file);
        }
        Product product = productFactory.createProduct(productDTO);
        product.setInputUser(AdminUtil.getAdminFromSession(session));
        product.setCreateTime(new Date());
        productService.save(product);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView model, @PathVariable Integer id) {
        Product product = productService.findById(id);
        ProductDTO productDTO = productFactory.createProductDTO(product);
        model.addObject("product", productDTO);
        model.setViewName("admin/product/productEdit");
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView doEdit(ModelAndView model, ProductDTO productDTO, HttpSession session, @RequestParam(name = "file", required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            uploadImage(productDTO, session, file);
        }
        Product product = productFactory.createProduct(productDTO);
        product.setInputUser(AdminUtil.getAdminFromSession(session));
        productService.save(product);
        model.setViewName("redirect:/admin/product");
        return model;
    }

    private void uploadImage(ProductDTO productDTO, HttpSession session, MultipartFile file) {
        String fileName = generateFileName();

    }

    private String generateFileName() {
        return new Date().getTime() + ".jpg";
    }
}
