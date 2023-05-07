package com.sysmap.parrot.models.embedded;

import lombok.Data;

@Data
public class Comment {
    private Author author;
    private String text;
    public Comment(Author author, String text){
        this.author = author;
        this.text = text;
    }

    public Comment(){}
}
