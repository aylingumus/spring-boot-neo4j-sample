package com.gdgtekirdag.springbootwithneo4j.domain;

import java.util.UUID;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Data;

@Data
@Node("Post")
public class Post {

	@Id
	@Property
	private UUID postId;

    @Property
	private String postText;

	public Post(String postText) {
		this.postText = postText;
	}
}