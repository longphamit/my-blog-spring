package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.dto.BlogDTO;
import com.longpc.myblogrestapi.entity.BlogEntity;
import com.longpc.myblogrestapi.service.BlogService;
import com.longpc.myblogrestapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogResource {
    private static final String FOLDER_EDITOR_IMAGE="editor_image";
    private static final String PATH_EDITOR_IMAGE="editor";

    @Autowired
    BlogService blogService;
    @Autowired
    ImageService imageService;

    @PostMapping
    public ResponseEntity insert(@RequestPart(value = "imageShow", required = false) MultipartFile[] imageShow,
                                 @RequestPart(value = "blog") BlogDTO blogDTO) {
        try {
            List<MultipartFile> imageShowList = Arrays.asList(imageShow);
            blogService.save(blogDTO, imageShowList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<BlogEntity> blogEntityList = blogService.getAll();
            return ResponseEntity.ok().body(blogEntityList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{category}")
    public ResponseEntity getLazyByCategory(
            @PathVariable("category") String categoryId,
            @RequestParam int page,
            @RequestParam int limit
    ) {
        try {
            List<BlogEntity> blogEntityList= blogService.getLazyByCategoryId(categoryId,page,limit);
            return ResponseEntity.ok().body(blogEntityList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBlog(@PathVariable("id") String id) {
        try {
            if(blogService.delete(id)){
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }


    @PostMapping("/editor")
    public HashMap<String, Object> ckfinderImage(@RequestPart(value = "upload", required = false) MultipartFile image) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Map<String, String> pathMap=imageService.saveImageEditor(image);
            pathMap.get(image.getOriginalFilename());
            HashMap<String, Object> mapSub = new HashMap<>();
            mapSub.put("acl", 255);
            map.put("resourceType", "Images");
            map.put("fileName", image.getOriginalFilename());
            map.put("url", pathMap.get(image.getOriginalFilename()));
            map.put("uploaded", 1);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
