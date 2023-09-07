package com.spence.aaron.ChatAppBackend.controllers;

import com.spence.aaron.ChatAppBackend.entities.MessageEntity;
import com.spence.aaron.ChatAppBackend.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public MessageEntity createMessage(@RequestBody MessageEntity message) {
        return messageService.createMessage(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageEntity> getMessageById(@PathVariable Long id) {
        Optional<MessageEntity> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<MessageEntity> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PutMapping("/{id}")
    public MessageEntity updateMessage(@PathVariable Long id, @RequestBody MessageEntity message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
