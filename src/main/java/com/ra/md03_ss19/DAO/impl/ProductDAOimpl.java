package com.ra.md03_ss19.DAO.impl;

import com.google.protobuf.Empty;
import com.ra.md03_ss19.DAO.IProductDAO;
import com.ra.md03_ss19.DTO.productRequest;
import com.ra.md03_ss19.entity.Category;
import com.ra.md03_ss19.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOimpl implements IProductDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    CategoryDAOimpl categoryDAOimpl;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Session session = sessionFactory.openSession();
        products = session.createQuery("from Product").getResultList();
        session.close();
        return products;
    }

    @Override
    public Product getProductById(int id) {
        Session session = sessionFactory.openSession();
        Product product = (Product) session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public Boolean updateProduct(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public void deleteProduct(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(getProductById(id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Boolean addProduct(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }

    public Product swichToProduct(productRequest productRequest, HttpServletRequest request)  {
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setStatus(productRequest.getStatus());
        product.setCate(categoryDAOimpl.getCategoryById(productRequest.getCate()));
        String path = request.getServletContext().getRealPath("/images");

        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdir();
        }
        MultipartFile imgFile = productRequest.getIngName();
        //lấy tên file file cần upload lên
        if (imgFile != null && !imgFile.isEmpty()) {
            String fileName = imgFile.getOriginalFilename();
            try {
                File destination = new File(file1.getAbsolutePath() + "/" + fileName);
                if (!destination.exists()) {
                    FileCopyUtils.copy(imgFile.getBytes(), destination);
                }
                product.setIngName(fileName);
                return product;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
                product.setIngName(getProductById(productRequest.getId()).getIngName());
                return product;
        }
    }
}
