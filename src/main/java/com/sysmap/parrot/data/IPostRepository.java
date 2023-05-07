package com.sysmap.parrot.data;

import com.sysmap.parrot.models.embedded.Author;
import com.sysmap.parrot.models.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
    Optional<Post> findByAuthor(Author author);
}
