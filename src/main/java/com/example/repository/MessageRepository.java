package com.example.repository;

import com.example.entity.MessageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Экземпляр репозитория сам создается
 */
@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
}
