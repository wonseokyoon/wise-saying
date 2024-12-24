package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class JsonBuild {
    private static final String path = "db/wiseSaying";

    public static void start() {
        try {
            JSONArray jsonArray=new JSONArray();
            File folder=new File(path);
            File[] files=folder.listFiles();
            File datajson=new File(path+"/data.json");
            if(datajson.exists()){
                datajson.delete();
            }
            if(files!=null){
                for (File file:files){
                    if(file.getName().endsWith(".json")&& !file.equals(datajson)){
                        String contents=new String(Files.readAllBytes(file.toPath()));
//                        System.out.println("파일 읽기: " + file.getName());
//                        System.out.println("내용: " + contents); // JSON 내용을 출력
                        JSONObject json=new JSONObject(contents);
                        jsonArray.put(json);
                    }
                }
            } else{
                System.out.println("명언을 등록하시오");
                return;
            }
            save(jsonArray);
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }

    public static void save(JSONArray jsonArray){
        try( FileWriter writer=new FileWriter(path+"/data.json")) {
            writer.write(jsonArray.toString(4));
            System.out.println("빌드 완료");
//            System.out.println(jsonArray.toString(4));
        }
        catch (IOException e){
            System.out.println("json 저장 오류"+e.getMessage());
        }
    }
}

