package com.morioucho.samarkand.repository;

import com.morioucho.samarkand.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
