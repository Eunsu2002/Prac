package com.example.demo.service;

import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.dto.PostDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
@Log4j2
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    public void testRegister() {
        log.info(postService.getClass().getName());

        PostDTO postDTO = PostDTO.builder()
                .content("Sample1 Content......")
                .nickName("user1")
                .build();
        long pid = postService.register(postDTO);

        log.info("pid: " + pid);
    }

    @Test
    public void testReadOne() {
        int pid = 101;

        PostDTO postDTO = postService.readOne(pid);

        log.info(postDTO);
    }

    @Test
    public void testModify() {
        PostDTO postDTO = PostDTO.builder()
                .pid(102)
                .content("update Content.... 102... ")
                .build();
        postService.modify(postDTO);
        log.info(postService.readOne(postDTO.getPid()));
    }

    @Test
    public void testRemove() {
        int pid = 103;

        postService.remove(pid);
        Assertions.assertThrows(NoSuchElementException.class,
                () -> postService.readOne(pid));
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<PostDTO> responseDTO = postService.list(pageRequestDTO);
        log.info(responseDTO);
    }
}
