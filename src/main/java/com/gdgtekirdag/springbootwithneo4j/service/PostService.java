package com.gdgtekirdag.springbootwithneo4j.service;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.Post;
import com.gdgtekirdag.springbootwithneo4j.dto.PostDTO;

public interface PostService {

    List<Post> listAll();

    Post getById(UUID postId);

    Post save(UUID userId, Post post);

    Post update(UUID userId, Post post, PostDTO postDTO);

    void delete(UUID userId, UUID postId);
}
