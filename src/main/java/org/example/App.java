package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App{
    private static int id=1;
    public void run(){
        Scanner scanner=new Scanner(System.in);
        List<Quote> quotes=new ArrayList<>();

        while(true){
            System.out.println("==명언 앱==");
            System.out.print("명령) ");
            String command=scanner.nextLine().trim();

            if(command.equals("등록")){
                System.out.print("명언: ");
                String text=scanner.nextLine().trim();

                System.out.print("작가: ");
                String author=scanner.nextLine().trim();

                quotes.add(new Quote(id++,text,author));
                System.out.println(id-1+"번 명언이 등록되었습니다");
            }
            else if (command.equals("종료")) {
                break;
            }
            else if (command.equals("목록")) {
                if(quotes.isEmpty()){
                    System.out.println("등록된 명언이 없음");
                }else {
                    System.out.println("========================");
                    for (Quote quote : quotes) {
                        System.out.println(quote.toString());
                    }
                }
            }
        }

    }
}
