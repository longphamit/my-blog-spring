package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.BlogDTO;
import com.longpc.myblogrestapi.entity.BlogEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    String save(BlogDTO blogDTO, List<MultipartFile> imagesShowList) throws Exception;

    List<BlogEntity> getLazyByCategoryId(String categoryId, int page, int limit);

    List<BlogEntity> getAll();

    boolean delete(String id) throws Exception;

    BlogEntity getById(String id) throws Exception;
}
