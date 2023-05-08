package com.sysmap.parrot.services.user.implementation;

import com.sysmap.parrot.models.entities.User;
import com.sysmap.parrot.services.user.dto.FollowUserRequest;
import com.sysmap.parrot.services.user.dto.ReadUserResponse;
import com.sysmap.parrot.services.user.dto.CreateUserRequest;
import com.sysmap.parrot.services.user.dto.UpdateUserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);
    ReadUserResponse readUserById(String id);
    ReadUserResponse readUserByEmail(String id);
    ReadUserResponse readUserByName(String name);
    ReadUserResponse updateUser(UpdateUserRequest request);
    String deleteUser(String id);
    User getUser(String email);
    public void uploadPhotoProfile(MultipartFile photo) throws Exception;
    public String followUser(FollowUserRequest request);
    public String unfollowUser(FollowUserRequest request);
    public ArrayList<UUID> getFollowingList(UUID userId);
}
