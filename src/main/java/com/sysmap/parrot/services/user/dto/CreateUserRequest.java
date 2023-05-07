package com.sysmap.parrot.services.user.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    public String name;
    public String email;
    public String password;
}
