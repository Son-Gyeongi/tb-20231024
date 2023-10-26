package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//프로그램 중심
class App {
    void run() {
        System.out.println("==명언 앱==");

        int lastQuotationId = 0; // 명언 번호

//        Quotation[] quotations = new Quotation[100]; // 명언 리모컨을 100개 담을 수 있는 배열 객체 1개, 고정크기
        List<Quotation> quotations = new ArrayList<>(); // 가변크기 리스트

        while (true) { // 반복문, 참이면 실행
            System.out.print("명령 ) ");

            // Scanner 외워라
            Scanner scanner = new Scanner(System.in); // 표준  입력, 키보드
            String cmd = scanner.nextLine(); // 고객이 입력하고 엔터 누를 때까지 정지

            if (cmd.equals("종료")) { // 문장 비교는 equals()
                break; // 나를 감싼 반복문 종료
//                continue; // 한턴만 쉰다.
            } else if (cmd.equals("등록")) {

                System.out.print("명언 : ");
                String  content = scanner.nextLine(); // 멈추고 입력받는다.

                System.out.print("작가 : ");
                String authorName = scanner.nextLine();

                lastQuotationId++;

                // Qutotation 생성자로 초기화하고 객체 만들기
                Quotation quotation = new Quotation(lastQuotationId, content, authorName);
                quotations.add(quotation);

                System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);
            } else if (cmd.equals("목록")) {
                System.out.println("총 개수 : " + quotations.size());
            }
        }
    }
}
