package com.example.demo.service;

import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.dto.PostDTO;

public interface PostService {

    int register(PostDTO postDTO);
    PostDTO readOne(int pid);
    void modify(PostDTO postDTO);
    void remove(int pid);
}
