package com.ll.domain;

public class Quotation {
    // 명언을 위한 정보 3개 필요
    // 외부에서 사용한다. - 목록 출력할 때
    public int id;
    public String content;
    public String authorName;

    public Quotation(int id, String content, String authorName) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
    }
}
