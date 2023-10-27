package com.ll.domain;

import com.ll.standard.util.Ut;

import java.util.HashMap;
import java.util.Map;

/*
Rq 안에 내용 못 짜도 상관없다.
그러나 Rq 사용법은 알아야 한다.
 */
public class Rq {
    // 장기기억으로 만들려면 객체를 사용

    private String cmd;
    private String action;
    private String queryString;

    private Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();

        this.cmd = cmd;

        // cmd 나누가 / Bits 조각이라는 뜻
        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();

        if (cmdBits.length == 1) { // 등록, 수정 만 있다면
            return;
            // ?가 없으니 이후로 작업하지 않겠다.
        }

        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramsMap.put(paramName, paramValue);
        }
    }

    public String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        // 수정?id=5, paramsMap.get(paramName)에 문자열 "5"가 들어온다.
        return Ut.str.parseInt(paramsMap.get(paramName), defaultValue);
    }
}
