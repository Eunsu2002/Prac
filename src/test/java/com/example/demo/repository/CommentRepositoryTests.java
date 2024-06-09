package com.example.demo.repository;

import com.example.demo.entity.Comment;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @Test
    public void testPostComments() {
        int pid = 23;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("cid").descending());

        Page<Comment> result = commentRepository.listOfPost(pid, pageable);
        log.info("게시글의 댓글 수 : " + result.getTotalElements());
        result.getContent().forEach(comment -> {
            log.info(comment);
        });
    }

    @Test
    public void testDelete() {
        commentRepository.deleteById(99);
    }

    @Test
    public void testTotal() {
        log.info("count: " + commentRepository.count());
    }
}
