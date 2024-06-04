package com.example.demo.dto;

import lombok.Builder;

import java.util.List;

public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;
    private boolean realEnd;

    private List<E> postLists;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO (PageRequestDTO pageRequestDTO, List<E> postLists, int total, boolean realEnd){
        if (total <= 0){
            return;
        }
        this.total = total;
        this.realEnd = realEnd;
        this.postLists = postLists;
    }
}
