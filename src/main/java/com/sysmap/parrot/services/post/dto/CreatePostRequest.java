package com.sysmap.parrot.services.post.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreatePostRequest {
    public String pictureUrl;
    public String body;
    public String authorId;
}
