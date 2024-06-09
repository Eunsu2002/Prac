package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class CommentServiceTests {

    @Autowired
    private CommentService commentService;

    @Test
    public void testRegister() {
        CommentDTO commentDTO = CommentDTO.builder()
                .content("Comment Text")
                .nickName("nickName")
                .pid(209)
                .build();
        log.info("commentDTO : " + commentDTO);
        log.info(commentService.register(commentDTO));
    }

    @Test
    public void testRead() {
        CommentDTO commentDTO = commentService.read(111);
        log.info(commentDTO);
    }

    @Test
    public void testDelete() {
        int cid = 55;
        commentService.remove(cid);
    }
}
