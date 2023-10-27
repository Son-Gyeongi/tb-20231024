package com.ll.domain;

public class Quotation {
    // 명언을 위한 정보 3개 필요
    // 외부에서 사용한다. - 목록 출력할 때
    // 클래스의 필드 들은 private으로 만드는 걸 권장한다.
    private int id;
    private String content;
    private String authorName;

    public Quotation(int id, String content, String authorName) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
