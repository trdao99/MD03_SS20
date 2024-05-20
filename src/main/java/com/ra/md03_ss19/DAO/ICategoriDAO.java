package com.ra.md03_ss19.DAO;

import com.ra.md03_ss19.entity.Category;

import java.util.List;

public interface ICategoriDAO {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    Boolean saveCategory(Category category);
    Boolean updateCategory(Category category);
    void deleteCategory(int id);
}
