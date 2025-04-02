package com.SpringEcommerce.SpringBootProject_With_JPA.payload;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class CategoryResponse {
    List<CategoryDTO> content;
    public Integer pageNumber;
    public Integer pageSize;
    public Integer totalElements;
    public boolean lastPage;

    public CategoryResponse(List<CategoryDTO> content, boolean lastPage, Integer totalElements, Integer pageSize, Integer pageNumber) {
        this.content = content;
        this.lastPage = lastPage;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public CategoryResponse() {
    }

    public List<CategoryDTO> getContent() {
        return content;
    }

    public void setContent(List<CategoryDTO> content) {
        this.content = content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
