package com.mulitpleDB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailDTO {

    private int id;

    private String name;

    private String category;

    private String price;
    
    private String description;
}
