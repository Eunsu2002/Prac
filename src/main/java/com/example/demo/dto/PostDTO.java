package com.example.demo.dto;

import com.example.demo.entity.Post;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private int pid;
    private String nickName;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
