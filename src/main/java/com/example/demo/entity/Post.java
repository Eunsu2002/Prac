package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private int pid;

    @Column(name = "nick_name")
    private String nickName;

    @Column(nullable = false, length = 600)
    private String content;

    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "modDate")
    private LocalDateTime modDate;

    public void changePost(String content){
        this.content = content;
    }
}
