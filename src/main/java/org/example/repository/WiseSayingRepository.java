package org.example.repository;

import org.example.entity.WiseSaying;

import java.util.List;

public interface WiseSayingRepository {
    List<WiseSaying> findAll();
    public int register(String text,String author);
    public void modify(int id,String text,String author);
    public void delete(int id);
    public void build();
    public void saveLastId(int id);

}
