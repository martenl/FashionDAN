package de.martenl.fashiondan.admin.repository;

import de.martenl.fashiondan.admin.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

}
