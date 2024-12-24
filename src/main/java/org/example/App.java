package org.example;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App{
    private static int id=1;
    List<Quote> quotes=new ArrayList<>();
    // 명언 저장 할 위치
    private static final String path="db/wiseSaying";

    public void run(){
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("==명언 앱==");
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("등록")) {
                Register register=new Register(quotes);
                register.start(id);
                Quote newQuote=quotes.get(quotes.size()-1); //등록한 quote를 새로운 객체에 저장
                saveFile(newQuote);
                saveLastId(id);
                id++;
            } else if (command.equals("종료")) {
                break;
            } else if (command.equals("목록")) {
                ListQuote listQuote=new ListQuote(quotes);
                listQuote.start();
            } else if (command.startsWith("삭제?id=")) {
                int id = Integer.parseInt(command.substring(6));
                Delete delete=new Delete(quotes);
                delete.start(id);
            } else if (command.startsWith("수정?id=")) {
                int id = Integer.parseInt(command.substring(6));
                Modify modify = new Modify(quotes);
                modify.start(id);
            }
        }
        scanner.close();
    }

    private void saveLastId(int id) {   //마지막 id 저장
        try{
            FileWriter writer=new FileWriter(path+"/lastId.txt");
            writer.write(String.valueOf(id));
            writer.close();
        }catch (IOException e){
            System.out.println("오류"+e.getMessage());
        }
    }

    public void saveFile(Quote quote){  // 파일 저장
        JSONObject json=new JSONObject();   //Json 객체 생성
        json.put("id",quote.getId());
        json.put("text",quote.getText());
        json.put("author",quote.getAuthor());

        String jsonPath=path+"/"+quote.getId()+".json";
        try{
            FileWriter writer=new FileWriter(jsonPath);
            writer.write(json.toString(4)); //들여쓰기(4번) 포함해서 json문자열 작성
            writer.close();  //리소스 해제
        }catch (IOException e){
            System.out.println("오류"+e.getMessage());
        }

    }
}
