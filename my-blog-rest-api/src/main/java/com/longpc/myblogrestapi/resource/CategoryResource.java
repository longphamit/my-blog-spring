package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.dto.CategoryDTO;
import com.longpc.myblogrestapi.entity.CategoryEntity;
import com.longpc.myblogrestapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryResource {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity getCategory(){
        List<CategoryEntity> categoryEntityList= categoryService.getAll();
        return ResponseEntity.ok().body(categoryEntityList);
    }
    @PostMapping("/auth")
    public ResponseEntity addCategory(@RequestBody CategoryDTO categoryDTO){
        if(!StringUtils.hasLength(categoryDTO.getName())){
            return ResponseEntity.badRequest().build();
        }
        String id=categoryService.insert(categoryDTO);
        return ResponseEntity.ok().body(id);
    }
    @PutMapping
    public void updateCategory(){

    }
}
