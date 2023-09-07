package com.spence.aaron.ChatAppBackend.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "chat_room_entity")
public class ChatRoomEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    private Set<UserEntity> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(Set<UserEntity> members) {
        this.members = members;
    }
}
