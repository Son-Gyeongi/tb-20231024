package com.ll.base;

import com.ll.domain.quotation.QuotationController;

import java.util.Scanner;

//프로그램 중심
// 외부에서 new App() 생성자 호출해야해서 App클래스는 public이다. 그리고 생성자도 public 붙여준다.
public class App {
    private Scanner scanner; // 표준  입력, 키보드

    public App() {
        scanner = new Scanner(System.in); // 표준  입력, 키보드
    }

    public void run() {
        System.out.println("==명언 앱==");

        // Controller로 토스한다. 핸들링 역할만 한다.
        QuotationController quotationController = new QuotationController(scanner);

        while (true) { // 반복문, 참이면 실행
            System.out.print("명령 ) ");

            String cmd = scanner.nextLine(); // 고객이 입력하고 엔터 누를 때까지 정지

            // 장기기억으로 만들려면 객체를 사용
            // 요청에 대한 책임을 떠안느다.
            Rq rq = new Rq(cmd);
            // Rq를 만들어서 더이상 cmd를 잡고 씨름할 필요없다.
            // cmd에 대한 처리는 모두 rq에 맡겼다. -> if문에서 switch문으로 바꿀 수 있다.

            switch (rq.getAction()) {
                case "종료":
//                    break; // switch를 끝내는 구문
                    return; // run 함수가 끝난다.
                case "등록":
                    quotationController.actionWrite();
                    break;
                case "목록":
                    quotationController.actionList();
                    break;
                case "삭제":
                    quotationController.actionRemove(rq);
                    break;
                case "수정":
                    quotationController.actionModify(rq);
                    break;
            }
        }
    }
}
