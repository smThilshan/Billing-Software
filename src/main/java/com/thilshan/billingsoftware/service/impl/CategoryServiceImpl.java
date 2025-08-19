package com.thilshan.billingsoftware.service.impl;

import com.thilshan.billingsoftware.entity.CategoryEntity;
import com.thilshan.billingsoftware.io.CategoryRequest;
import com.thilshan.billingsoftware.io.CategoryResponse;
import com.thilshan.billingsoftware.repository.CategoryRepository;
import com.thilshan.billingsoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public CategoryResponse add(CategoryRequest request) {
        CategoryEntity newCategory =  convertToEntity(request);
        newCategory =  categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> read() {
       return categoryRepository.findAll().stream().map(categoryEntity -> convertToResponse(categoryEntity)).collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
     CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId).orElseThrow(()-> new RuntimeException("Category not found: "+ categoryId));
     categoryRepository.delete(existingCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        return CategoryResponse.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .imageUrl(newCategory.getImageUrl())
                .build();
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
       return CategoryEntity.builder().categoryId(UUID.randomUUID().toString()).name(request.getName()).description(request.getDescription()).bgColor(request.getBgColor()).build();

    }
}
