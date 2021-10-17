package com.gdgtekirdag.springbootwithneo4j.repository;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.Post;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface PostRepository extends Neo4jRepository<Post, UUID>{

    @Query("MATCH (posts:Post) RETURN posts")
    List<Post> listAll();

    @Query("MATCH (post:Post {postId: $postId}) RETURN post")
    Post getById(UUID postId);

    @Query("CREATE (user:User {userId: $userId})-[:POSTS]->(post:Post {postId: randomUUID(), postText: $postText}) " +
    "RETURN post")
    Post savePost(UUID userId, String postText);

    @Query("MATCH (user:User {userId: $userId})-[:POSTS]->(post:Post {postId: $postId}) " +
    "SET post.postText = $postText RETURN post")
    Post updatePost(UUID userId, UUID postId, String postText);

    @Query("MATCH (user:User {userId: $userId})-[:POSTS]->(post:Post {postId: $postId}) DELETE post")
    void deletePost(UUID userId, UUID postId);
}