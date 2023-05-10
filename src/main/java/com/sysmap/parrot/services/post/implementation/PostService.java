package com.sysmap.parrot.services.post.implementation;

import com.sysmap.parrot.data.IPostRepository;
import com.sysmap.parrot.models.embedded.Author;
import com.sysmap.parrot.models.embedded.Comment;
import com.sysmap.parrot.models.entities.Post;
import com.sysmap.parrot.services.post.IPostService;
import com.sysmap.parrot.services.post.dto.CreatePostRequest;
import com.sysmap.parrot.services.post.dto.ReadPostResponse;
import com.sysmap.parrot.services.post.dto.embedded.AddCommentRequest;
import com.sysmap.parrot.services.post.dto.embedded.AddLikeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sysmap.parrot.services.user.implementation.IUserService;

import java.util.Date;
import java.util.UUID;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository _postsRepository;
    @Autowired
    private IUserService _userService;

    public String createPost(CreatePostRequest request) {
        var post = new Post(request.authorId, request.pictureUrl, request.body, new Date());

        _postsRepository.save(post);

        return post.getId().toString();
    }

    public ReadPostResponse getPost(UUID id){
        Post post = _postsRepository.findById(id).get();

        var response = new ReadPostResponse(
                            post.getId(),
                            post.getPictureUrl(),
                            post.getBody(),
                            post.getAuthorId(),
                            post.getDate(),
                            post.getComments(),
                            post.getLikes());

        return response;
    }

    public String deletePost(UUID id){
        _postsRepository.deleteById(id);

        return "Successfully deleted post";
    }

    public List<Post> getAllPosts(){
        return _postsRepository.findAll();
    }

    public String addComment(AddCommentRequest request){
        var user = _userService.readUserById(request.authorId);

        Author author = new Author(user.getId(), user.getName(), user.getPhotoUrl());

        Comment comment = new Comment(author, request.text);

        Post post = _postsRepository.findById(request.postId).get();

        post.addComment(comment);

        _postsRepository.save(post);

        return "Comment Added Successfully";
    }

    public String addLike(AddLikeRequest request){
        var user = _userService.readUserById(request.authorId);

        Author author = new Author(user.getId(), user.getName(), user.getPhotoUrl());

        Post post = _postsRepository.findById(request.postId).get();

        if(post.getLikes().contains(author)){
            post.getLikes().remove(author);
            _postsRepository.save(post);

            return "Post Disliked";
        }            
        post.addLike(author);
        _postsRepository.save(post);

        return "Post Liked Successfully";
    }
}
