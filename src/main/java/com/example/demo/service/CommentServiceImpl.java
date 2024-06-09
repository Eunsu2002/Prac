package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public int register(CommentDTO commentDTO) {

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        int cid = commentRepository.save(comment).getCid();

        log.info("생성된 댓글 : "+comment);
        return cid;
    }

    @Override
    public CommentDTO read(int cid) {

        Optional<Comment> commentOptional = commentRepository.findById(cid);
        Comment comment = commentOptional.orElseThrow();
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        log.info("read CommentDTO : " + commentDTO);
        return commentDTO;

    }

    @Override
    public void remove(int cid) {
        commentRepository.deleteById(cid);
    }

    @Override
    public PageResponseDTO<CommentDTO> getListOfPost(int pid, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("cid").ascending());
        Page<Comment> result = commentRepository.listOfPost(pid, pageable);

        List<CommentDTO> postList = result.getContent().stream().map( comment -> {
            CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
            commentDTO.setPid(comment.getPost().getPid());
            return commentDTO;
        }).collect(Collectors.toList());

        return PageResponseDTO.<CommentDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .postLists(postList)
                .total((int)result.getTotalElements())
                .build();
    }
}
