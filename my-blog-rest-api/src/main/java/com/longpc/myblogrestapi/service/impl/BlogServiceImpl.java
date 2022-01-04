package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.constant.FileConstant;
import com.longpc.myblogrestapi.constant.PathConstant;
import com.longpc.myblogrestapi.dto.BlogDTO;
import com.longpc.myblogrestapi.entity.BlogEntity;
import com.longpc.myblogrestapi.repository.BlogRepo;
import com.longpc.myblogrestapi.service.BlogService;
import com.longpc.myblogrestapi.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    public String save(BlogDTO blogDTO, List<MultipartFile> imagesShowList) throws Exception {
        BlogEntity blogEntity= modelMapper.map(blogDTO,BlogEntity.class);
        if(!StringUtils.hasLength(blogDTO.getId())){
            String id=UUID.randomUUID().toString();
            blogEntity.setId(id);
        }
        blogEntity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        blogRepo.save(blogEntity);
        if(imagesShowList!=null){
            Map<String,String> path= imageService.saveImage(blogEntity.getId(),imagesShowList, FileConstant.BLOG_IMAGE_FOLDER_PREFIX, PathConstant.BLOG_PATH_ACCESS_IMAGE);
            blogEntity.setImageShow(path.get(imagesShowList.get(0).getOriginalFilename()));
            blogRepo.save(blogEntity);
        }
        return blogEntity.getId();
    }
    public String update(BlogDTO blogDTO, List<MultipartFile> imagesShowList) throws Exception {
        BlogEntity blogExisted=getById(blogDTO.getId());
        blogExisted.setTitle(blogDTO.getTitle());
        blogExisted.setContent(blogDTO.getContent());
        blogExisted.setCategoryId(blogDTO.getCategoryId());
        if(imagesShowList!=null){
            Map<String,String> path= imageService.saveImage(blogExisted.getId(),imagesShowList, FileConstant.BLOG_IMAGE_FOLDER_PREFIX, PathConstant.BLOG_PATH_ACCESS_IMAGE);
            blogExisted.setImageShow(path.get(imagesShowList.get(0).getOriginalFilename()));
        }
        blogRepo.save(blogExisted);
        return blogExisted.getId();
    }
    public List<BlogEntity> getLazyByCategoryId(String categoryId,int page, int limit){
       return blogRepo.findByCategoryId(categoryId, PageRequest.of(page,limit));
    }
    public List<BlogEntity> getAll(){
        return blogRepo.findAll();
    }

    @Override
    public boolean delete(String id) throws Exception{
        blogRepo.deleteById(id);
        return imageService.deleteImage(id, FileConstant.BLOG_IMAGE_FOLDER_PREFIX);
    }
    @Override
    public BlogEntity getById(String id) throws Exception{
        Optional<BlogEntity> blogEntity= blogRepo.findById(id);
        if(blogEntity.isPresent()){
            return blogEntity.get();
        }
        return null;
    }
}
