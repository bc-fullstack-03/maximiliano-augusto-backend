package com.sysmap.parrot.services.user.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class UpdateUserRequest {
    public UUID id;
    public String name;
    public String email;
}
