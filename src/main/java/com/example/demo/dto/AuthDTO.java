// 데이터의 중간 다리 역할 -> DTO
package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthDTO {

    private int aid;
    private String email;
    private String password;
    private String nickName;
}
