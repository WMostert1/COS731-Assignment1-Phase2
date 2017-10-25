package za.co.wernerm.squekyclean.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import za.co.wernerm.squekyclean.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    String FIND_USER = "SELECT * FROM Users WHERE email = ?1";

    @Query(value = FIND_USER, nativeQuery = true)
    List<User> findUsers(String email);
}
