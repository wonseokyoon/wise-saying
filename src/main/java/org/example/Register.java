package org.example;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Register {
    private List<Quote> quotes;
    public Register(List<Quote> quotes){
        this.quotes=quotes;
    }
    Scanner scanner=new Scanner(System.in);

    public void start(int id,String path){
        System.out.print("명언: ");
        String text = scanner.nextLine().trim();

        System.out.print("작가: ");
        String author = scanner.nextLine().trim();
        Quote quote=new Quote(id,text,author);
//        quotes.add(new Quote(id, text, author));
        System.out.println(id + "번 명언이 등록되었습니다");

        JSONObject json=new JSONObject();
        json.put("id",quote.getId());
        json.put("text",quote.getText());
        json.put("author",quote.getAuthor());

        String jsonPath=path+"/"+quote.getId()+".json";
        try {
            FileWriter writer=new FileWriter(jsonPath);
            writer.write(json.toString(4));
            writer.close();
        }catch (IOException e){
            System.out.println("오류"+e.getMessage());
        }


    }

}
