package com.sysmap.parrot.controllers;

import com.sysmap.parrot.services.post.IPostService;
import com.sysmap.parrot.services.post.dto.CreatePostRequest;
import com.sysmap.parrot.services.post.dto.ReadPostResponse;
import com.sysmap.parrot.services.post.dto.embedded.AddCommentRequest;
import com.sysmap.parrot.services.post.dto.embedded.AddLikeRequest;
import com.sysmap.parrot.models.entities.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private IPostService _postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request){
        var response = _postService.createPost(request);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadPostResponse> readPost(@PathVariable("id") UUID id){
        var response = _postService.getPost(id);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") UUID id){
        var response = _postService.deletePost(id);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/comment")
    public ResponseEntity<String> addComment(@RequestBody AddCommentRequest request){
        var response = _postService.addComment(request);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/like")
    public ResponseEntity<String> addLike(@RequestBody AddLikeRequest request){
        var response = _postService.addLike(request);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllPosts(){
        List<Post> response = _postService.getAllPosts();
        return ResponseEntity.ok().body(response);
    }
}
