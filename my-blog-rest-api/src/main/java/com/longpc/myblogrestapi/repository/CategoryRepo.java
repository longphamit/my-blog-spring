package com.longpc.myblogrestapi.repository;

import com.longpc.myblogrestapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity,String> {
}
