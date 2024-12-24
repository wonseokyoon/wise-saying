package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Modify {
    private List<Quote> quotes;
    public Modify(List<Quote> quotes) {
        this.quotes = quotes;
    }
    Scanner scanner=new Scanner(System.in);
    public Quote start(int id) {
        for (int i = 0; i < quotes.size(); i++) {
            if (id == quotes.get(i).getId()) {
                System.out.println("명언(기존) : " + quotes.get(i).getText());
                System.out.print("명언 : ");
                String newText = scanner.nextLine();
                quotes.get(i).setText(newText);

                System.out.println("작가(기존) : " + quotes.get(i).getAuthor());
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine();
                quotes.get(i).setAuthor(newAuthor);


                return quotes.get(i);
            }
        }
        System.out.println(id + "번 명언은 존재하지 않습니다");
        return null;
    }

}