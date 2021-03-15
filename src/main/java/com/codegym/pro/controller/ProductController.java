package com.codegym.pro.controller;

import com.codegym.pro.exception.NotFoundException;
import com.codegym.pro.model.Product;
import com.codegym.pro.model.Category;
import com.codegym.pro.service.ProductService;
import com.codegym.pro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categorylist")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("error");
    }

    //region INSERT
    @GetMapping("/add")
    public ModelAndView showInsertForm() {
        ModelAndView modelAndView = new ModelAndView("product/add");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }


    //top 5 product by price
    @GetMapping("/top5")
    public ModelAndView showTop5() {
        ModelAndView modelAndView = new ModelAndView("líst");
        modelAndView.addObject("productList", productService.findTop5());
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    //top 5 product by date
    @GetMapping("top5bydate")
    public ModelAndView showTop5ByDate(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("productList", productService.findTop5ByDate());
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    //tong tien
    @GetMapping("sum")
    public ModelAndView getSumPrice(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("sum", productService.getSumPrice());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView insertProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/add");
        modelAndView.addObject("message", "Product created successfully");
        return modelAndView;

    }
    //endregion
    @GetMapping
    public ModelAndView showAllProduct(@RequestParam("s")Optional<String> name, @PageableDefault(size = 5) Pageable pageable ){
        Page<Product> products;
        if(name.isPresent()){
            products = productService.findAllByNameContaining(name.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:/products";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailProduct(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("product/detail");
            modelAndView.addObject("product", product);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

}
