package org.example.service;

import org.example.entity.WiseSaying;
import org.example.repository.WiseSayingRepository;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WiseSayingService {
    static WiseSayingRepository wiseSayingRepository=new WiseSayingRepository();
    private static final String path="db/wiseSaying";

    public List<WiseSaying> loadWiseSayings(){
        return wiseSayingRepository.loadWiseSayings();
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public int register(String text,String author) throws IOException {
        loadWiseSayings();
        return wiseSayingRepository.register(text, author);
    }

    public boolean delete(int id) {
        WiseSaying wiseSaying=wiseSayingRepository.findById(id);
        if(wiseSaying==null) return false;
        return wiseSayingRepository.delete(wiseSaying);
    }

    public void modify(int id,String text,String author) {
        WiseSaying wiseSaying=wiseSayingRepository.findById(id);
        wiseSayingRepository.modify(wiseSaying,text,author);
        saveToFile(wiseSaying);
    }

    public void build(List<WiseSaying> wiseSayingList) throws IOException {
        if(wiseSayingList==null || wiseSayingList.isEmpty()){
            throw new IOException("명언 목록이 비어있음");
        }
        wiseSayingRepository.build(wiseSayingList);
    }

    public static void saveLastId(int id) throws IOException {
        wiseSayingRepository.saveLastId(id);
    }
    public void saveToFile(WiseSaying wiseSaying) {
        wiseSayingRepository.saveToFile(wiseSaying);
    }

    public List<WiseSaying> search(String type, String word) {
        if(type.equals("content")){    // 타입이 content
            return wiseSayingRepository.findByContent(word);
        }else if(type.equals("author")){   //타입이 author
            return wiseSayingRepository.findByAuthor(word);
        }else {
            return null;
        }
    }

    public List<WiseSaying> reverOrder(List<WiseSaying> wiseSayingList) {
        Collections.sort(wiseSayingList, Comparator.comparingInt(WiseSaying::getId).reversed());
        return wiseSayingList;
    }

    public List<WiseSaying> paging(List<WiseSaying> wiseSayingList, int pageNum) {
        int pageSize=5;
        int start=(pageNum-1)*pageSize;
        int end=Math.min(start+pageSize
        ,wiseSayingList.size());

        //1. pageNum=0 or null
        if(pageNum<=0){
            return paging(wiseSayingList,1);
        }
        else if(start>wiseSayingList.size()){
            return Collections.emptyList();
        }
        else{
            return wiseSayingRepository.paging(wiseSayingList,start,end);
        }


    }
}
