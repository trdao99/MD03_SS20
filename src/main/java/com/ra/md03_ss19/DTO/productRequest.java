package com.ra.md03_ss19.DTO;

import com.ra.md03_ss19.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class productRequest {
    private int id;
    @NotEmpty(message = "Name is empty!")
    private String name;
    @NotNull(message = "price is empty!")
    private Double price;
    @NotNull(message = "stock is empty!")
    private Integer stock;
    @NotNull(message = "status is empty!")
    private Boolean status;
    @NotNull(message = "cate is empty!")
    private Integer cate;
    @NotNull(message = "img is empty!")
    private MultipartFile ingName;
}
