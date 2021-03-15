package com.codegym.pro.formatter;

import com.codegym.pro.model.Category;
import com.codegym.pro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
// ko thuộc những tầng đã đc định nghĩa trong spring
public class CategoryFormatter implements Formatter<Category> {
    private CategoryService categoryService;

    @Autowired
    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Category object, Locale locale) {
        return  "[" + object.getId() + ", " +object.getName() + "]";
    }
}
