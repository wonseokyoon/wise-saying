package org.example.repository;

import org.example.entity.WiseSaying;
import org.example.service.WiseSayingService;
import org.json.JSONObject;

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
    public void modify(int id,String text,String author);
    public void delete(int id);
    public void build();
    public void saveLastId(int id);

    //파일 저장
    private static void saveToFile(WiseSaying wiseSaying) {
        JSONObject json=new JSONObject();
        json.put("id",wiseSaying.getId());
        json.put("text",wiseSaying.getText());
        json.put("author",wiseSaying.getAuthor());
        String jsonPath=path+"/"+wiseSaying.getId()+".json";
        try (FileWriter writer=new FileWriter(jsonPath)){
            writer.write(json.toString(4));
        }catch (IOException e){
            System.out.println("저장 오류: "+e.getMessage());
        }
    }

}
