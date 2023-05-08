package com.sysmap.parrot.services.user.embedded;

import java.util.ArrayList;
import java.util.UUID;

public interface IFollowService {
    public void createFollow(UUID id);
    public void followUser(UUID id, UUID targetId);
    public void unfollowUser(UUID id, UUID targetId);
    public ArrayList<UUID> getFollowList(UUID userId);
}
