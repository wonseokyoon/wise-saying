package org.example;

import org.example.controller.WiseSayingController;
import org.example.service.WiseSayingService;

import java.io.IOException;
import java.util.Scanner;

public class App{
    private WiseSayingController wiseSayingController;
    private WiseSayingService wiseSayingService;
    public App(WiseSayingController wiseSayingcontroller,WiseSayingService wiseSayingService){
        this.wiseSayingController=wiseSayingcontroller;
        this.wiseSayingService=wiseSayingService;
    }

    public void run() throws IOException {

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("==명언 앱==");
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("등록")) {
                wiseSayingController.registerQuote();
                wiseSayingService.loadWiseSayings();
            } else if (command.equals("종료")) {
                System.out.println("종료");
                break;
            } else if (command.equals("목록")) {
                wiseSayingService.loadWiseSayings();
                wiseSayingController.listQuote();
            } else if (command.startsWith("삭제?id=")) {
                int id=Integer.parseInt(command.split("=")[1]);
                wiseSayingController.deleteQuote(id);
                wiseSayingService.loadWiseSayings();
            } else if (command.startsWith("수정?id=")) {
                int id=Integer.parseInt(command.split("=")[1]);
                wiseSayingController.modifyQuote(id);
                wiseSayingService.loadWiseSayings();
            }else if(command.equals("빌드")){
                wiseSayingController.build();
                wiseSayingService.loadWiseSayings();
                System.out.println("빌드 완료");
            } else if (command.startsWith("목록?type")) {  //목록?type=content&word=후
                String str=command.split("\\?")[1];   //type=content&word=후
                String type=str.split("&")[0].split("=")[1];  //type
                String word=str.split("&")[1].split("=")[1];
                wiseSayingController.search(type,word);
            } else{
                System.out.println("유효하지 않은 명령");
            }
            wiseSayingService.loadWiseSayings();    //로딩
        }
        scanner.close();
    }
}