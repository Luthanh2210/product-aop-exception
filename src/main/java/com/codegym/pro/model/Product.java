package com.codegym.pro.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long price;
    private String description;
    private Long quantity;


    private Date dateTime;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(){

    }
    public Product(String name, Long price, String description,  Date dateTime, Long quantity, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.dateTime = dateTime;
        this.quantity = quantity;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Category getCategory_id() {
        return category;
    }

    public void setCategory_id(Long category_id) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}
