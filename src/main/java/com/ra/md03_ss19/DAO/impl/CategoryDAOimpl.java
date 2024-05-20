package com.ra.md03_ss19.DAO.impl;

import com.ra.md03_ss19.DAO.ICategoriDAO;
import com.ra.md03_ss19.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOimpl implements ICategoriDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        Session session = sessionFactory.openSession();
        categories = session.createQuery("from Category").list();
        session.close();
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        Session session = sessionFactory.openSession();
        Category category = (Category) session.get(Category.class, id);
        session.close();
        return category;
    }

    @Override
    public Boolean saveCategory(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(category);
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
    public Boolean updateCategory(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(category);
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
    public void deleteCategory(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(getCategoryById(id));
        session.getTransaction().commit();
        session.close();
    }
}
