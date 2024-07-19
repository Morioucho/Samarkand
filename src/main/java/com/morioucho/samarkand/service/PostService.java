package com.morioucho.samarkand.service;

import com.morioucho.samarkand.model.Post;
import com.morioucho.samarkand.model.User;

import com.morioucho.samarkand.repository.PostRepository;
import com.morioucho.samarkand.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> getPostByID(long id){
        return postRepository.findById(id);
    }

    public Post createPost(Long userID, Post post){
        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("Unable to find user with the ID of \"" + userID + "\"."));
        post.setUser(user);

        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Unable to find post with the id \"" + id + "\"."));

        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());

        return postRepository.save(post);
    }

    public void deletePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Unable to find a post with ID \"" + id + "\"."));

        postRepository.delete(post);
    }
}
