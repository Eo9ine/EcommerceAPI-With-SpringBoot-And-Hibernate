package com.SpringEcommerce.SpringBootProject_With_JPA.payload;

import java.util.List;

public class ProductResponse {
    private List<ProductDTO> content;

    public ProductResponse(List<ProductDTO> productDTOS) {
        this.content = productDTOS;
    }

    public ProductResponse() {
    }

    public List<ProductDTO> getProductDTOS() {
        return content;
    }

    public void setContent(List<ProductDTO> productDTOS) {
        this.content = productDTOS;
    }
}

