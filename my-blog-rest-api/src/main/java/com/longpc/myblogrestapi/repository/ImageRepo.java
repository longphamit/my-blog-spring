package com.longpc.myblogrestapi.repository;
import com.longpc.myblogrestapi.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ImageEntity,String> {
}
