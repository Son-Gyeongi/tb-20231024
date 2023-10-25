package com.ll;

import java.util.Scanner;

//프로그램 중심
class App {
    void run() {
        System.out.println("==명언 앱==");

        while (true) { // 반복문, 참이면 실행
            System.out.print("명령 ) ");

            // Scanner 외워라
            Scanner scanner = new Scanner(System.in); // 표준  입력, 키보드
            String cmd = scanner.nextLine(); // 고객이 입력하고 엔터 누를 때까지 정지

            if (cmd.equals("종료")) { // 문장 비교는 equals()
                break; // 나를 감싼 반복문 종료
//                continue; // 한턴만 쉰다.
            }

            System.out.printf("입력하신 명령어 : %s\n", cmd);
            // 위 라인은 아래 라인과 같은 뜻
//        System.out.println("입력하신 명령어 : "+cmd+"\n");
        }
    }
}
