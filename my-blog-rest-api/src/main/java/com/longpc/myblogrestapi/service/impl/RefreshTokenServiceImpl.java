package com.longpc.myblogrestapi.service.impl;

import com.longpc.myblogrestapi.bean.JwtCustomBean;
import com.longpc.myblogrestapi.dto.AuthenResponseDTO;
import com.longpc.myblogrestapi.entity.AuthenEntity;
import com.longpc.myblogrestapi.entity.RefreshTokenEntity;
import com.longpc.myblogrestapi.repository.AuthenRepo;
import com.longpc.myblogrestapi.repository.RefreshTokenRepo;
import com.longpc.myblogrestapi.service.RefreshTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${jwt.custom.expired}")
    private Long refreshTokenDurationMs;
    @Autowired
    private JwtCustomBean jwtCustomBean;
    @Autowired
    private RefreshTokenRepo refreshTokenRepository;
    @Autowired
    private AuthenRepo authenRepo;
    @Autowired
    ModelMapper modelMapper;


    public RefreshTokenEntity findByToken(String token) {
        return refreshTokenRepository.findByValue(token);
    }

    public String reNewToken(String refreshToken){
        RefreshTokenEntity refreshTokenEntity= findByToken(refreshToken);
        String authenId=refreshTokenEntity.getAuthenId();
        Optional<AuthenEntity> authenEntityOption= authenRepo.findById(authenId);
        if(authenEntityOption.isPresent()){
            AuthenResponseDTO authenResponseDTO= modelMapper.map(authenEntityOption.get(), AuthenResponseDTO.class);
            String token= jwtCustomBean.generateJwtToken(authenResponseDTO);
            return token;
        }
        return null;
    }

    public RefreshTokenEntity createRefreshToken(String userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        RefreshTokenEntity checkExist=refreshTokenRepository.findByAuthenId(userId);
        if(checkExist!=null){
            refreshTokenRepository.delete(checkExist);
        }
        refreshToken.setAuthenId(userId);
        refreshToken.setExpiration(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setValue(UUID.randomUUID().toString());
        refreshToken.setId(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

//    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
//        if (token.getExpiration().compareTo(Instant.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new TokenRefreshException(token.getValue(), "Refresh token was expired. Please make a new signin request");
//        }
//
//        return token;
//    }

}
