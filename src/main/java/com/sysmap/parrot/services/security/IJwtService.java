package com.sysmap.parrot.services.security;

import java.util.UUID;

public interface IJwtService {
    public String generateToken(UUID userId);
    public boolean isValidToken(String token, String userId);
}
