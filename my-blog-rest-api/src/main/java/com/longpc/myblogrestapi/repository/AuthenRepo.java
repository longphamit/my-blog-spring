package com.longpc.myblogrestapi.repository;

import com.longpc.myblogrestapi.entity.AuthenEntity;
import com.longpc.myblogrestapi.repository.custom.CustomInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenRepo extends JpaRepository<AuthenEntity,String> {
    AuthenEntity findByEmail(String Email);
}
