package com.longpc.myblogrestapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImageService {
    Map<String, String> saveImage(String itemId, List<MultipartFile> images, String prefixFolder, String prefixPathAccess) throws Exception;


    byte[] getImageFromIdAndName(String prefix, String blogId, String imageName) throws Exception;

    String getPathFolder(String itemId, String prefix);

    Map<String, String> saveImageEditor(MultipartFile multipartFile) throws Exception;

    byte[] getEditorImageFromName(String imageName) throws Exception;

    boolean deleteImage(String id, String folderPrefix) throws Exception;
}
