package com.SpringEcommerce.SpringBootProject_With_JPA.services;

import com.SpringEcommerce.SpringBootProject_With_JPA.exceptions.APIException;
import com.SpringEcommerce.SpringBootProject_With_JPA.exceptions.CustomResourceNotFoundException;
import com.SpringEcommerce.SpringBootProject_With_JPA.model.Category;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryDTO;
import com.SpringEcommerce.SpringBootProject_With_JPA.payload.CategoryResponse;
import com.SpringEcommerce.SpringBootProject_With_JPA.repository.DBRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CategoryService implements CartegoryServiceInterface{


    private DBRepository dbRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryService(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @Override
    public CategoryResponse getCategories()
    {
        List<Category> categories = dbRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No category have been created !");

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO)
    {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category existingCategory = dbRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory != null)
            throw new APIException("Category " + category.getCategoryName() + " already exists !!!");

        Category savedCategory = dbRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);

    }

    @Override
    public CategoryDTO removeCategory(Long id) {
        Optional<Category> catId = dbRepository.findById(id);

        if(catId.isPresent())
        {
            Category existingCategory = catId.get();
            dbRepository.delete(existingCategory);
            return modelMapper.map(existingCategory,CategoryDTO.class);
        }
        else
        {
            throw new CustomResourceNotFoundException("Category", "category", id);
        }
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {

        Category category = modelMapper.map(categoryDTO,Category.class);
        List<Category> cat = dbRepository.findAll();

        Category existingCategory = cat.stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomResourceNotFoundException("Category","category",id));

        Category categoryName = dbRepository.findByCategoryName(category.getCategoryName());

        if(categoryName != null)
            throw new APIException("Category " + category.getCategoryName() + " already exists !!!");

        existingCategory.setCategoryName(category.getCategoryName());
        Category savedCategoryName = dbRepository.save(existingCategory);
        return modelMapper.map(savedCategoryName, CategoryDTO.class);

    }


}
