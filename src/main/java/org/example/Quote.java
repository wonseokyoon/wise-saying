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

    public int getId() {
        return id;
    }
    public void setText(String text){
        this.text=text;
    }
    public void setAuthor(String author){
        this.author=author;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }
}
