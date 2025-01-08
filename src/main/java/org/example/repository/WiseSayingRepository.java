package org.example.repository;

import org.example.entity.WiseSaying;
import org.example.service.WiseSayingService;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private static final String path="db/wiseSaying";
    private List<WiseSaying> wiseSayingList=new ArrayList<>();
    private static int lastId=1;

    public List<WiseSaying> findAll(){
        return wiseSayingList;
    }
    public int register(String text,String author){
        WiseSaying wiseSaying=new WiseSaying(++lastId,text,author);
        wiseSayingList.add(wiseSaying);
        saveLastId(lastId);
        saveToFile(wiseSaying);
        return lastId;
    }
    public void modify(WiseSaying wiseSaying,String newText,String newAuthor){
        wiseSaying.setText(newText);
        wiseSaying.setAuthor(newAuthor);
    }

    public WiseSaying findById(int id) {
        return wiseSayingList.get(id);
    }

    public void delete(WiseSaying wiseSaying){
        //리스트에서 제거
        wiseSayingList.removeIf(wisesaying -> wisesaying.getId()== wiseSaying.getId());
        //파일 삭제
        File file=new File(path+"/"+wiseSaying.getId()+".json");
        if(file.exists()){
            file.delete();
        }else {
            System.out.println("존재하지 않는 id번호");
        }
    }
    public void build();
    public void saveLastId(int id);



}
