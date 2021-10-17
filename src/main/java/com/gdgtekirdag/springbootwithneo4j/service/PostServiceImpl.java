package com.gdgtekirdag.springbootwithneo4j.service;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.Post;
import com.gdgtekirdag.springbootwithneo4j.dto.PostDTO;
import com.gdgtekirdag.springbootwithneo4j.repository.PostRepository;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> listAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(UUID postId) {
        return postRepository.getById(postId);
    }

    @Override
    public Post save(UUID userId, Post post) {
        Post savedPost = postRepository.savePost(userId, post.getPostText());
        return savedPost;
    }

    @Override
    public Post update(UUID userId, Post post, PostDTO postDTO) {
        Post updatedPost = postRepository.updatePost(userId, post.getPostId(), postDTO.getPostText());
        return updatedPost;
    }

    @Override
    public void delete(UUID userId, UUID postId) {
        postRepository.deletePost(userId, postId);
    }
}
