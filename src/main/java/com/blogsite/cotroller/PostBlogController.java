package com.blogsite.cotroller;



import com.blogsite.entity.PostBlog;
import com.blogsite.exception.MessageResponse;
import com.blogsite.service.PostBlogService;
//import com.blogsite.service.SecretService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/user/blogs")
public class PostBlogController {
    private static final Logger logger = LoggerFactory.getLogger(PostBlogController.class);

    @Autowired
    private PostBlogService postService;

//    @Autowired
//    private SecretService secretService;
//
//    @GetMapping("/secret")
//    public String getSecret() {
//        String secretPath = "/blog/blog-site-key";
//        return secretService.getSecret(secretPath);
//    }
    @PostMapping("/add")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostBlog post, @RequestHeader("Authorization") String token) {
        logger.info("Access Token :->>",token);
        return postService.createPost(post, token) != null
                ? ResponseEntity.ok(new MessageResponse("Blog created successfully"))
                : ResponseEntity.badRequest().body(new MessageResponse("Failed to create blog"));
    }

    @GetMapping("/{id}")
    public PostBlog getPostById(@PathVariable Long id) {
        return postService.findById(id);
    }
    @GetMapping("/getall")
    public List<PostBlog> findAll() {
        return postService.findAll();
    }
    @GetMapping("/info/{category}")
    public List<PostBlog> getByCategory(@PathVariable("category") String category) {
        return postService.findByCategory(category);
    }
    @GetMapping("/get/{startDuration}/{endDuration}")
    public List<PostBlog> getBytimestampofcreation(@PathVariable("startDuration") LocalDateTime startDuration,
                                                   @PathVariable("endDuration") LocalDateTime endDuration) {
        return postService.findBytimestampofcreation(startDuration,endDuration);
    }

    @GetMapping("/get/{category}/{startDuration}/{endDuration}")
    public List<PostBlog> getByCategoryAndTimestampofcreationBefore(@PathVariable("category") String category,
                                                                    @PathVariable("startDuration") LocalDateTime startDuration,
                                                                    @PathVariable("endDuration") LocalDateTime endDuration) {
        return postService.findByCategoryAndTimestampofcreation(category,startDuration,endDuration);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateBlog(@PathVariable Long id, @RequestBody PostBlog updatedBlog) {
       Boolean updated = postService.updatePostBlog(id, updatedBlog);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id) {
        Boolean updated = postService.deletePostBlog(id);
        return ResponseEntity.ok(updated);
    }

}
