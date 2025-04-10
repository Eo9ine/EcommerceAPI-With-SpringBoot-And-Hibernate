package com.SpringEcommerce.SpringBootProject_With_JPA.payload;

import java.util.List;

public class ProductResponse {
    private List<ProductDTO> productDTOS;

    public ProductResponse(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    public ProductResponse() {
    }

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}
