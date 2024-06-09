package com.example.demo.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    public Pageable getPageable(String...props){
        return PageRequest.of(0, this.size, Sort.by(props).descending());
    }

    private String link; // 캐시된 링크 문자열

    // 페이지네이션과 검색을 위한 쿼리 문자열 생성 메서드
    public String getLink(){
        if(link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("&size=").append(this.size); // 페이지와 크기 파라미터 추가
            link = builder.toString(); // 생성된 링크를 캐시에 저장
        }

        return link; // 캐시된 링크 반환
    }
}
