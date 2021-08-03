package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.MemoDTO;
import com.longpc.myblogrestapi.entity.MemoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemoService {
    String insert(MemoDTO memoDTO, List<MultipartFile> image) throws Exception;

    List<MemoEntity> getAll();

    boolean delete(String id) throws Exception;
}
