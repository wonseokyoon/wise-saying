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

        while(true) {
            System.out.println("==명언 앱==");
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("등록")) {
                Register register=new Register(quotes);
                register.start(id);
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
}
