package com.ra.md03_ss19.controller;

import com.ra.md03_ss19.DAO.impl.CategoryDAOimpl;
import com.ra.md03_ss19.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryDAOimpl categorydaoimpl;
    @RequestMapping("/")
    public String index(Model model) {
        return "home";
    }

    @RequestMapping("/category")
    public String home(Model model) {
        List<Category> categories = categorydaoimpl.getAllCategories();
        model.addAttribute("list", categories);
        return "category/listCat";
    }

    @RequestMapping("/addinit")
    public String addInit(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/addCat";
    }
    @RequestMapping("addcategory")
    public String addCategory(@ModelAttribute("category") Category category, Model model) {
        categorydaoimpl.saveCategory(category);
        return "redirect:/category";
    }

    @RequestMapping("/editinit/{id}")
    public String editInit(@PathVariable int id, Model model) {
        model.addAttribute("category", categorydaoimpl.getCategoryById(id));
        return "category/editCat";
    }
    @RequestMapping("/update")
    public String update(@ModelAttribute("category") Category category, Model model) {
        categorydaoimpl.updateCategory(category);
        return "redirect:/category";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable int id) {
        categorydaoimpl.deleteCategory(id);
        return "redirect:/category";
    }
}
