package com.example.demo.service;

import com.example.demo.dto.AuthDTO;
import com.example.demo.entity.Auth;

public interface AuthService {
    static class AidExistException extends Exception {

        public AidExistException() {}
        public AidExistException(String message) {super(message);}

    }

    static class EmailExistException extends Exception {

        public EmailExistException() {}
        public EmailExistException(String message) {super(message);}

    }

    Auth join(AuthDTO authDTO) throws AidExistException, EmailExistException;

    AuthDTO info(int id);
    void modify(AuthDTO authDTO);
    void remove(int id);
}
