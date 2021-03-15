package com.codegym.pro.controller;

import com.codegym.pro.model.Category;
import com.codegym.pro.model.Product;
import com.codegym.pro.service.ProductService;
import com.codegym.pro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView listProvinces(){
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("cagetory/list");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("category/add");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("message", "Category add successfully");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/add");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if (category != null){
            ModelAndView modelAndView = new ModelAndView("category/edit");
            modelAndView.addObject("category", category);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateProvince(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if (category != null){
            ModelAndView modelAndView = new ModelAndView("category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteProvince(@ModelAttribute("category") Category category){
        categoryService.remove(category.getId());
        return "redirect:/categories";
    }

    @GetMapping("view/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if (category == null){
            return new ModelAndView("/error.404");
        }
        Iterable<Product> products = productService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
