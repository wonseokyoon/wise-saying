package org.example.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WiseSaying {
    private int id;
    private String text;
    private String author;

    public WiseSaying(int id,String text,String author){
        this.id=id;
        this.text=text;
        this.author=author;
    }

    @Override
    public String toString(){
        return id+" / " + text + " / " + author;
    }
    //역순 정렬

}
