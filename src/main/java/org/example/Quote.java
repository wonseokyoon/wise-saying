package org.example;

public class Quote {
    private String text;
    private String author;
    private int id;

    public Quote(int id,String text,String author){
        this.id=id;
        this.text=text;
        this.author=author;
    }

    @Override
    public String toString(){
        return id + " / "+
                text + " / "+
                author;
    }

}
