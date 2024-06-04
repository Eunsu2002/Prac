package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    private int aid;

    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 8, max = 20)
    private String password;

    @NotEmpty
    private String nickName;
}
