package com.longpc.myblogrestapi.repository;

import com.longpc.myblogrestapi.entity.BlogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository<BlogEntity,String> {
    List<BlogEntity> findByCategoryId(String categoryId, Pageable pageble);
}
