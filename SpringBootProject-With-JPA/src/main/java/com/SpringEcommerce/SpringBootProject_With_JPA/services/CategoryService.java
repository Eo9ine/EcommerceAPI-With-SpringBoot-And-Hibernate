package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.repository.DBRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CategoryService implements CartegoryServiceInterface{

    DBRepository dbRepository;

    @Override
    public List<Category> getCategories() {
        return dbRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        dbRepository.save(category);
    }

    @Override
    public String removeCategory(Long id) {
        Optional<Category> catId = dbRepository.findById(id);

        if(catId.isPresent())
        {
            Category existingCategory = catId.get();
            dbRepository.delete(existingCategory);
            return "Successfully deleted Category";
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found.");
        }
    }

    @Override
    public String updateCategory(Category category, Long id) {
        Optional<Category> categoryId = dbRepository.findById(id);

        if(categoryId.isPresent())
        {
            Category existingCategory = categoryId.get();
            existingCategory.setCategoryName(category.getCategoryName());
            dbRepository.save(existingCategory);
            return "Category successfully updated";
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found.");
        }
    }
}
