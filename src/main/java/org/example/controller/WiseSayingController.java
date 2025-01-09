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
        System.out.println(id+"번 명언이 등록되었습니다.");

    }

    public void listQuote(){
        List<WiseSaying> wiseSayingList=wiseSayingService.findAll();
        // 오름차순 정렬
        wiseSayingList.sort((wiseSaying1,wiseSaying2)
                ->Integer.compare(wiseSaying1.getId(), wiseSaying2.getId()));
        if (wiseSayingList.isEmpty()) {
            System.out.println("등록된 명언이 없음");
        } else {
            System.out.println("========================");
            for (int i= wiseSayingList.size()-1;i>=0;i--) {
                System.out.println(wiseSayingList.get(i));
            }
        }
    }

    public void deleteQuote(int id){
        if(!wiseSayingService.delete(id)){   // 삭제 실패
             System.out.println("존재하지 않는 id번호");
        }
        else{
            System.out.println((id)+"번 명언이 삭제되었습니다.");
        }
    }
    public WiseSaying modifyQuote(int id) {
        List<WiseSaying> wiseSayingList=wiseSayingService.findAll();
        for (int i = 0; i < wiseSayingList.size(); i++) {
            if (id == wiseSayingList.get(i).getId()) {
                System.out.println("명언(기존) : " + wiseSayingList.get(i).getText());
                System.out.print("명언 : ");
                String newText = scanner.nextLine();

                System.out.println("작가(기존) : " + wiseSayingList.get(i).getAuthor());
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine();
                wiseSayingService.modify(id,newText,newAuthor);
                System.out.println((id)+"번 명언이 수정되었습니다.");
                return wiseSayingList.get(i);
            }
        }
        System.out.println((id) + "번 명언은 존재하지 않습니다");
        return null;
    }
    public void build(){
        wiseSayingService.loadWiseSayings();
        List<WiseSaying> wiseSayingList=wiseSayingService.findAll();
        try {
            wiseSayingService.build(wiseSayingList);
        } catch (IOException e) {
            System.out.println("Json 빌드 오류");
            e.printStackTrace();
        }
    }

    public void search(String type, String word) {
        wiseSayingService.loadWiseSayings();    //로딩
        System.out.println("검색 타입 : "+ type);
        System.out.println("검색어 : "+ word);
        System.out.println("----------------------");
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> findList=wiseSayingService.search(type,word);
        if(findList==null){
            System.out.println(word+ "는 목록에 없습니다.");
        }
        findList.sort((w1,w2)->Integer.compare(w1.getId(), w2.getId()));
        for(WiseSaying wiseSaying:findList){
            System.out.println(wiseSaying);
        }
    }

}
