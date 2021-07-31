package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.BlogDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    void save(BlogDTO blogDTO, List<MultipartFile> imagesShowList) throws Exception;
}
