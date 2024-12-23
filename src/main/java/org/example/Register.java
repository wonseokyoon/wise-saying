package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Register {
    private List<Quote> quotes;
    public Register(List<Quote> quotes){
        this.quotes=quotes;
    }
    Scanner scanner=new Scanner(System.in);

    public void start(int id){
        System.out.print("명언: ");
        String text = scanner.nextLine().trim();

        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        quotes.add(new Quote(id, text, author));
        System.out.println(id + "번 명언이 등록되었습니다");
    }

}
