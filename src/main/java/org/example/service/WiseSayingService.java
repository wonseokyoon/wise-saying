package org.example.service;

import org.example.entity.WiseSaying;
import org.example.repository.WiseSayingRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository=new WiseSayingRepository();
    private static final String path="db/wiseSaying";

    public List<WiseSaying> loadWiseSayings(){
        return wiseSayingRepository.loadWiseSayings();
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public int register(String text,String author) throws IOException {
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

    public void build() {
        List<WiseSaying> wiseSayingList=wiseSayingRepository.loadWiseSayings();
        wiseSayingRepository.build(wiseSayingList);
    }


    public static void saveLastId(int id) throws IOException {
        FileWriter writer=new FileWriter(path+"/lastId.txt");
        writer.write(String.valueOf(id));
        writer.close();
    }
    public void saveToFile(WiseSaying wiseSaying) {
        wiseSayingRepository.saveToFile(wiseSaying);
    }

}
