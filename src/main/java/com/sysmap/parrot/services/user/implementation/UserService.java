package com.sysmap.parrot.services.user.implementation;

import com.sysmap.parrot.data.IPostRepository;
import com.sysmap.parrot.data.IUserRepository;
import com.sysmap.parrot.models.embedded.Feed;
import com.sysmap.parrot.models.entities.Post;
import com.sysmap.parrot.models.entities.User;
import com.sysmap.parrot.services.fileUpload.IFileUploadService;
import com.sysmap.parrot.services.user.dto.CreateUserRequest;
import com.sysmap.parrot.services.user.dto.FollowUserRequest;
import com.sysmap.parrot.services.user.dto.ReadUserResponse;
import com.sysmap.parrot.services.user.dto.UpdateUserRequest;
import com.sysmap.parrot.services.user.dto.embedded.ReadFeedResponse;
import com.sysmap.parrot.services.user.embedded.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;
    @Autowired
    private PasswordEncoder _passwordEncoder;
    @Autowired
    private IFileUploadService _fileUploadService;
    @Autowired
    private IFollowService _followService;

    public String createUser(CreateUserRequest request){
        var user = new User(request.name, request.email);

        var hash = _passwordEncoder.encode(request.password);

        _followService.createFollow(user.getId());

        user.setPassword(hash);

        _userRepository.save(user);

        return user.getId().toString();
    }

    public ReadUserResponse readUserById(String id){
        UUID uuid = UUID.fromString(id);

        var user = _userRepository.findById(uuid).get();

        var response = new ReadUserResponse(user.getId(), user.getName(), user.getEmail());

        return response;
    }

    public ReadUserResponse readUserByEmail(String email){
        var user = _userRepository.findByEmail(email).get();

        var response = new ReadUserResponse(user.getId(), user.getName(), user.getEmail());

        return response;
    }

    public ReadUserResponse readUserByName(String name){
        var user = _userRepository.findByName(name).get();

        var response = new ReadUserResponse(user.getId(), user.getName(), user.getEmail());

        return response;
    }

    public ReadUserResponse updateUser(UpdateUserRequest request){
        UUID id = UUID.fromString(request.id);

        User user = _userRepository.findById(id).get();

        user.setName(request.name);

        user.setEmail(request.email);

        _userRepository.save(user);

        var response = new ReadUserResponse(user.getId(), user.getName(), user.getEmail());

        return response;
    }

    public String deleteUser(String id){
        UUID userId = UUID.fromString(id);

        _userRepository.deleteById(userId);

        return ResponseEntity.ok("User Deleted Successfully").toString();
    }

    public User getUser(String email){
        return _userRepository.findByEmail(email).get();
    }

    public void uploadPhotoProfile(MultipartFile photo) throws Exception{
        var user = ((User) SecurityContextHolder.getContext().getAuthentication());

        var photoUrl = " ";

        try {
            var fileName = user.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);

            photoUrl = _fileUploadService.upload(photo, fileName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        user.setPhotoUrl(photoUrl);
        _userRepository.save(user);
    }

    public String followUser(FollowUserRequest request){
        _followService.followUser(request.myUserId, request.targetUserId);

        return "User Followed Successfully";
    }

    public String unfollowUser(FollowUserRequest request){
        _followService.unfollowUser(request.myUserId, request.targetUserId);

        return "User Unfollowed Successfully";
    }

    public ArrayList<UUID> getFollowingList(UUID userId){

        var response = _followService.getFollowList(userId);

        return response;
    }

    /*public ReadFeedResponse getUserFeed(UUID id){
        User user = _userRepository.findById(id).get();

        generateUserFeed(user.getId());

        ReadFeedResponse response = new ReadFeedResponse(user.getFeed());

        return response;
    }

    public void generateUserFeed(UUID id){
        User user = _userRepository.findById(id).get();

        var postsList = _postsRepository.findAll();

        ArrayList<Post> posts = generateUserFeedList(user.getFollowing(), postsList);

        Feed feed = new Feed(posts);

        user.setFeed(feed);

        _userRepository.save(user);
    }

    public ArrayList<Post> generateUserFeedList(ArrayList<UUID> following, List<Post> posts){
        ArrayList<Post> response = new ArrayList<>();

        for(UUID id : following){
            for(Post post : posts){
                if(post.getAuthor().getId().equals(id)){
                    response.add(post);
                }
            }
        }

        Collections.sort(response);

        return response;
    }*/

}
