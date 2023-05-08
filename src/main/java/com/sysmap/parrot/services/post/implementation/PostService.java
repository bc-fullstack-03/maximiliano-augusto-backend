package com.sysmap.parrot.services.post.implementation;

import com.sysmap.parrot.data.IPostRepository;
import com.sysmap.parrot.data.IUserRepository;
import com.sysmap.parrot.models.embedded.Author;
import com.sysmap.parrot.models.embedded.Comment;
import com.sysmap.parrot.models.embedded.Like;
import com.sysmap.parrot.models.entities.User;
import com.sysmap.parrot.models.entities.Post;
import com.sysmap.parrot.services.post.IPostService;
import com.sysmap.parrot.services.post.dto.CreatePostRequest;
import com.sysmap.parrot.services.post.dto.ReadPostResponse;
import com.sysmap.parrot.services.post.dto.embedded.AddCommentRequest;
import com.sysmap.parrot.services.post.dto.embedded.AddLikeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository _postsRepository;
    @Autowired
    private IUserRepository _userRepository;

    public String createPost(CreatePostRequest request) {
        UUID id = UUID.fromString(request.authorId);
        User user = _userRepository.findById(id).get();

        Author author = new Author(user.getId(), user.getName(), user.getPhotoUrl());

        var post = new Post(author, request.pictureUrl, request.body, new Date());

        _postsRepository.save(post);

        return post.getId().toString();
    }

    public ReadPostResponse getPost(String id){
        UUID postId = UUID.fromString(id);

        Post post = _postsRepository.findById(postId).get();

        var response = new ReadPostResponse(
                            post.getId(),
                            post.getPictureUrl(),
                            post.getBody(),
                            post.getAuthor(),
                            post.getDate(),
                            post.getComments(),
                            post.getLikes());

        return response;
    }

    public String addComment(AddCommentRequest request){
        User user = _userRepository.findById(request.authorId).get();

        Author author = new Author(user.getId(), user.getName(), user.getPhotoUrl());

        Comment comment = new Comment(author, request.text);

        Post post = _postsRepository.findById(request.postId).get();

        post.addComment(comment);

        _postsRepository.save(post);

        return ResponseEntity.ok("Comment Added Successfully").toString();
    }

    public String addLike(AddLikeRequest request){
        User user = _userRepository.findById(request.authorId).get();

        Author author = new Author(user.getId(), user.getName(), user.getPhotoUrl());

        Like like = new Like(author, request.liked);

        Post post = _postsRepository.findById(request.postId).get();

        post.addLike(like);

        _postsRepository.save(post);

        return ResponseEntity.ok("Post Liked Successfully").toString();
    }
}
