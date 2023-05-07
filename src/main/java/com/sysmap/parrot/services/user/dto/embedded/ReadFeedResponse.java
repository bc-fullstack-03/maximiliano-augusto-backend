package com.sysmap.parrot.services.user.dto.embedded;

import com.sysmap.parrot.models.embedded.Feed;
import lombok.Data;

@Data
public class ReadFeedResponse {
    private Feed feed;

    public ReadFeedResponse(Feed feed){
        this.feed = feed;
    }
}
