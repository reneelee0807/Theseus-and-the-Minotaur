package com.example.rul0070.ass2_v2;

import android.content.Context;

import java.util.ArrayList;

public class GameController {
    private Game game = new Game();
    private GameView view;
    private Filer Filer;
    private String levelString;
//    private ArrayList<MapItem> Map;

    public GameController(GameView theView){

        view = theView;
    }

//    public Game getGame(String level){
//       return  game.getGame(level);
//    }
//
//    public void MakeMap(String level){
////        Game.load(levelString);
////        View.onDraw();
//    }

    public void setGameLevel(Context context, String fileName){
        game.load(context, fileName);
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

//    public Boolean moveUp(Direction direction){
////        return game.moveTheseus(Direction.UP);
////    }
////
////    public Boolean moveDown(Direction direction){
////        return game.moveTheseus(Direction.DOWN);
////    }
////
////    public Boolean moveRight(Direction direction){
////        return game.moveTheseus(Direction.RIGHT);
////    }
////
////    public Boolean moveLeft(Direction direction){
////        return game.moveTheseus(Direction.LEFT);
////    }

    public Boolean moveTheseus(Direction direction) {
        Boolean moved = game.moveTheseus(direction);
        int moveCount = game.getMoveCount();
//        view.updateCount(moveCount);
        return moved;
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

//    public void moveUp(){
//        game.moveTheseus(Direction.UP);
//    }
//
//    public void moveDown(){
//        game.moveTheseus(Direction.DOWN);
//    }
//
//    public void moveRight(){
//        game.moveTheseus(Direction.RIGHT);
//    }
//
//    public void moveLeft(){
//        game.moveTheseus(Direction.LEFT);
//    }


}
