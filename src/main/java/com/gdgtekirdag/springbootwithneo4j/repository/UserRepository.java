package com.gdgtekirdag.springbootwithneo4j.repository;

import java.util.List;
import java.util.UUID;

import com.gdgtekirdag.springbootwithneo4j.domain.User;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface UserRepository extends Neo4jRepository<User, UUID>{

    @Query("MATCH (users:User) RETURN users")
    List<User> listAll();

    @Query("MATCH (user:User {userId: $userId}) RETURN user")
    User getById(UUID userId);

    @Query("MATCH (user:User {username: $username}) RETURN user")
    User getByUsername(String username);

    @Query("CREATE (user:User {userId: randomUUID(), username: $username}) RETURN user")
    User saveUser(String username);

    @Query("MATCH (user:User {username: $username}) SET user.userId = $userId RETURN user")
    User updateUser(UUID userId, String username);

    @Query("MATCH (user:User {userId: $userId}) DELETE user")
    void deleteUser(UUID userId);
}