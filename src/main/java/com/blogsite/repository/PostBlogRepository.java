package com.blogsite.repository;


import com.blogsite.entity.PostBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostBlogRepository extends JpaRepository<PostBlog, Long> {
    List<PostBlog>  findByCategory(String category);
    List<PostBlog> findAll();
    List<PostBlog> findByTimestampofcreationBetween(LocalDateTime from, LocalDateTime to);
    List<PostBlog> findByCategoryAndTimestampofcreationBetween(String category, LocalDateTime from,LocalDateTime to);
}

