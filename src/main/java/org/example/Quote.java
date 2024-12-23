package org.example;

public class Quote {
    private String text;
    private String author;

    public Quote(String text,String author){
        this.text=text;
        this.author=author;
    }

    @Override
    public String toString(){
        return "명언 " + text + "\n작가: "+ author;
    }

}
