package com.sysmap.parrot.services.user.implementation;

import com.sysmap.parrot.data.IPostRepository;
import com.sysmap.parrot.data.IUserRepository;
import com.sysmap.parrot.models.embedded.Feed;
import com.sysmap.parrot.models.entities.Post;
import com.sysmap.parrot.models.entities.User;
import com.sysmap.parrot.services.security.IJwtService;
import com.sysmap.parrot.services.user.IUserService;
import com.sysmap.parrot.services.user.dto.CreateUserRequest;
import com.sysmap.parrot.services.user.dto.FollowUserRequest;
import com.sysmap.parrot.services.user.dto.ReadUserResponse;
import com.sysmap.parrot.services.user.dto.UpdateUserRequest;
import com.sysmap.parrot.services.user.dto.embedded.ReadFeedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private IJwtService _jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private IPostRepository _postsRepository; //Mudar e utilizar apenas as funções de IPostService

    public String createUser(CreateUserRequest request){
        var user = new User(request.name, request.email);

        var hash = _passwordEncoder.encode(request.password);

        user.setPassword(hash);

        _userRepository.save(user);

        return user.getId().toString();
    }

    public ReadUserResponse readUserById(String id){
        UUID uuid = UUID.fromString(id);

        var user = _userRepository.findById(uuid).get();

        var response = new ReadUserResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getFollowers(),
                            user.getFollowing());

        return response;
    }

    public ReadUserResponse readUserByEmail(String email){
        var user = _userRepository.findByEmail(email).get();

        var response = new ReadUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getFollowers(),
                user.getFollowing());

        return response;
    }

    public ReadUserResponse readUserByName(String name){
        var user = _userRepository.findByName(name).get();

        var response = new ReadUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getFollowers(),
                user.getFollowing());

        return response;
    }

    public ReadUserResponse updateUser(UpdateUserRequest request){
        UUID id = UUID.fromString(request.id);

        User user = _userRepository.findById(id).get();

        user.setName(request.name);

        user.setEmail(request.email);

        _userRepository.save(user);

        var response = new ReadUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getFollowers(),
                user.getFollowing());

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

    public String followUser(FollowUserRequest request){
        User myUser = _userRepository.findById(request.myUserId).get();
        User targetUser = _userRepository.findById(request.targetUserId).get();

        myUser.addFollowed(request.targetUserId);
        targetUser.addFollower(request.myUserId);

        _userRepository.save(myUser);
        _userRepository.save(targetUser);

        return ResponseEntity.ok("User Followed Successfully").toString();
    }

    public String unfollowUser(FollowUserRequest request){
        User myUser = _userRepository.findById(request.myUserId).get();
        User targetUser = _userRepository.findById(request.targetUserId).get();

        myUser.removeFollowed(request.targetUserId);
        targetUser.removeFollower(request.myUserId);

        _userRepository.save(myUser);
        _userRepository.save(targetUser);

        return ResponseEntity.ok("User Unfollowed").toString();
    }

    public ReadFeedResponse getUserFeed(UUID id){
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
    }

}
