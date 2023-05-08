package com.sysmap.parrot.models.embedded;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class Follows {
    @Id
    private UUID userId;
    @Field
    private ArrayList<UUID> following;

    public Follows(UUID userId){
        this.userId = userId;
        this.following = new ArrayList<>();
    }
}
