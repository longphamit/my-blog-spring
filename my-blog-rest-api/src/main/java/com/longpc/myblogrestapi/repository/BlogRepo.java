package com.longpc.myblogrestapi.repository;

import com.longpc.myblogrestapi.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<BlogEntity,String> {
}
