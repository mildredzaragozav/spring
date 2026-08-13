package com.belle.springweb.service;

import com.belle.springweb.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final RestClient postRestClient;

    public List<Post> getPosts() {
        return postRestClient
                .get()
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public ResponseEntity<List<Post>> getPostsAsResponseEntity() {
        return postRestClient
                .get()
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>(){});
    }

    public Post addPost(Post post) {
        return postRestClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public Post getPostById(String id) {
        return postRestClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .body(Post.class);
    }


}
