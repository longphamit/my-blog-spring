package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.dto.AuthenDTO;
import com.longpc.myblogrestapi.entity.AuthenEntity;
import com.longpc.myblogrestapi.repository.AuthenRepo;
import com.longpc.myblogrestapi.service.AuthenService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenServiceImpl implements AuthenService {
    @Autowired
    AuthenRepo authenRepo;
    @Autowired
    ModelMapper modelMapper;

    public AuthenEntity login(AuthenDTO authenDTO) {
        AuthenEntity authenEntity = authenRepo.findByEmail(authenDTO.getEmail());
        if(authenEntity!=null){
            boolean checking = BCrypt.checkpw(authenDTO.getPassword(), authenEntity.getPassword());
            if (checking) {
                authenEntity.setPassword("");
                return authenEntity;
            }
        }
        return null;
    }

    public AuthenEntity register(AuthenDTO authenDTO) {
        String password = BCrypt.hashpw(authenDTO.getPassword(), BCrypt.gensalt(12));
        authenDTO.setPassword(password);
        AuthenEntity authenEntity = modelMapper.map(authenDTO, AuthenEntity.class);
        authenEntity.setId(UUID.randomUUID().toString());
        authenRepo.save(authenEntity);
        return authenEntity;
    }
}
