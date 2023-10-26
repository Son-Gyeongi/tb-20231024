package com.ll;

import java.util.ArrayList;
import java.util.List;

/*
Rq 안에 내용 못 짜도 상관없다.
그러나 Rq 사용법은 알아야 한다.
 */
public class Rq {
    // 장기기억으로 만들려면 객체를 사용

    String cmd;
    String action;
    String queryString;

    // Map을 안배워서 일단 List에 차곡차곡 저장하자
    List<String> paramNames;
    List<String> paramValues;

    public Rq(String cmd) {
        paramNames = new ArrayList<>();
        paramValues = new ArrayList<>();

        this.cmd = cmd;

        // cmd 나누가 / Bits 조각이라는 뜻
        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();
        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramNames.add(paramName);
            paramValues.add(paramValue);
        }
    }

    String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        // paramName가 없을 수도 있다. 없으면 -1을 반환한다.
        int index = paramNames.indexOf(paramName);// indexOf()는 찾는거다.

        if (index == -1) return defaultValue;

        // 원하는 paramName 찾으면
        String paramValue = this.paramValues.get(index);

        // 고객이 잘못 입력해 실패할 수도 있다.
        // 위험한 일은 try-catch()문에 감싼다.
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
