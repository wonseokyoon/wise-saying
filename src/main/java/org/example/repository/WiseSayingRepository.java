package org.example.repository;

import org.example.entity.WiseSaying;

import java.util.List;

public interface WiseSayingRepository {
    List<WiseSaying> findAll(); // 리스트 조회
    public void register(String text,String author); //
    public void modify(int id,String text,String author);
    public void delete(int id);
    public void build();
    public void saveLastId(int id);

}
