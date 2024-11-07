package com.blogsite.service;


import com.blogsite.entity.PostBlog;
import com.blogsite.entity.UserDto;
import com.blogsite.exception.UserNotFoundException;
import com.blogsite.repository.PostBlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostBlogServiceImpl implements PostBlogService {


    private static final Logger logger = LoggerFactory.getLogger(PostBlogServiceImpl.class);

    @Autowired
    private PostBlogRepository postRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://34.70.118.87:80/api/v1.0/blogsite/auth/user/";

    @Override
    public PostBlog createPost(PostBlog post, String token) {
        logger.info("URl-> {} token-> {}",USER_SERVICE_URL + post.getUserId(),token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");
        HttpEntity<PostBlog> requestEntity = new HttpEntity<>(post, headers);
        try{
           UserDto user = restTemplate.exchange(USER_SERVICE_URL + post.getUserId(), HttpMethod.GET,
                   requestEntity, UserDto.class).getBody();
           // UserDto user = restTemplate.getForEntity(USER_SERVICE_URL + post.getUserId(),UserDto.class).getBody();
           logger.info(">>>>>{}",String.valueOf(user));
           if (user != null) {
               return postRepository.save(post);
           }
       }catch (HttpClientErrorException.Forbidden e) {

            logger.error("Access Denied: Status Code: {}, Response Body: {}", e.getStatusCode(), e.getResponseBodyAsString());
        } catch (HttpClientErrorException e) {
            logger.error("HTTP Status Code: {}, Error Body: {}", e.getStatusCode(), e.getResponseBodyAsString());
        } catch (RestClientException e) {

            logger.error("RestClientException: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("User with ID " + post.getUserId() + " not found.");
            throw new UserNotFoundException("User with ID " + post.getUserId() + " not found.");
        }

       return null;
    }

    @Override
    public PostBlog findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<PostBlog> findByCategory(String category) {
        return postRepository.findByCategory(category);
    }


    @Override
    public List<PostBlog> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostBlog> findBytimestampofcreation(LocalDateTime from, LocalDateTime to) {
        return postRepository.findByTimestampofcreationBetween(from, to);
    }

    @Override
    public List<PostBlog> findByCategoryAndTimestampofcreation(String category, LocalDateTime from,LocalDateTime to) {
        return postRepository.findByCategoryAndTimestampofcreationBetween(category, from,to);
    }



    @Override
    public Boolean updatePostBlog(Long id, PostBlog updatedBlog) {
        if (postRepository.existsById(id)) {
            updatedBlog.setId(id);
             postRepository.save(updatedBlog);
            return true;
        } else {
            throw new UserNotFoundException("Blog not found with id " + id);

        }
    }

    @Override
    public Boolean deletePostBlog(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Blog not found with id " + id);
        }
        //return postRepository.existsById(id);
        return true;
    }


}
