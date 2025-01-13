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

    public void run() throws IOException{

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
                wiseSayingController.paging(1);
            } else if (command.startsWith("삭제?id=")) {
                wiseSayingService.loadWiseSayings();
                int id=Integer.parseInt(command.split("=")[1]);
                wiseSayingController.deleteQuote(id);
                wiseSayingService.loadWiseSayings();
            } else if (command.startsWith("수정?id=")) {
                wiseSayingService.loadWiseSayings();
                int id=Integer.parseInt(command.split("=")[1]);
                wiseSayingController.modifyQuote(id);
                wiseSayingService.loadWiseSayings();
            }else if(command.equals("빌드")){
                wiseSayingService.loadWiseSayings();
                wiseSayingController.build();
                wiseSayingService.loadWiseSayings();
                System.out.println("빌드 완료");
            } else if (command.equals("검색")) {  //목록?type=content&word=후
                wiseSayingService.loadWiseSayings();
                System.out.print("타입(content / author): ");
                String type=scanner.nextLine().trim();
                System.out.print("검색어: ");
                String word=scanner.nextLine().trim();

                wiseSayingController.search(type,word);
            } else if(command.startsWith("목록?")){
                wiseSayingService.loadWiseSayings();
                String[] param=command.split("\\?");
                if(!param[1].split("=")[0].equals("page")){
                    System.out.println("유효하지 않은 명령");
                }
                int pageNum= Integer.parseInt(param[1].split("=")[1]);
                wiseSayingController.paging(pageNum);
            }
            else{
                System.out.println("유효하지 않은 명령");
            }
            wiseSayingService.loadWiseSayings();    //로딩
        }
        scanner.close();
    }
}