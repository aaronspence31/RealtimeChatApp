package com.spence.aaron.ChatAppBackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "message_entity")
public class MessageEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private String timestamp;

    @ManyToOne
    private UserEntity sender;

    @ManyToOne
    private ChatRoomEntity chatRoom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public ChatRoomEntity getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoomEntity chatRoom) {
        this.chatRoom = chatRoom;
    }
}
