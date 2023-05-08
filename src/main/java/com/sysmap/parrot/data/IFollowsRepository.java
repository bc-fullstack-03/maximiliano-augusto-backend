package com.sysmap.parrot.data;

import com.sysmap.parrot.models.embedded.Follows;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IFollowsRepository extends MongoRepository<Follows, UUID> {
}
