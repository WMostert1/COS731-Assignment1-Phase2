package za.co.wernerm.squekyclean.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import za.co.wernerm.squekyclean.model.Post;
import za.co.wernerm.squekyclean.model.User;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    String FIND_USER = "SELECT * FROM Post WHERE author = ?1";

    @Query(value = FIND_USER, nativeQuery = true)
    List<Post> findByAuthor(String author);
}
