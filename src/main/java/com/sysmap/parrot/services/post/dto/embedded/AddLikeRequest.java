package com.sysmap.parrot.services.post.dto.embedded;

import lombok.Data;

import java.util.UUID;

@Data
public class AddLikeRequest {
    public UUID postId;
    public UUID authorId;
    public boolean liked;
}
