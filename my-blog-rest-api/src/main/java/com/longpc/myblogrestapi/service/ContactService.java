package com.longpc.myblogrestapi.service;

import com.longpc.myblogrestapi.dto.ContactDTO;
import com.longpc.myblogrestapi.entity.ContactEntity;

import java.util.List;

public interface ContactService {
    void insert(ContactDTO contactDTO);

    List<ContactEntity> getAll();

    void deletedById(String id);
}
