package com.sysmap.parrot.services.user.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class ReadUserResponse {
    private UUID id;
    private String name;
    private String email;

    public ReadUserResponse(UUID id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;

    }
}