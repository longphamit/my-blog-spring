package com.longpc.myblogrestapi.resource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryResource {
    @GetMapping
    public void getCategory(){

    }
    @PostMapping
    public void addCategory(@RequestBody RequestBody requestBody){

    }
    @PutMapping
    public void updateCategory(){

    }
}
