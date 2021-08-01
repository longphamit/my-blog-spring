package com.longpc.myblogrestapi.resource;

import com.longpc.myblogrestapi.constant.FileConstant;
import com.longpc.myblogrestapi.service.ImageService;
import com.longpc.myblogrestapi.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageResource {
    @Autowired
    ImageService imageService;
    @GetMapping(path="/blog/{blogId}/{imageName}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity getImageBlog(@PathVariable("blogId")String blogId,@PathVariable("imageName")String imageName){
        try{
            return ResponseEntity.ok().body(imageService.getImageFromIdAndName(FileConstant.BLOG_IMAGE_FOLDER_PREFIX,blogId,imageName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
    @GetMapping(path="/editor/{imageName}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity getImageEditor(@PathVariable("imageName")String imageName){
        try{
            return ResponseEntity.ok().body(imageService.getEditorImageFromName(imageName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
}
