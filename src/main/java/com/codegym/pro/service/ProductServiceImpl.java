package com.codegym.pro.service;

import com.codegym.pro.model.Category;
import com.codegym.pro.model.Product;
import com.codegym.pro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }



    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }


    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }



    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public List<Product> findTop5() {
        return productRepository.getProductByPriceExp(5);
    }

    @Override
    public List<Product> findTop5ByDate() {
        return productRepository.findTop5ByOrderByDateDesc();
    }

    @Override
    public int getSumPrice() {
        int sum = 0;
        sum = productRepository.getSumPrice();
        return sum;
    }



}
