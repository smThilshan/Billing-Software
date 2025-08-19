package com.thilshan.billingsoftware.service;

import com.thilshan.billingsoftware.io.CategoryRequest;
import com.thilshan.billingsoftware.io.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);

    List<CategoryResponse> read();

    void delete(String categoryId);
}
