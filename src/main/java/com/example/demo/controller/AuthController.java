package com.example.demo.controller;

import com.example.demo.dto.AuthDTO;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public void loginGET(String error, String logout) {
        log.info("login get ........");
        log.info("logout : " + logout);

        if (logout != null) {
            log.info("user logout ........");
        }
    }

    @GetMapping("/join")
    public void joinGET() {
        log.info("join get ........");
    }

    @GetMapping("/join")
    public String joinPOST(AuthDTO authDTO, RedirectAttributes redirectAttributes) {

        log.info("join post ........");
        log.info(authDTO);

        try {
            authService.join(authDTO);
        } catch (AuthService.AidExistException | AuthService.EmailExistException e) {
            redirectAttributes.addFlashAttribute("error", "aid");
            return "redirect:/auth/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/auth/login";
    }
}
