package com.ll.domain.quotation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor // 모든 필드를 입력받는 생성자
public class Quotation {
    // 명언을 위한 정보 3개 필요
    // 외부에서 사용한다. - 목록 출력할 때
    // 클래스의 필드 들은 private으로 만드는 걸 권장한다.
    @Getter
    private int id;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private String authorName;
}
