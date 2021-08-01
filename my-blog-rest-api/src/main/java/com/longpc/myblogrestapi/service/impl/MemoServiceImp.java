package com.longpc.myblogrestapi.service.impl;

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
    public void insert(MemoDTO memoDTO, List<MultipartFile> image) {
        MemoEntity memoEntity = modelMapper.map(memoDTO,MemoEntity.class);
        memoEntity.setCreateAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        memoEntity.setId(UUID.randomUUID().toString());
        memoRepo.save(memoEntity);


    }

    @Override
    public List<MemoEntity> getAll() {
        return null;
    }
}
