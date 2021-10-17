package com.gdgtekirdag.springbootwithneo4j.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import lombok.Data;

@Data
@Node("User")
public class User {

    @Id
	@Property
	private UUID userId;

    @Property
	private String username;

    @Relationship(type = "POSTS", direction = Direction.OUTGOING)
    private Set<Post> posts = new HashSet<>();

    public User(String username) {
        this.username = username;
    }

}