package com.longpc.myblogrestapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImageService {
    Map<String, String> saveImage(String blogId, List<MultipartFile> images) throws Exception;

    Map<String, String> saveImage(MultipartFile image) throws Exception;
}
