package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.dto.BlogDTO;
import com.longpc.myblogrestapi.entity.BlogEntity;
import com.longpc.myblogrestapi.entity.ImageEntity;
import com.longpc.myblogrestapi.repository.BlogRepo;
import com.longpc.myblogrestapi.service.BlogService;
import com.longpc.myblogrestapi.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ImageService imageService;
    @Override
    public void save(BlogDTO blogDTO, List<MultipartFile> imagesShowList) throws Exception {
        BlogEntity blogEntity= modelMapper.map(blogDTO,BlogEntity.class);
        blogEntity.setId(UUID.randomUUID().toString());
        blogEntity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        imageService.saveImage(blogEntity.getId(),imagesShowList);
        blogRepo.save(blogEntity);
        Map<String,String> path= imageService.saveImage(blogEntity.getId(),imagesShowList);
        blogEntity.setImageShow(path.get(imagesShowList.get(0).getOriginalFilename()));
        blogRepo.save(blogEntity);
    }
    public List<BlogEntity> getLazyByCategoryId(String categoryId,int page, int limit){
       return blogRepo.findByCategoryId(categoryId, PageRequest.of(page,limit));
    }
}
