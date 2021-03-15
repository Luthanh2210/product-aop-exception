package com.codegym.pro.service;

import com.codegym.pro.model.Category;
import com.codegym.pro.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void remove(Long id);

    void save(Product product);

    Iterable<Product> findAllByCategory(Category category);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);


    List<Product> findTop5();
    List<Product> findTop5ByDate();
    int getSumPrice();
}
