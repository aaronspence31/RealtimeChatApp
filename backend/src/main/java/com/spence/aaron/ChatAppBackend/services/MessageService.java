package com.spence.aaron.ChatAppBackend.services;

import com.spence.aaron.ChatAppBackend.entities.MessageEntity;
import com.spence.aaron.ChatAppBackend.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageEntity createMessage(MessageEntity message) {
        return messageRepository.save(message);
    }

    public Optional<MessageEntity> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public List<MessageEntity> getAllMessages() {
        return messageRepository.findAll();
    }

    public MessageEntity updateMessage(MessageEntity message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
