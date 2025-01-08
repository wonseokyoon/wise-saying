package org.example.controller;

import org.example.entity.WiseSaying;
import org.example.service.WiseSayingService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private WiseSayingService wiseSayingService;
    private Scanner scanner;
    private static final String path="db/wiseSaying";
    public WiseSayingController (WiseSayingService wiseSayingService){
        this.wiseSayingService=wiseSayingService;
        this.scanner=new Scanner(System.in);
    }

    public void registerQuote() throws IOException {
        System.out.print("명언: ");
        String text = scanner.nextLine().trim();

        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        int id = wiseSayingService.register(text,author);
        saveLastId(id);
        System.out.println((id-1)+"번 명언이 등록되었습니다.");

    }

    public void listQuote(){
        List<WiseSaying> sayings=wiseSayingService.findAll();
        // 오름차순 정렬
        sayings.sort((s1,s2)->Integer.compare(s1.getId(), s2.getId()));
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
        List<WiseSaying> sayings=wiseSayingService.findAll();
        if(!wiseSayingService.delete(id)){   // 삭제 실패
             System.out.println("존재하지 않는 id번호");
        }
        System.out.println((id)+"번 명언이 삭제되었습니다.");
    }
    public WiseSaying modifyQuote(int id) {
        List<WiseSaying> sayings=wiseSayingService.findAll();
        for (int i = 0; i < sayings.size(); i++) {
            if (id == sayings.get(i).getId()) {
                System.out.println("명언(기존) : " + sayings.get(i).getText());
                System.out.print("명언 : ");
                String newText = scanner.nextLine();

                System.out.println("작가(기존) : " + sayings.get(i).getAuthor());
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine();
                wiseSayingService.modify(id,newText,newAuthor);
                System.out.println((id)+"번 명언이 수정되었습니다.");
                return sayings.get(i);
            }
        }
        System.out.println((id) + "번 명언은 존재하지 않습니다");
        return null;
    }
    public void build(){
        List<WiseSaying> wiseSayingList=wiseSayingService.loadWiseSayings();
        try {
            wiseSayingService.build(wiseSayingList);
        } catch (IOException e) {
            System.out.println("Json 빌드 오류");
            e.printStackTrace();
        }
    }

    public void saveLastId(int id){
        try{
            WiseSayingService.saveLastId(id);
        }catch (IOException e){
            System.out.println("ID 저장 오류: "+e.getMessage());
        }
    }
}
