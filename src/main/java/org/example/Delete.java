package org.example;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Delete {
    Scanner scanner=new Scanner(System.in);
    private List<Quote> quotes;
    public Delete(List<Quote> quotes){
        this.quotes=quotes;
    }
    public void start(int id){
        for (int i = 0; i < quotes.size(); i++) {
            if (id == quotes.get(i).getId()) {
                quotes.remove(i);
                System.out.println((id + "번 명언이 삭제되었습니다."));
                return;
            }
        }
        // 일치하는 번호 없을 경우 도달
        System.out.println(id + "번 명언은 존재하지 않습니다");
    }

}
