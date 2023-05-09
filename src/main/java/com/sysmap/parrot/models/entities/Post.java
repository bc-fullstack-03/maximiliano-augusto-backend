package com.sysmap.parrot.models.entities;

import com.sysmap.parrot.models.embedded.Comment;
import com.sysmap.parrot.models.embedded.Like;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Document
@Data
public class Post implements Comparable<Post>{
    @Id
    private UUID id;
    @Field
    private String pictureUrl;
    @Field
    private String body;
    @Field
    private UUID authorId;
    @Field
    public Date date;
    @Field
    private ArrayList<Comment> comments;
    @Field
    private ArrayList<Like> likes;

    public Post(UUID authorId, String pictureUrl, String body, Date date){
        setId();
        this.authorId = authorId;
        this.pictureUrl = pictureUrl;
        this.body = body;
        this.date = date;
        comments = new ArrayList<Comment>();
        likes = new ArrayList<Like>();
    }

    public Post(){}

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public ArrayList<Comment> getComments(){
        return this.comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public ArrayList<Comment> deleteComment(Comment comment) {
        this.comments.remove(comment);
        return this.comments;
    }

    public ArrayList<Like> getLikes(){
        return this.likes;
    }

    public void addLike(Like like){
        this.likes.add(like);
    }

    public ArrayList<Like> removeLike(Like like){
        this.likes.remove(like);
        return this.likes;
    }

    @Override
    public int compareTo(Post otherPost){
        return this.date.compareTo(otherPost.date);
    }
}
