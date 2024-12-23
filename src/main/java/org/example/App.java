package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App{
    public void run(){
        Scanner scanner=new Scanner(System.in);
        List<Quote> quotes=new ArrayList<>();

        while(true){
            System.out.print("명령) ");
            String command=scanner.nextLine();

            if(command.equals("등록")){
                System.out.print("명언: ");
                String text=scanner.nextLine();
                System.out.print("작가: ");
                String author=scanner.nextLine();

                quotes.add(new Quote(text,author));
                System.out.println("1번 명언이 등록되었습니다");
            } else if (command.equals("종료")) {
                break;
            }
        }

    }
}
