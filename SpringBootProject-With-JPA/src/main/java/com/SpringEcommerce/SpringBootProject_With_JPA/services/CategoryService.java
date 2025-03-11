package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.exceptions.APIException;
import com.SpringEcommerce.SpringBootProject_With_JPA.exceptions.CustomResourceNotFoundException;
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
    public List<Category> getCategories()
    {
        List<Category> categories = dbRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No category have been created !");
        return categories;
    }

    @Override
    public void addCategory(Category category)
    {
        Category savedCategory = dbRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null)
            throw new APIException("Category " + category.getCategoryName() + " already exists !!!");

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
            throw new CustomResourceNotFoundException("Category", "category", id);
        }
    }

    @Override
    public Category updateCategory(Category category, Long id) {

        List<Category> cat = dbRepository.findAll();

        Category category1 = dbRepository.findByCategoryName(category.getCategoryName());

        if(category1 != null)
            throw new APIException("Category " + category.getCategoryName() + " already exists !!!");


        Category existingCategory = cat.stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomResourceNotFoundException("Category","category",id));

        existingCategory.setCategoryName(category.getCategoryName());

        return dbRepository.save(existingCategory);
    }


}
