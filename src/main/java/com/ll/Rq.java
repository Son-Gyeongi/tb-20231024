package com.ll;

import java.util.HashMap;
import java.util.Map;

/*
Rq 안에 내용 못 짜도 상관없다.
그러나 Rq 사용법은 알아야 한다.
 */
public class Rq {
    // 장기기억으로 만들려면 객체를 사용

    String cmd;
    String action;
    String queryString;

    Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();

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

            paramsMap.put(paramName, paramValue);
        }
    }

    String getAction() {
        return action;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String paramValue = paramsMap.get(paramName);

        // 값이 있는 경우
        if (paramValue != null) {
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException e) {
            }
        }

        return defaultValue;
    }
}
