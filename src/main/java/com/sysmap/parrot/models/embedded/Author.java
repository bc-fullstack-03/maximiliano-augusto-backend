package com.sysmap.parrot.models.embedded;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Author {
    @Id
    private UUID id;
    private String name;
    private String profilePictureUrl;

    public Author(UUID id, String name, String profilePictureUrl){
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
    }
}
