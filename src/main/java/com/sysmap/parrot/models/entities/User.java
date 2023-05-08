package com.sysmap.parrot.models.entities;

import com.sysmap.parrot.models.embedded.Feed;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.UUID;

@Document
@Data
public class User {
    @Id
    private UUID id;
    @Field
    private String name;
    @Field
    private String email;
    @Field
    private String password;
    @Field
    private String photoUrl;
    private Feed feed;
    public User(){

    }

    public User(String name, String email){
        setId();
        this.name = name;
        this.email = email;
        this.photoUrl = " ";
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return  this.id;
    }
}
