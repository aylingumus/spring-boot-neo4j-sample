package com.gdgtekirdag.springbootwithneo4j.controller;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.Post;
import com.gdgtekirdag.springbootwithneo4j.dto.PostDTO;
import com.gdgtekirdag.springbootwithneo4j.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/post")
public class PostController {
    
    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getByPostId(@PathVariable(value = "postId") UUID postId) {
        return new ResponseEntity<>(postService.getById(postId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable(value = "userId") UUID userId){
        List<Post> postList = postService.listAll();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/create-post", consumes = "application/json")
    public ResponseEntity<Post> createPost(@PathVariable(value = "userId") UUID userId, @RequestBody PostDTO postDTO) {
        Post post = new Post(postDTO.getPostText());
        Post savedPost = postService.save(userId, post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}/update-post/{postId}",consumes = "application/json")
    public ResponseEntity<Post> updateUser(@PathVariable(value = "userId") UUID userId,
                                           @PathVariable(value = "postId") UUID postId,
                                           @RequestBody PostDTO postDTO) {
        Post post = postService.getById(postId);
        Post updatedPost = postService.update(userId, post, postDTO);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("{userId}/delete-post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "userId") UUID userId,
    @PathVariable(value = "postId") UUID postId) {
        postService.delete(userId, postId);
        return new ResponseEntity<>("Post is deleted.", HttpStatus.OK);
    }
}
