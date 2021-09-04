package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.dto.ContactDTO;
import com.longpc.myblogrestapi.entity.ContactEntity;
import com.longpc.myblogrestapi.repository.ContactRepo;
import com.longpc.myblogrestapi.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void insert(ContactDTO contactDTO) {
        ContactEntity contactEntity= modelMapper.map(contactDTO,ContactEntity.class);
        contactEntity.setId(UUID.randomUUID().toString());
        contactEntity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        contactRepo.save(contactEntity);
    }

    @Override
    public List<ContactEntity> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public void deletedById(String id) {
        contactRepo.deleteById(id);
    }
}
