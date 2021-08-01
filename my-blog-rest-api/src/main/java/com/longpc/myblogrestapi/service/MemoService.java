package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.MemoDTO;
import com.longpc.myblogrestapi.entity.MemoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemoService {
    void insert(MemoDTO memoDTO, List<MultipartFile> image);

    List<MemoEntity> getAll();
}
