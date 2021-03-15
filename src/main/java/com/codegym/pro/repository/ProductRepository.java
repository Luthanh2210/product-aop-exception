package com.codegym.pro.repository;

import com.codegym.pro.model.Product;
import com.codegym.pro.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Iterable<Product> findAllByCategory(Category category);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);


    //top 5 san pham theo gia
    @Query(value = "select * from product order by price desc limit ?", nativeQuery = true)
    List<Product> getProductByPriceExp(int limit);

//    //repo ho tro
//    List<Product> findTop5ByOrderByPriceDesc();
//
    //top 5 san pham theo thoi gian
    List<Product> findTop5ByOrderByDateTimeDesc();

    //tong tien
    @Query(value = "select sum(price) from Product ", nativeQuery = false)
    int getSumPrice();

}
