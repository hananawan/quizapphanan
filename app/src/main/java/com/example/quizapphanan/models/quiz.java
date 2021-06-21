package com.example.quizapphanan.models;


import java.util.HashMap;
import java.util.Map;

public class quiz {
     String id="";
     String title ="";
     Map<String,questions> questions = new HashMap<>();

    public quiz(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String toString(){
        return "Quiz{" +
                "id=' " + id + '\'' +
                ", title=' " + title + '\'' +
                ", questions=' " + questions +
                '}';
    }*/
}
