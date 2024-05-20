package com.ra.md03_ss19.controller;

import com.ra.md03_ss19.DAO.impl.CategoryDAOimpl;
import com.ra.md03_ss19.DAO.impl.ProductDAOimpl;
import com.ra.md03_ss19.DTO.productRequest;
import com.ra.md03_ss19.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
    @Autowired
    ProductDAOimpl productDAOimpl;
    @Autowired
    CategoryDAOimpl categorydaoimpl;
    @RequestMapping("/product")
    public String product(Model model) {
        model.addAttribute("product", productDAOimpl.getAllProducts());
        return "product/list";
    }

    @RequestMapping("/addinitPro")
    public String addProduct(Model model) {
        model.addAttribute("category", categorydaoimpl.getAllCategories() );
        model.addAttribute("product", new productRequest());
        return "product/add";
    }

    @RequestMapping("/addPro")
    public String addProduct(productRequest productRq, HttpServletRequest request  ) {
        productDAOimpl.addProduct(productDAOimpl.swichToProduct(productRq,request));
        return "redirect:/product";
    }
    @RequestMapping("/editProductinit/{id}")
    public String editProduct(Model model, @PathVariable int id) {
        Product p = productDAOimpl.getProductById(id);
        model.addAttribute("currFile", p.getIngName());
        model.addAttribute("category", categorydaoimpl.getAllCategories());
        model.addAttribute("product", p);
        return "product/edit";
    }


    @RequestMapping("/updatePro")
    public String updatePro(productRequest productRequest, HttpServletRequest request) {
        Product p = productDAOimpl.swichToProduct(productRequest,request);
        productDAOimpl.updateProduct(p);
        return "redirect:/product";
    }


    @RequestMapping("/delProduct/{id}")
    public String delProduct(@PathVariable int id) {
        productDAOimpl.deleteProduct(id);
        return "redirect:/product";
    }
}
