package com.sitiapp.pruebatecnicasitiapp.dto;

import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private Product product;

    private ImageDto image;

}
