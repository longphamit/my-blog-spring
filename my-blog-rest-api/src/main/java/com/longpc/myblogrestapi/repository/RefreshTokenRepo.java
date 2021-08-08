package com.longpc.myblogrestapi.repository;

import com.longpc.myblogrestapi.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshTokenEntity, String> {
    void deleteByAuthenId(String id);
    RefreshTokenEntity findByValue(String token);
    RefreshTokenEntity findByAuthenId(String authenId);
}
