package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.CategoryDTO;
import com.longpc.myblogrestapi.entity.CategoryEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    String insert(CategoryDTO categoryDTO,List<MultipartFile> image) throws Exception;

    List<CategoryEntity> getAll();

    void deleteById(String id) throws Exception;
}
