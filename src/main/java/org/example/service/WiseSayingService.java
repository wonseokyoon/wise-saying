package org.example.service;

import org.example.entity.WiseSaying;
import org.example.repository.WiseSayingRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingService implements WiseSayingRepository{
    WiseSayingRepository wiseSayingRepository=new WiseSayingRepository();
    private static final String path="db/wiseSaying";

    public void loadWiseSayings(){
        sayings.clear();
        File folder=new File(path);
        File[] files=folder.listFiles();

        if(files!=null){
            for(File file:files){
                if(file.isFile() && file.getName().endsWith(".json")
                        && !file.getName().equals("data.json")){
                    try {
                        String contents=new String(Files.readAllBytes(file.toPath()));
                        JSONObject json=new JSONObject(contents);

                        int id= json.getInt("id");
                        String text= json.getString("text");
                        String author= json.getString("author");

                        sayings.add(new WiseSaying(id,text,author));
                        lastId=Math.max(lastId,id+1);
                    }catch (IOException e){
                        System.out.println("파일 로드 실패"+e.getMessage());
                    }catch (JSONException e){
                        System.out.println("Json 오류"+e.getMessage());
                    }
                }
            }
        }
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    @Override
    public int register(String text,String author) {
        return wiseSayingRepository.register(text, author);
    }


    public void modify(int id,String text,String author) {
        WiseSaying wiseSaying=wiseSayingRepository.findById(id);
        wiseSayingRepository.modify(wiseSaying,text,author);
        saveToFile(wiseSaying);
    }

    public void delete(int id) {
        WiseSaying wiseSaying=wiseSayingRepository.findById(id);
        wiseSayingRepository.delete(wiseSaying);
    }

    @Override
    public void build() {
        loadWiseSayings();
        String jsonPath=path+"/data.json";
        JSONArray jsonArray=new JSONArray();
        sayings.sort((s1,s2)->Integer.compare(s1.getId(), s2.getId()));
        for(WiseSaying saying:sayings){
            JSONObject json=new JSONObject();
            json.put("id",saying.getId());
            json.put("text",saying.getText());
            json.put("author",saying.getAuthor());
            jsonArray.put(json);
        }
        File jsonfile=new File(jsonPath);
        if(jsonfile.exists()){
            jsonfile.delete();
        }
        try(FileWriter writer=new FileWriter(jsonPath)){
            writer.write(jsonArray.toString(4));
        }catch (IOException e){
            System.out.println("Json 빌드 오류: "+e.getMessage());
        }
    }
    @Override
    public void saveLastId(int id){
        try{
            FileWriter writer=new FileWriter(path+"/lastId.txt");
            writer.write(String.valueOf(id));
            writer.close();
        }catch (IOException e){
            System.out.println("ID 저장 오류: "+e.getMessage());
        }
    }
    public static void saveToFile(WiseSaying wiseSaying) {
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
