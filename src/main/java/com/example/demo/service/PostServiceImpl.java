package com.example.demo.service;

import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional                      // 여러 DB 에 작업시, 완전 성공시에 처리함
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public int register(PostDTO postDTO) {

        Post post = modelMapper.map(postDTO, Post.class);

        int pid = postRepository.save(post).getPid();

        return pid;

    }

    @Override
    public PostDTO readOne(int pid) {

        Optional<Post> result = postRepository.findById(pid);

        Post post = result.orElseThrow();

        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        return postDTO;

    }

    @Override
    public void modify(PostDTO postDTO) {

        Optional<Post> result = postRepository.findById(postDTO.getPid());

        Post post = result.orElseThrow();

        post.changePost(postDTO.getContent());

        postRepository.save(post);

    }

    @Override
    public void remove(int pid) {

        postRepository.deleteById(pid);

    }

    @Override
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("pid");
        Page<Post> result = postRepository.findAll(pageable);

        List<PostDTO> postList = result.getContent().stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<PostDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .postLists(postList)
                .total((int)result.getTotalElements())
                .build();
    }
}
