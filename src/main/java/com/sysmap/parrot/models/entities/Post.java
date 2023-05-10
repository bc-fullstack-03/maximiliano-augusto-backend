package com.sysmap.parrot.models.entities;

import com.sysmap.parrot.models.embedded.Comment;
import com.sysmap.parrot.models.embedded.Author;
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
    private ArrayList<Author> likes;

    public Post(UUID authorId, String pictureUrl, String body, Date date){
        setId();
        this.authorId = authorId;
        this.pictureUrl = pictureUrl;
        this.body = body;
        this.date = date;
        comments = new ArrayList<Comment>();
        likes = new ArrayList<>();
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

    public ArrayList<Author> getLikes(){
        return this.likes;
    }

    public void addLike(Author author){
        this.likes.add(author);
    }

    public ArrayList<Author> removeLike(Author author){
        this.likes.remove(author);
        return this.likes;
    }

    @Override
    public int compareTo(Post otherPost){
        return this.date.compareTo(otherPost.date);
    }
}
