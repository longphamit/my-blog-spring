package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String PATH_IMAGE = System.getProperty("user.home") + "/my-blog";

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
                File serverFile = new File(uploadBlogDir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(multipartFile.getBytes());
                stream.close();
                paths.put(name, serverFile.getPath());
            }
        }
        return paths;
    }
}
