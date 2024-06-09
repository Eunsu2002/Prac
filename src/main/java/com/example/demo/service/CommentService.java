package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;

public interface CommentService {

    int register(CommentDTO commentDTO);

    CommentDTO read(int pid);

    void remove(int pid);

    PageResponseDTO<CommentDTO> getListOfPost(int pid, PageRequestDTO pageRequestDTO);
}
