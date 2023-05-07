package com.sysmap.parrot.services.user.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class ReadUserResponse {
    private UUID id;
    private String name;
    private String email;
    private ArrayList<UUID> followers;
    private ArrayList<UUID> following;

    public ReadUserResponse(UUID id,
                            String name,
                            String email,
                            ArrayList<UUID> followers,
                            ArrayList<UUID> following){
        this.id = id;
        this.name = name;
        this.email = email;
        this.followers = followers;
        this.following = following;
    }
}