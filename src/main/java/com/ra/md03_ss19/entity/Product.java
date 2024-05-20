package com.ra.md03_ss19.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name is empty!")
    @Column(name = "name")
    private String name;
    @NotNull(message = "price is empty!")
    @Column(name = "price")
    private Double price;
    @NotNull(message = "stock is empty!")
    @Column(name = "stock")
    private Integer stock;
    @NotNull(message = "status is empty!")
    @Column(name = "status")
    private Boolean status;
    @Column(name = "imgName")
    @NotEmpty(message = "img is empty!")
    private String ingName;
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category cate;
}
