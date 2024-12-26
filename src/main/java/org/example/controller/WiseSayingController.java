package org.example.controller;

import org.example.entity.WiseSaying;
import org.example.service.WiseSayingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private WiseSayingService wiseSayingService;
    private Scanner scanner;

    public WiseSayingController (WiseSayingService wiseSayingService){
        this.wiseSayingService=wiseSayingService;
        this.scanner=new Scanner(System.in);
    }
    public void run() throws IOException {
        while(true) {
            System.out.println("==명언 앱==");
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("등록")) {
                registerQuote();
            } else if (command.equals("종료")) {
                System.out.println("종료");
                break;
            } else if (command.equals("목록")) {
                listQuote();
            } else if (command.startsWith("삭제?id=")) {
                int id=Integer.parseInt(command.split("=")[1]);
                deleteQuote(id);
            } else if (command.startsWith("수정?id=")) {
                int id=Integer.parseInt(command.split("=")[1]);
                modifyQuote(id);
            }else if(command.equals("빌드")){
                wiseSayingService.build();
                System.out.println("빌드 완료");
            }else{
                System.out.println("유효하지 않은 명령");
            }
        }
        scanner.close();
    }

    private void registerQuote() {
        System.out.print("명언: ");
        String text = scanner.nextLine().trim();

        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        wiseSayingService.register(text,author);
        System.out.println("명언이 등록되었습니다.");
    }
    public void listQuote(){
        List<WiseSaying> sayings=new ArrayList<>();
        if (sayings.isEmpty()) {
            System.out.println("등록된 명언이 없음");
        } else {
            System.out.println("========================");
            for (int i= sayings.size()-1;i>=0;i--) {
                System.out.println(sayings.get(i));
            }
        }
    }
    public void deleteQuote(int id){
        List<WiseSaying> sayings=new ArrayList<>();
        wiseSayingService.delete(id);
    }
    public WiseSaying modifyQuote(int id) {
        List<WiseSaying> sayings = new ArrayList<>();

        for (int i = 0; i < sayings.size(); i++) {
            if (id == sayings.get(i).getId()) {
                System.out.println("명언(기존) : " + sayings.get(i).getText());
                System.out.print("명언 : ");
                String newText = scanner.nextLine();

                System.out.println("작가(기존) : " + sayings.get(i).getAuthor());
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine();
                wiseSayingService.modify(id,newText,newAuthor);
                return sayings.get(i);
            }
        }
        System.out.println(id + "번 명언은 존재하지 않습니다");
        return null;
    }
}
