package com.thilshan.billingsoftware.controller;

import com.thilshan.billingsoftware.io.CategoryRequest;
import com.thilshan.billingsoftware.io.CategoryResponse;
import com.thilshan.billingsoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest request) {
       return categoryService.add(request);
    }
}
