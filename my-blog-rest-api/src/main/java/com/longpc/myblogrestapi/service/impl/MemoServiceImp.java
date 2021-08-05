package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.constant.FileConstant;
import com.longpc.myblogrestapi.constant.PathConstant;
import com.longpc.myblogrestapi.dto.MemoDTO;
import com.longpc.myblogrestapi.entity.MemoEntity;
import com.longpc.myblogrestapi.repository.MemoRepo;
import com.longpc.myblogrestapi.service.ImageService;
import com.longpc.myblogrestapi.service.MemoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MemoServiceImp implements MemoService {
    @Autowired
    private MemoRepo memoRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ImageService imageService;
    @Override
    public String insert(MemoDTO memoDTO, List<MultipartFile> image) throws Exception{
        MemoEntity memoEntity = modelMapper.map(memoDTO,MemoEntity.class);
        String id=UUID.randomUUID().toString();
        memoEntity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        memoEntity.setId(id);
        memoRepo.save(memoEntity);
        Map<String,String> path= imageService.saveImage(memoEntity.getId(),image, FileConstant.MEMO_IMAGE_FOLDER_PREFIX, PathConstant.MEMO_PATH_ACCESS_IMAGE);
        memoEntity.setImage(path.get(image.get(0).getOriginalFilename()));
        memoRepo.save(memoEntity);
        return id;
    }

    @Override
    public List<MemoEntity> getAll() {
        return memoRepo.findAll();
    }

    @Override
    public boolean delete(String id) throws Exception{
        memoRepo.deleteById(id);
        return imageService.deleteImage(id,FileConstant.MEMO_IMAGE_FOLDER_PREFIX);
    }
}
