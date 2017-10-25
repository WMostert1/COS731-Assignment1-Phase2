package za.co.wernerm.squekyclean.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import za.co.wernerm.squekyclean.dto.CreatePostDTO;
import za.co.wernerm.squekyclean.dto.PostDTO;
import za.co.wernerm.squekyclean.model.Post;
import za.co.wernerm.squekyclean.repository.PostRepository;
import za.co.wernerm.squekyclean.model.Thread;
import za.co.wernerm.squekyclean.repository.ThreadRepository;
import za.co.wernerm.squekyclean.subscriptions.SNSSubscription;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by werner on 2017/09/13.
 */
@Controller
@RequestMapping(path="api/post")
public class PostController {
    @Autowired
    public PostRepository postRepository;

    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    SNSSubscription snsSubscription;

    @GetMapping
    @ResponseBody
    public List<PostDTO> getPosts(String author){
        List<Post> posts = postRepository.findByAuthor(author);
        List<PostDTO> returnPosts = new ArrayList<>();
        for(Post p : posts){
            PostDTO dto = new PostDTO();
            dto.setAuthor(p.getAuthor());
            dto.setContent(p.getContent());
            dto.setDatePosted(p.getDatePosted());
            dto.setId(p.getId());
            returnPosts.add(dto);
        }
        return returnPosts;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity createPost(@RequestBody CreatePostDTO createPostDTO){
        Post post = new Post();
        post.setAuthor(createPostDTO.getAuthor());
        post.setContent(createPostDTO.getContent());
        post.setDatePosted(Date.valueOf(LocalDate.now()));

        Thread thread = threadRepository.findOne(createPostDTO.getThreadId());
        post.setThread(thread);

        snsSubscription.notifySubscribers(thread.getTopicArn(), createPostDTO, thread.getThreadName());

        postRepository.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}