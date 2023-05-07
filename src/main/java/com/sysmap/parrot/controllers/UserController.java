package com.sysmap.parrot.controllers;

import com.sysmap.parrot.services.user.dto.CreateUserRequest;
import com.sysmap.parrot.services.user.IUserService;
import com.sysmap.parrot.services.user.dto.FollowUserRequest;
import com.sysmap.parrot.services.user.dto.ReadUserResponse;
import com.sysmap.parrot.services.user.dto.UpdateUserRequest;
import com.sysmap.parrot.services.user.dto.embedded.ReadFeedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService _userService;

    @GetMapping("/{id}")
    public ResponseEntity<ReadUserResponse> readUser(@PathVariable("id") String id){
        return ResponseEntity.ok().body(_userService.readUserById(id));
    }

    @GetMapping("/login")
    public ResponseEntity<ReadUserResponse> readUserByEmail(String email){
        return ResponseEntity.ok().body(_userService.readUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity<ReadUserResponse> readUserByName(String name){
        return ResponseEntity.ok().body(_userService.readUserByName(name));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request){
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ReadUserResponse> updateUser(@RequestBody UpdateUserRequest request){
        var response = _userService.updateUser(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
        var response = _userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestBody FollowUserRequest request){
        var response = _userService.followUser(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestBody FollowUserRequest request){
        var response = _userService.unfollowUser(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/feed/{id}")
    public ResponseEntity<ReadFeedResponse> getUserFeed(@PathVariable("id") UUID id){
        var response = _userService.getUserFeed(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
