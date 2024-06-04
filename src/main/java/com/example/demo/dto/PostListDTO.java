package com.example.demo.dto;

import java.time.LocalDateTime;

public class PostListDTO {

    private String nickName;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public PostListDTO(String nickName, String title, String content, LocalDateTime regDate, LocalDateTime modDate) {
        this.nickName = nickName;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
