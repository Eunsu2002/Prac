package com.example.demo.repository;

import com.example.demo.entity.Post;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Post post = Post.builder()
                    .content("content..............."+i)
                    .nickName("user"+(i%10))
                    .build();
            Post result = postRepository.save(post);
            log.info("PID : " + result.getPid());
        });
    }

    @Test
    public void testSelect() {
        int pid = 100;

        Optional<Post> result =  postRepository.findById(pid);
        Post post = result.orElseThrow();

        log.info(post);

    }

    @Test
    public void testUpdate() {
        int pid = 100;

        Optional<Post> result = postRepository.findById(pid);

        Post post = result.orElseThrow();

        post.changePost("update content....... 100");

        postRepository.save(post);
    }

    @Test
    public void testDelete() {
        int pid = 1;

        postRepository.deleteById(pid);
    }

    public void testPaging() {
        // 1 page order by bno desc
        Pageable pageable =
                PageRequest.of(0,10, Sort.by("pid").descending());

        Page<Post> result = postRepository.findAll(pageable);

        log.info("total count : " + result.getTotalElements());
        log.info("total page : "+ result.getTotalPages());
        log.info("page number : " + result.getNumber());
        log.info("page size : " + result.getSize());
        log.info("다음 페이지 여부? : " + result.hasNext());
        log.info("이전 페이지 여부? : " + result.hasPrevious());

        List<Post> boardList =  result.getContent();
        boardList.forEach(post -> log.info(post));

    }

    @Test
    public void testGetTime() {
        log.info(postRepository.getTime());
    }

}
