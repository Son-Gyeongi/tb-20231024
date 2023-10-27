package com.ll.domain.quotation;

import com.ll.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotationController {
    private Scanner scanner;
    private int lastQuotationId; // 명언 번호
    //     Quotation[] quotations = new Quotation[100]; // 명언 리모컨을 100개 담을 수 있는 배열 객체 1개, 고정크기
    private List<Quotation> quotations; // 가변크기 리스트

    public QuotationController(Scanner scanner) {
        this.scanner = scanner;
        lastQuotationId = 0; // 명언 번호
        quotations = new ArrayList<>(); // 가변크기 리스트

        // 앱이 시작되자마자 자동으로 데이터 들어간다.
        initTestData();
    }


    // 테스트 데이터 만들기
    private void initTestData() { // 테스트를 위해서 만들었다. 실제 배포할 때는 빼야한다.
        for (int i = 0; i < 10; i++) {
            write("명언 " + i, "작가 " + i);
        }
    }

    // action 관련 메서드들 모두 해당 클래스 내에서만 사용한다. 그래서 private이다.
    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine(); // 멈추고 입력받는다.

        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        // 객체 생성
        Quotation quotation = write(content, authorName);

        System.out.printf("%d번 명언이 등록되었습니다.\n", quotation.getId());
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        if (quotations.isEmpty()) { // 명언이 없다면
            System.out.println("등록된 명언이 없습니다.");
        }

        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.getId(), quotation.getAuthorName(), quotation.getContent());
        }
    }

    public void actionRemove(Rq rq) {
        // 내가 몇번 삭제하면 돼?
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }

        // 우리가 제거할 리모콘이 몇 번째인지 알아야 한다.
        int index = findQuotationIndexById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        quotations.remove(index);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return; // 함수를 끝낸다.
        }

        // 우리가 수정할 리모콘이 몇 번째인지 알아야 한다.
        int index = findQuotationIndexById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        Quotation quotation = quotations.get(index);

        System.out.printf("명언(기존) : %s\n", quotation.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine(); // 멈추고 입력받는다.

        System.out.printf("작가(기존) : %s\n", quotation.getAuthorName());
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        // 객체를 새로 만들 필요없이 기존에 있던 것을 새로 입력 받은 걸로 교체해주면 된다.
        quotation.setContent(content);
        quotation.setAuthorName(content);

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

    /*
    dry 뜻 - 중복 제거
    don't repeat yourself
     */
    // 객체 생성
    public Quotation write(String content, String authorName) {
        lastQuotationId++;
        int id = lastQuotationId; // 가독성 때문에 만들었다.

        // Qutotation 생성자로 초기화하고 객체 만들기
        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        return quotation;
    }

    private int findQuotationIndexById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if (quotation.getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
