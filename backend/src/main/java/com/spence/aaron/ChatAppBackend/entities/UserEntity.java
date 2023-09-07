package com.spence.aaron.ChatAppBackend.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "sender")
    private Set<MessageEntity> messages;

    @ManyToMany(mappedBy = "members")
    private Set<ChatRoomEntity> chatRooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    public Set<ChatRoomEntity> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(Set<ChatRoomEntity> chatRooms) {
        this.chatRooms = chatRooms;
    }

    // Additional methods like equals(), hashCode(), and toString() can be added
}
