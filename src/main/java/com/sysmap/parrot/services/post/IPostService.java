package com.sysmap.parrot.services.post;
import java.util.List;
import com.sysmap.parrot.models.entities.Post;

import com.sysmap.parrot.services.post.dto.CreatePostRequest;
import com.sysmap.parrot.services.post.dto.ReadPostResponse;
import com.sysmap.parrot.services.post.dto.embedded.AddCommentRequest;
import com.sysmap.parrot.services.post.dto.embedded.AddLikeRequest;

import java.util.UUID;

public interface IPostService {
    String createPost(CreatePostRequest request);
    ReadPostResponse getPost(UUID id);
    public String deletePost(UUID id);
    public List<Post> getAllPosts();
    String addComment(AddCommentRequest request);
    String deleteComment(AddCommentRequest request);
    String addLike(AddLikeRequest request);
}
