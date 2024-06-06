package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)                     // 자동 입력
    @Column(name = "pid")                                                   // DB 칼럼명 pid
    private int pid;                                                        // 사용자 번호

    @NotEmpty
    @Column(name = "nick_name")
    private String nickName;                                                // 사용자에게 받은 닉네임

    @Column(nullable = false, length = 600, name = "content")               // 칼럼명 content
    private String content;                                                 // 내용

    @CreatedDate
    @Builder.Default
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "modDate")
    private LocalDateTime modDate;

    public void changePost(String content){
        this.content = content;
    }

    public void changeNickName(String nickName){this.nickName=nickName;}
}
