//package com.example.demo.service;
//
//import com.example.demo.dto.CommentDTO;
//import com.example.demo.dto.PageRequestDTO;
//import com.example.demo.dto.PageResponseDTO;
//import com.example.demo.entity.Comment;
//import com.example.demo.repository.CommentRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.awt.print.Pageable;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class CommentServiceImpl implements CommentService {
//
//    private final CommentRepository commentRepository;
//
//    private final ModelMapper modelMapper;
//
//    @Override
//    public int register(CommentDTO commentDTO) {
//
//        Comment comment = modelMapper.map(commentDTO, Comment.class);
//
//        comment.setPost(commentDTO.getPid());
//
//        int cid = commentRepository.save(comment).getCid();
//
//        return cid;
//    }
//
//    @Override
//    public CommentDTO read(int pid) {
//
//        Optional<Comment> commentOptional = commentRepository.findById(cid);
//
//        Comment comment = commentOptional.orElseThrow();
//
//        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
//        log.info("read CommentDTO : " + commentDTO);
//
//        commentDTO.setPid(comment.getPost().getPid());
//
//        return commentDTO;
//    }
//
//    @Override
//    public void modify(CommentDTO commentDTO) {
//
//        Optional<Comment> commentOptional = commentRepository.findById(commentDTO.getCid());
//
//        Comment comment = commentOptional.orElseThrow();
//
//        comment.changeContent(comment.getContent());
//
//        commentRepository.save(comment);
//    }
//
//    @Override
//    public void remove(int pid) {
//        commentRepository.deleteById(pid);
//    }
//
//    @Override
//    public PageResponseDTO<CommentDTO> getListOfPost(int pid, PageRequestDTO pageRequestDTO) {
//        Pageable pageable = PageRequest.of(
//                pageRequestDTO.getPage() <= 0 ? 0: pageRequestDTO.getPage() - 1,
//                pageRequestDTO.getSize(),
//                Sort.by("cid").ascending());  // 처음 댓글을 위로 올리기 위해서...
//        Page<Comment> result = commentRepository.listOfPost(pid, pageable);
//
//        List<Comment> dtoList = result.getContent().stream().map(comment -> {
//            CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
//            CommentDTO.setPid(comment.getPost().getPid());
//            return commentDTO;
//        }).collect(Collectors.toList());
//
//        return PageResponseDTO.<CommentDTO>withAll()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(dtoList)
//                .total((int)result.getTotalElements())
//                .build();
//    }
//}
