package com.longpc.myblogrestapi.repository;

import com.longpc.myblogrestapi.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<ContactEntity,String> {
}
