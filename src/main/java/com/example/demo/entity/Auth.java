package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "aid")
    private int aid;

    @NotEmpty
    @Column(unique = true, name = "email")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(unique = true, name = "nick_name")
    private String nickName;
}
