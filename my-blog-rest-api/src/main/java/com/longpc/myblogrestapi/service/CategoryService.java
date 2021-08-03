package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.CategoryDTO;
import com.longpc.myblogrestapi.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    String insert(CategoryDTO categoryDTO);

    List<CategoryEntity> getAll();
}
