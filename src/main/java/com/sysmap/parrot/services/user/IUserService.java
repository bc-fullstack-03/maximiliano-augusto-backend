package com.sysmap.parrot.services.user;

import com.sysmap.parrot.models.entities.Post;
import com.sysmap.parrot.services.user.dto.FollowUserRequest;
import com.sysmap.parrot.services.user.dto.ReadUserResponse;
import com.sysmap.parrot.services.user.dto.CreateUserRequest;
import com.sysmap.parrot.services.user.dto.UpdateUserRequest;
import com.sysmap.parrot.services.user.dto.embedded.ReadFeedResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);
    ReadUserResponse readUserById(String id);
    ReadUserResponse readUserByEmail(String id);
    ReadUserResponse readUserByName(String name);
    ReadUserResponse updateUser(UpdateUserRequest request);
    String deleteUser(String id);
    String followUser(FollowUserRequest request);
    String unfollowUser(FollowUserRequest request);
    void generateUserFeed(UUID id);
    ArrayList<Post> generateUserFeedList(ArrayList<UUID> following, List<Post> posts);
    ReadFeedResponse getUserFeed(UUID id);
}
