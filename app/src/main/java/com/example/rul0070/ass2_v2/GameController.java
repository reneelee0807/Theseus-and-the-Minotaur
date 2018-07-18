package com.example.rul0070.ass2_v2;

import android.content.Context;

import java.util.ArrayList;

public class GameController {
    private Game game = new Game();
    private GameView view;
//    private Filer Filer;
//    private String levelString;

    public GameController(GameView theView){
        view = theView;
    }

    public void setGameLevel(Context context, String fileName){
        game.load(context, fileName);
    }

    public void setDBLevel(Context context, String levelContent){
        game.readLevel(levelContent);
    }

    public int getFinalX(){
        return game.getFinalX();
    }

    public int getFinalY(){
        return game.getFinalY();
    }

    public int getTheseusX(){
        return game.getCurrentTheseusX();
    }

    public int getTheseusY(){
        return game.getCurrentTheseusY();
    }

    public int getMinotaurX(){
        return game.getCurrentMinotaurX();
    }

    public int getMinotaurY(){
        return game.getCurrentMinotaurY();
    }

    public  boolean[][] getHLines(){
        return game.getHorizontalLines();
    }

    public  boolean[][] getVLines(){
        return game.getVerticalLines();
    }

    public int getCellSizeX(){
        return game.getCellSizeX();
    }

    public int getCellSizeY(){
        return game.getCellSizeY();
    }

    public Boolean moveTheseus(Direction direction) {
        Boolean moved = game.moveTheseus(direction);
        this.getCount();
        return moved;
    }

    public void getCount(){
        int moveCount = game.getMoveCount();
        view.updateCount(moveCount);
    }


    public Boolean isWin(){
        return game.isGameFinish();
    }

    public Boolean isFail(){
        return game.isGameFail();
    }

    public Boolean pause(){
        return game.pause();
    }

    public boolean reset(){
        game.reset();
        this.getCount();
        return true;
    }

    public boolean undo(){
        game.undo();
        this.getCount();
        return true;
    }

}
