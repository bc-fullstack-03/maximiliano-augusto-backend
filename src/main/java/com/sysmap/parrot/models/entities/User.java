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
    private String profilePictureUrl;
    @Field
    private ArrayList<UUID> following;
    @Field
    private ArrayList<UUID> followers;
    private Feed feed;
    public User(){

    }

    public User(String name, String email, String password){
        setId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = " ";
        this.following = new ArrayList<UUID>();
        this.followers = new ArrayList<UUID>();
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return  this.id;
    }
    public void addFollower(UUID id){
        this.followers.add(id);
    }

    public void addFollowed(UUID id){
        this.following.add(id);
    }

    public void removeFollower(UUID id){
        this.followers.remove(id);
    }

    public void removeFollowed(UUID id){
        this.following.remove(id);
    }
}
