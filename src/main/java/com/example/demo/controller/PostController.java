package com.example.demo.controller;

import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

//    @GetMapping("/list")
//    public void list(PageRequestDTO pageRequestDTO, Model model) {
//
//        PageResponseDTO<PostDTO> responseDTO = postService.list(pageRequestDTO);
//
//        model.addAttribute("responseDTO", responseDTO);
//    }

    @GetMapping("/register")
    public void registerGet() {}

    @GetMapping("/register")
    public String registerPost(
            @Valid PostDTO postDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        log.info("board POST register .........");

        if (bindingResult.hasErrors()) {
            log.info("had Errors ..........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/post/register";
        }

        log.info(postDTO);

        int pid = postService.register(postDTO);
        redirectAttributes.addFlashAttribute("result", pid);

        return "redirect:/post/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(int pid, PageRequestDTO pageRequestDTO, Model model) {
        PostDTO postDTO = postService.readOne(pid);
        log.info(postDTO);
        model.addAttribute("dto", postDTO);
    }

    @PreAuthorize("principal.username == #postDTO.nickName")   //수정 처리는 게시물 작성자와 로그인한 사용자가 같은 경우...
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid PostDTO postDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("modify post ...... " + postDTO);
        if(bindingResult.hasErrors()) {
            log.info("has errors......");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("pid", postDTO.getPid());
            return "redirect:/post/modify?"+link;
        }

        postService.modify(postDTO);
        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addAttribute("pid", postDTO.getPid());
        return "redirect:/post/read";

    }

    @PreAuthorize("principal.username == #postDTO.nickName")
    @PostMapping("/remove")
    public String remove(PostDTO postDTO, RedirectAttributes redirectAttributes) {
        log.info("remove post.... "+ postDTO.getPid());
        postService.remove(postDTO.getPid());
        redirectAttributes.addFlashAttribute("result","removed");
        return "redirect:/post/list";
    }
}
