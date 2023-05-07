package com.sysmap.parrot.services.user.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    public String id;
    public String name;
    public String email;
}
