package com.example.rul0070.ass2_v2;

import android.content.Context;

/**
 * Created by rul0070 on 13/06/2018.
 */

public class GameCreator {

    public static Game getGame( String level){
//    public static Game getGame(int level){
        Game game = null;
        game = new Game();
//        game.load(context,level);
            game.setVerticalLines(level);
            game.setHorizontalLines(level);
            game.SetCurrentTheseus(level);
            game.SetCurrentMinotaur(level);
            game.SetExit(level);
            game.getMoveCount();


        return game;


    }
}
