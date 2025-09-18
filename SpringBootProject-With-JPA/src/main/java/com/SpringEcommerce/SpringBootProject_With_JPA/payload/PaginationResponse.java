package com.SpringEcommerce.SpringBootProject_With_JPA.payload;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginationResponse <T>{
    private List<T> content;
    public Integer pageNumber;
    public Integer pageSize;
    public Integer totalElements;
    public boolean lastPage;
}
