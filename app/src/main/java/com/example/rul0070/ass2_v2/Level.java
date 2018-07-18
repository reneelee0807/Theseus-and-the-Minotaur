package com.example.rul0070.ass2_v2;

public class Level {
    private int id;
    private int move;
    private String name;
    private String levelContent;

    public Level (int id, String levelContent){
        this.id = id;
        this.move = 0;
        this.name = "";
        this.levelContent = levelContent;

    }

    public Level (int id, String levelContent, int moveNum){
        this.id = id;
        this.move = moveNum;
        this.name = "";
        this.levelContent = levelContent;

    }

    public Level (String name, String levelContent, int moveNum){
        this.name = name;
        this.move = moveNum;
        this.name = "";
        this.levelContent = levelContent;

    }

    public void setId(int id){
        this.id = id;
    }

    public void setMove(int move){
        this.move = move;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLevelContent(String levelContent){
        this.levelContent = levelContent;
    }

    public int getLevelId(){
        return id;
    }

    public int getMove(){
        return move;
    }

    public String getName(){
        return name;
    }

    public String getLevelContent(){
        return levelContent;
    }








}
