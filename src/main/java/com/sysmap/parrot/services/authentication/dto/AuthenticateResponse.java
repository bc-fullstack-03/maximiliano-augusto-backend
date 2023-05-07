package com.sysmap.parrot.services.authentication.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthenticateResponse {
    private UUID userId;
    private String token;

    public AuthenticateResponse(UUID userId, String  token){
        this.userId = userId;
        this.token = token;
    }
}
