package com.sysmap.parrot.services.post.dto;

import com.sysmap.parrot.models.embedded.Comment;
import com.sysmap.parrot.models.embedded.Like;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Data
public class ReadPostResponse {
    private UUID id;
    private String pictureUrl;

    private String body;

    private UUID authorId;

    private Date date;

    private ArrayList<Comment> comments;

    private ArrayList<Like> likes;

    public ReadPostResponse(UUID id, String pictureUrl, String body, UUID authorId, Date date,
                            ArrayList<Comment> comments, ArrayList<Like> likes){
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.body = body;
        this.authorId = authorId;
        this.date = date;
        this.comments = comments;
        this.likes = likes;
    }
}
