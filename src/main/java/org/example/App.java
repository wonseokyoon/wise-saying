package org.example;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App{
    private static int lastId=1;
    List<Quote> quotes=new ArrayList<>();
    // 명언 저장 할 위치
    private static final String path="db/wiseSaying";

    public void run() throws IOException {
        loadFile(); //파일 로드
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("==명언 앱==");
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("등록")) {
                Register register=new Register(quotes);
                register.start(lastId,path);
                Quote newQuote=quotes.get(quotes.size()-1); //등록한 quote를 새로운 객체에 저장
                saveFile(newQuote);
                saveLastId(lastId);
                lastId++;
            } else if (command.equals("종료")) {
                break;
            } else if (command.equals("목록")) {
                ListQuote listQuote=new ListQuote(quotes);
                listQuote.start();
            } else if (command.startsWith("삭제?id=")) {
                int id = Integer.parseInt(command.substring(6));
                Delete delete=new Delete(quotes);
                delete.start(id);
                deleteFile(id);
            } else if (command.startsWith("수정?id=")) {
                int id = Integer.parseInt(command.substring(6));
                Modify modify = new Modify(quotes);
                Quote modified=modify.start(id);
                modifyFile(modified);
            }else if(command.equals("빌드")){
                JsonBuild.start();
            }
        }
        scanner.close();
    }

    private void modifyFile(Quote quote) {
        deleteFile(quote.getId());  //삭제
        saveFile(quote);    //재생성
    }

    private void deleteFile(int deleteId) {
        //삭제할 파일
        File file=new File(path+"/"+deleteId+".json");
        file.delete();
        return;
    }

    private void loadFile() throws IOException {
        File folder=new File(path);   // 경로가 path인 폴더
        File[] files=folder.listFiles();    //folder의 file리스트
        File datajson=new File(path+"/data.json");
        if(files!=null){
            for(File file:files){
                //lastId 추출
                if(file.getName().endsWith(".json")&& !file.equals(datajson)){
                    try{
                        String contents=new String(Files.readAllBytes(file.toPath()));
                        JSONObject json=new JSONObject(contents);

                        int id= json.getInt("id");
                        String text=json.getString("text");
                        String author=json.getString("author");

                        quotes.add(new Quote(id,text,author));
                        lastId=Math.max(lastId,id+1);
                    }catch (IOException e){
                        System.out.println("파일 로드 실패"+e.getMessage());
                    }catch (JSONException e){
                        System.out.println("Json 오류"+e.getMessage());
                    }
                }
            }
        }else{
            System.out.println("파일이 없음");
        }

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