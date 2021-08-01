package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    public static final String PATH_IMAGE = System.getProperty("user.home") + "/my-blog";

    public Map<String, String> saveImage(String blogId, List<MultipartFile> images) throws Exception {
        File uploadRootDir = new File(PATH_IMAGE);
        // create folder save image
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdir();
        }
        // create blog folder in folder image
        File uploadBlogDir = new File(PATH_IMAGE + File.separator + "blog_" + blogId);
        if (!uploadBlogDir.exists()) {
            uploadBlogDir.mkdir();
        }
        Map<String, String> paths = new HashMap<>();
        for (MultipartFile multipartFile : images) {
            //file image
            String name = multipartFile.getOriginalFilename();
            if (StringUtils.hasLength(name)) {
                File serverFile = new File(serverPathImageStorage(uploadBlogDir,name));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(multipartFile.getBytes());
                stream.close();
                paths.put(name, serverPathImageAccess(blogId,name));
            }
        }
        return paths;
    }

    public Map<String, String> saveImage(MultipartFile image) throws Exception {
        File uploadRootDir = new File(PATH_IMAGE);
        // create folder save image
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdir();
        }
        // create blog folder in folder image
        File uploadBlogDir = new File(PATH_IMAGE + File.separator + "editor_image");
        if (!uploadBlogDir.exists()) {
            uploadBlogDir.mkdir();
        }
        Map<String, String> paths = new HashMap<>();
        //file image
        String name = image.getOriginalFilename();
        if (StringUtils.hasLength(name)) {
            File serverFile = new File(serverPathImageStorage(uploadBlogDir,name));
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(image.getBytes());
            stream.close();
            paths.put(name, serverPathImageEditorAccess(name));
        }
        return paths;
    }
    private String serverPathImageEditorAccess(String imageName){
        return  ServletUriComponentsBuilder.fromCurrentContextPath().path("/image"+"/editor"+"/"+imageName).toUriString();
    }

    private String serverPathImageAccess(String blogId, String imageName){
      return  ServletUriComponentsBuilder.fromCurrentContextPath().path("/image"+"/image-show"+"/"+blogId+"/"+imageName).toUriString();
    }
    private String serverPathImageStorage(File uploadBlogDir, String imageName){
        return uploadBlogDir.getAbsolutePath() + File.separator + imageName;
    }
    public byte[] getImageFromBlogIdAndName(String blogId,String imageName) throws Exception{
        String path=PATH_IMAGE + File.separator + "blog_" + blogId+File.separator+imageName;
        return Files.readAllBytes(Paths.get(path));
    }
    public byte[] getImageEditor(String imageName) throws Exception{
        String path=PATH_IMAGE + File.separator + "editor_image"+File.separator+imageName;
        return Files.readAllBytes(Paths.get(path));
    }
}
