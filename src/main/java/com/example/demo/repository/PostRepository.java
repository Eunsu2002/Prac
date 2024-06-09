package com.example.demo.repository;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<PostDTO> findAllByOrderByRegDateDesc();

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
