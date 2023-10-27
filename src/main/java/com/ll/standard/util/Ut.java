package com.ll.standard.util;

public class Ut { // Utility
    // 내부 클래스 - 유틸리티를 용도별로 나누려고 내부클래스 만들었다. str, url, img 등
    public static class str { // 문장에 관련된 함수
        // 숫자로 변환
        public static int parseInt(String value, int defaultValue) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }
}
