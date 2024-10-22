package com.blogsite.service;



import com.blogsite.entity.PostBlog;

import java.time.LocalDateTime;
import java.util.List;


public interface PostBlogService {
    public PostBlog createPost(PostBlog post,String token);
    public PostBlog findById(Long id);
    List<PostBlog> findByCategory(String category);
    List<PostBlog> findAll();
    List<PostBlog> findBytimestampofcreation(LocalDateTime from, LocalDateTime to);
    List<PostBlog> findByCategoryAndTimestampofcreation(String category, LocalDateTime from,LocalDateTime to);
    Boolean deletePostBlog(Long id);
    Boolean updatePostBlog(Long id, PostBlog updatedBlog);
}
