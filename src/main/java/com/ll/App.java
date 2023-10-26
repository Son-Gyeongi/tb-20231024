package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//프로그램 중심
class App {
    Scanner scanner; // 표준  입력, 키보드
    int lastQuotationId; // 명언 번호
    //     Quotation[] quotations = new Quotation[100]; // 명언 리모컨을 100개 담을 수 있는 배열 객체 1개, 고정크기
    List<Quotation> quotations; // 가변크기 리스트

    App() {
        scanner = new Scanner(System.in); // 표준  입력, 키보드
        lastQuotationId = 0; // 명언 번호
        quotations = new ArrayList<>(); // 가변크기 리스트
    }

    void run() {
        System.out.println("==명언 앱==");

        while (true) { // 반복문, 참이면 실행
            System.out.print("명령 ) ");

            String cmd = scanner.nextLine(); // 고객이 입력하고 엔터 누를 때까지 정지

            // 장기기억으로 만들려면 객체를 사용
            Rq rq = new Rq(cmd);

            System.out.println("rq.getAction : " + rq.getAction());
            System.out.println("rq.getParamAsInt : " + rq.getParamAsInt("id", 0)); // id의 값을 찾고 없으면 0을 반환한다.

            if (cmd.equals("종료")) { // 문장 비교는 equals()
                break; // 나를 감싼 반복문 종료
//                continue; // 한턴만 쉰다.
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?")) {
                actionRemove(cmd);
            } else if (cmd.startsWith("수정?")) {
                actionModify(cmd);
            }
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine(); // 멈추고 입력받는다.

        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++;
        int id = lastQuotationId; // 가독성 때문에 만들었다.

        // Qutotation 생성자로 초기화하고 객체 만들기
        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        if (quotations.isEmpty()) { // 명언이 없다면
            System.out.println("등록된 명언이 없습니다.");
        }

        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }

    void actionRemove(String cmd) {
        // 내가 몇번 삭제하면 돼?
        int id = getParamAsInt(cmd, "id", 0);

        if (id == 0) {
            System.out.println("id를 입력해주세요.");
            return; // 함수를 끝낸다.
        }

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    void actionModify(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

    int getParamAsInt(String cmd, String paramName, int defaultValue) {
        // Bits 조각이라는 뜻
        String[] cmdBits = cmd.split("\\?", 2);
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        int id = 0;

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String _paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (_paramName.equals(paramName)) {
                // 실패 대비
                try {
                    // 문제가 없을 경우
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    // 문제가 생긴 경우
                    return defaultValue;
                }
            }
        }

        return defaultValue;
    }
}
