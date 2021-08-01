package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.constant.FileConstant;
import com.longpc.myblogrestapi.constant.PathConstant;
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

    //"blog_"
    public Map<String, String> saveImage(String itemId, List<MultipartFile> images, String prefixFolder, String prefixPath) throws Exception {
        File uploadRootDir = new File(PATH_IMAGE);
        // create folder save image
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdir();
        }
        // create blog folder in folder image
        File uploadBlogDir = new File(PATH_IMAGE + File.separator + prefixFolder + itemId);
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
                paths.put(name, serverPathImageAccess(itemId, name, prefixPath));
            }
        }
        return paths;
    }

    public Map<String, String> saveImageEditor(MultipartFile multipartFile) throws Exception {
        File uploadRootDir = new File(PATH_IMAGE);
        // create folder save image
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdir();
        }
        // create blog folder in folder image
        File uploadBlogDir = new File(PATH_IMAGE + File.separator + FileConstant.EDITOR_IMAGE_FOLDER);
        if (!uploadBlogDir.exists()) {
            uploadBlogDir.mkdir();
        }
        Map<String, String> paths = new HashMap<>();
        //file image
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + "." + extension;

        File serverFile = new File(uploadBlogDir.getAbsolutePath() + File.separator + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(multipartFile.getBytes());
        stream.close();
        paths.put(multipartFile.getOriginalFilename(), serverPathImageAccessEditor(fileName, PathConstant.EDITOR_PATH_ACCESS_IMAGE));
        return paths;
    }

    // "/image-show"
    private String serverPathImageAccess(String itemId, String imageName, String prefixPath) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/image" + "/" + prefixPath + "/" + itemId + "/" + imageName).toUriString();
    }

    private String serverPathImageAccessEditor(String imageName, String prefixPath) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/image" + "/" + prefixPath + "/" + imageName).toUriString();
    }


    public byte[] getImageFromIdAndName(String prefix, String itemId, String imageName) throws Exception {
        String path = PATH_IMAGE + File.separator + prefix + itemId + File.separator + imageName;
        return Files.readAllBytes(Paths.get(path));
    }

    public byte[] getEditorImageFromName(String imageName) throws Exception {
        String path = PATH_IMAGE + File.separator + FileConstant.EDITOR_IMAGE_FOLDER + File.separator + imageName;
        return Files.readAllBytes(Paths.get(path));
    }

    public String getPathFolder(String itemId, String prefix) {
        return PATH_IMAGE + File.separator + prefix + itemId;
    }

}
