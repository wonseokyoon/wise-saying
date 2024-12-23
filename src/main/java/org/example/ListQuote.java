package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListQuote {
    Scanner scanner=new Scanner(System.in);
    private List<Quote> quotes;
    public ListQuote(List<Quote> quotes){
        this.quotes=quotes;
    }
    public void start(){
        if (quotes.isEmpty()) {
            System.out.println("등록된 명언이 없음");
        } else {
            System.out.println("========================");
            for (int i= quotes.size()-1;i>=0;i--) {
                System.out.println(quotes.get(i));
            }
        }
    }

}
