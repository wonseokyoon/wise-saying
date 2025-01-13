package org.example.repository;

import org.example.entity.WiseSaying;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class WiseSayingRepository {
    private static final String path="db/wiseSaying";
    private List<WiseSaying> wiseSayingList=new ArrayList<>();
    private static int lastId=0;

    public void saveLastId(int id) throws IOException {
        FileWriter writer=new FileWriter(path+"/lastId.txt");
        writer.write(String.valueOf(id));
        writer.close();
    }
    public WiseSaying findById(int id) {
        for(WiseSaying wiseSaying:wiseSayingList){
            if(wiseSaying.getId()==id){
                return wiseSaying;
            }
        }
        return null;
    }
    public List<WiseSaying> findAll(){
        return wiseSayingList;
    }

    public int register(String text,String author) throws IOException {
        lastId=loadLastId();
        WiseSaying wiseSaying=new WiseSaying(++lastId,text,author);
        wiseSayingList.add(wiseSaying);
        saveLastId(lastId);
        saveToFile(wiseSaying);
        return wiseSaying.getId();
    }
    public boolean delete(WiseSaying wiseSaying){
        //리스트에서 제거
        boolean removed=
                wiseSayingList.removeIf(wisesaying -> wisesaying.getId()== wiseSaying.getId());
        //파일 삭제
        File file=new File(path+"/"+wiseSaying.getId()+".json");
        if(file.exists()){
            file.delete();
            return removed;
        }
        //삭제 실패시
        return false;
    }

    public void modify(WiseSaying wiseSaying,String newText,String newAuthor){
        wiseSaying.setText(newText);
        wiseSaying.setAuthor(newAuthor);
    }

    public List<WiseSaying> loadWiseSayings() {
        wiseSayingList.clear();
        File folder = new File(path);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json") && !file.getName().equals("data.json")) {
                    try {
                        String contents = new String(Files.readAllBytes(file.toPath()));
                        JSONObject json = new JSONObject(contents);
                        int id = json.getInt("id");
                        String text = json.getString("text");
                        String author = json.getString("author");
                        wiseSayingList.add(new WiseSaying(id, text, author));
                    } catch (IOException | JSONException e) {
                        System.out.println("파일 로드 실패: " + e.getMessage());
                    }
                }
            }
        }
        return wiseSayingList;
    }

    public void saveToFile(WiseSaying wiseSaying) {
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


    public void build(List<WiseSaying> wiseSayingList) throws IOException {
        // Json
        String jsonPath=path+"/data.json";
        JSONArray jsonArray=new JSONArray();
        for(WiseSaying wisesaying:wiseSayingList){
            JSONObject json=new JSONObject();
            json.put("id",wisesaying.getId());
            json.put("text",wisesaying.getText());
            json.put("author",wisesaying.getAuthor());
            jsonArray.put(json);
        }

        //저장
        File jsonfile=new File(jsonPath);
        if(jsonfile.exists()){
            jsonfile.delete();
        }
        FileWriter writer=new FileWriter(jsonPath);
        writer.write(jsonArray.toString(4));
        writer.close();

    }
    public int loadLastId() throws IOException {
        File files=new File(path+"/lastId.txt");
        if(!files.exists()){
            saveLastId(lastId);
            return lastId;
        }
        try {
            String id=new String(Files.readAllBytes(files.toPath()));
            return Integer.parseInt(id);
        } catch (IOException e) {
            e.printStackTrace();
            return lastId;
        }
    }

    public List<WiseSaying> findByContent(String word) {
        wiseSayingList=findAll();
        List<WiseSaying> findList=new ArrayList<>();
        for(WiseSaying wiseSaying:wiseSayingList){
            if(wiseSaying.getText().contains(word)){    //word를 포함시키는 경우
                findList.add(wiseSaying);
            }
        }
        return findList;

    }

    public List<WiseSaying> findByAuthor(String word) {
        wiseSayingList=findAll();
        List<WiseSaying> findList=new ArrayList<>();
        for(WiseSaying wiseSaying:wiseSayingList){
            if(wiseSaying.getAuthor().contains(word)){
                findList.add(wiseSaying);
            }
        }
        return findList;
    }

    public List<WiseSaying> paging(List<WiseSaying> wiseSayingList, int start, int end) {
        List<WiseSaying> list=
                wiseSayingList.subList(start,end);
        return list;
    }
}
