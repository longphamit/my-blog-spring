package com.longpc.myblogrestapi.repository;

import com.longpc.myblogrestapi.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepo extends JpaRepository<MemoEntity,String> {
}
