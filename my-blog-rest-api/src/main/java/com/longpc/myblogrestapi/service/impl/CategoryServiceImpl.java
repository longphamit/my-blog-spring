package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.dto.CategoryDTO;
import com.longpc.myblogrestapi.entity.CategoryEntity;
import com.longpc.myblogrestapi.repository.CategoryRepo;
import com.longpc.myblogrestapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;
    public String insert(CategoryDTO categoryDTO){
        String id=UUID.randomUUID().toString();
        CategoryEntity categoryEntity= modelMapper.map(categoryDTO, CategoryEntity.class);
        categoryEntity.setId(id);
        categoryRepo.save(categoryEntity);
        return id;
    }
    public List<CategoryEntity> getAll(){
        return categoryRepo.findAll();
    }
}
