package com.sysmap.parrot.models.embedded;

import com.sysmap.parrot.models.entities.Post;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Feed {
    private ArrayList<Post> posts;

    public Feed(ArrayList<Post> posts){
        this.posts = posts;
    }
}
