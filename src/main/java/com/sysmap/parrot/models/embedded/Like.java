package com.sysmap.parrot.models.embedded;

import lombok.Data;

@Data
public class Like {
    private Author author;
    private boolean liked;
    public Like(Author author, boolean liked){
        this.author = author;
        this.liked = liked;
    }
}
