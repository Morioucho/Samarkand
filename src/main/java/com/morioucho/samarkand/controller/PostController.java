package com.morioucho.samarkand.controller;

import com.morioucho.samarkand.model.Post;
import com.morioucho.samarkand.model.Session;
import com.morioucho.samarkand.model.User;
import com.morioucho.samarkand.repository.UserRepository;
import com.morioucho.samarkand.service.PostService;

import com.morioucho.samarkand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostByID(@PathVariable Long id){
        Optional<Post> post = postService.getPostByID(id);

        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Post createPost(@RequestParam String token, @RequestBody Post post){
        Session supposedSession = new Session();
        supposedSession.setToken(token);

        User user = userRepository.findBySession(supposedSession);

        if(user == null){
            return null;
        }

        return postService.createPost(user.getId(), post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails){
        Post updatedPost = postService.updatePost(id, postDetails);

        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
