package com.codegym.pro.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min=2, max=30)
    private String name;
    @NotNull
    private Long price;
    private String description;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull
    @Past
    private Date dateTime;
    @NotNull
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Long category;

    public Product(){

    }
    public Product(@NotEmpty @Size(min = 2, max = 30) String name, @NotNull Long price, String description, @NotNull @Past Date dateTime, @NotNull Long quantity, Long category) {
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

    public Long getCategory_id() {
        return category;
    }

    public void setCategory_id(Long category_id) {
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
