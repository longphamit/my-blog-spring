package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.constant.FileConstant;
import com.longpc.myblogrestapi.constant.PathConstant;
import com.longpc.myblogrestapi.dto.CategoryDTO;
import com.longpc.myblogrestapi.entity.CategoryEntity;
import com.longpc.myblogrestapi.repository.CategoryRepo;
import com.longpc.myblogrestapi.service.CategoryService;
import com.longpc.myblogrestapi.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ImageService imageService;
    public String insert(CategoryDTO categoryDTO,List<MultipartFile> image) throws Exception{
        String id=UUID.randomUUID().toString();
        CategoryEntity categoryEntity= modelMapper.map(categoryDTO, CategoryEntity.class);
        categoryEntity.setId(id);
        categoryRepo.save(categoryEntity);
        Map<String,String> path= imageService.saveImage(id,image, FileConstant.CATEGORY_FOLDER_PREFIX, PathConstant.CATEGORY_PATH_ACCESS_IMAGE);
        categoryEntity.setImage(path.get(image.get(0).getOriginalFilename()));
        categoryRepo.save(categoryEntity);
        return id;
    }
    public List<CategoryEntity> getAll(){
        return categoryRepo.findAll();
    }

    @Override
    public void deleteById(String id) throws Exception {
        categoryRepo.deleteById(id);
        imageService.deleteImage(id,FileConstant.CATEGORY_FOLDER_PREFIX);
    }
}
