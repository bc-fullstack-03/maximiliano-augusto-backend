package com.sysmap.parrot.services.user.embedded;

import com.sysmap.parrot.data.IFollowsRepository;
import com.sysmap.parrot.models.embedded.Follows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class FollowsService implements IFollowService{

    @Autowired
    private IFollowsRepository _followsRepository;

    public String followUser(UUID id, UUID targetId){
        var follows = _followsRepository.findById(id).get();

        if(follows.getFollowing().contains(targetId)){
            return "Following This User";
        }

        follows.getFollowing().add(targetId);
        _followsRepository.save(follows);

        return "User Followed Successfully";
    }

    public String unfollowUser(UUID id, UUID targetId){
        var follows = _followsRepository.findById(id).get();

        if(!follows.getFollowing().contains(targetId)){
            return "You Dont Follow This User";
        }

        follows.getFollowing().remove(targetId);
        _followsRepository.save(follows);

        return "User Success Unfollowed";
    }

    public void createFollow(UUID id){
        Follows follows = new Follows(id);

        _followsRepository.save(follows);
    }

    public ArrayList<UUID> getFollowList(UUID userId){
        var follows = _followsRepository.findById(userId).get();

        var response = follows.getFollowing();

        return response;
    }
}
