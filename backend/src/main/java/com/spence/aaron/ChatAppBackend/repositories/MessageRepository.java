package com.spence.aaron.ChatAppBackend.repositories;

import com.spence.aaron.ChatAppBackend.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findBySender_Username(String username);
}
