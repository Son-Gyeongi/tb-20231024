package com.ll;

public class Quotation {
    // 명언을 위한 정보 3개 필요
    int id;
    String content;
    String authorName;

    public Quotation(int id, String content, String authorName) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
    }
}
