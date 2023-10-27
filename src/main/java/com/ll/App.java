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
            // 요청에 대한 책임을 떠안느다.
            Rq rq = new Rq(cmd);
            // Rq를 만들어서 더이상 cmd를 잡고 씨름할 필요없다.
            // cmd에 대한 처리는 모두 rq에 맡겼다. -> if문에서 switch문으로 바꿀 수 있다.

            switch (rq.getAction()) {
                case "종료" :
//                    break; // switch를 끝내는 구문
                    return; // run 함수가 끝난다.
                case "등록" :
                    actionWrite();
                    break;
                case "목록" :
                    actionList();
                    break;
                case "삭제" :
                    actionRemove(rq);
                    break;
                case "수정" :
                    actionModify(rq);
                    break;
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

    void actionRemove(Rq rq) {
        // 내가 몇번 삭제하면 돼?
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }

        // 우리가 제거할 리모콘이 몇 번째인지 알아야 한다.
        int index = getIndexOfQuotationById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        quotations.remove(index);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    int getIndexOfQuotationById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if (quotation.id == id) {
                return i;
            }
        }
        return -1;
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }
}
