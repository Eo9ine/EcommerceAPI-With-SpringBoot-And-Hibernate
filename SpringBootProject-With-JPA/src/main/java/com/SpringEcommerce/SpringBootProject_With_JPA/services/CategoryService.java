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

    public CategoryService(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

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
    public Category updateCategory(Category category, Long id) {

        List<Category> cat = dbRepository.findAll();

        Category existingCategory = cat.stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with ID " + id + " not found"));

        existingCategory.setCategoryName(category.getCategoryName());

        return dbRepository.save(existingCategory);
    }
}
