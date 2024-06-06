package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {
        @Index(name = "idx_comment_post_pid", columnList = "post_pid")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "post")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private String content;

    private String nickName;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public void changeContent(String content) {
        this.content = content;
    }

    public void setPost(int pid) {
        this.post = Post.builder().pid(pid).build();
    }
}
