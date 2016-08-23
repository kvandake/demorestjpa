package com.example.repository;


import com.example.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by home on 29.01.16.
 */
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByEmail(String emailAddress);

}
