package com.example.rul0070.ass2_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rul0070 on 12/06/2018.
 */

public class GameActivity extends AppCompatActivity {

    private TextView editText;
    private Game game;
    private GameView gameView;
    public String levelFile;
    private Level selectedLevel;
    private LevelDataBase levelDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_game);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        // intent for loading game from local file
        if(intent.hasExtra("level")){
            Object level = extras.get("level");
            int item = Integer.parseInt(String.valueOf(level));
            final String[] files = {"level1.txt", "level2.txt", "level3.txt"};
            levelFile = files[item];

//            this.setView();
            game = new Game();
            gameView= new GameView(this, game);
            gameView.setGameLevelFile(levelFile);
            gameView.setId(R.id.custom_view);
            editText = findViewById(R.id.move_count);
            gameView.setMoveId(editText);
        }
        else{
    //      intent for loading from DB
            levelDB = new LevelDataBase(this);
            int id = extras.getInt("levelId");
            String levelContent = extras.getString("levelContent");
            int moveNum = extras.getInt("move");
            String levelName = extras.getString("levelName");
            if(id!= 0) {
                selectedLevel = new Level(id, levelContent, 0);
            }
            else{
                selectedLevel = new Level(levelName, levelContent, moveNum);
            }

//            this.setView();
            game = new Game();
            gameView= new GameView(this, game);
            gameView.setGameLevelDB(levelContent);
            gameView.setId(R.id.custom_view);
            editText = findViewById(R.id.move_count);
            gameView.setMoveId(editText);
        }

    }

    public void setView(){
        game = new Game();
        gameView= new GameView(this, game);
        gameView.setId(R.id.custom_view);
        editText = findViewById(R.id.move_count);
        gameView.setMoveId(editText);
    }

    @Override
    protected void onStart(){
        super.onStart();
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.game_layout);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(gameView.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, 8);
        constraintSet.connect(gameView.getId(), ConstraintSet.END, layout.getId(), ConstraintSet.END, 8);
        constraintSet.constrainHeight(gameView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainWidth(gameView.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.applyTo(layout);
        layout.addView(gameView);
        editText.setText(Integer.toString(game.getMoveCount()));

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.up_btn:
                gameView.move("up");
                break;
            case R.id.down_btn:
                gameView.move("down");
                break;
            case R.id.right_btn:
                gameView.move("right");
                break;
            case R.id.left_btn:
                gameView.move("left");
                break;
            case R.id.p_btn:
                gameView.move("pause");
                break;
            case R.id.reset_btn:
                gameView.move("reset");
                break;
            case R.id.undo_btn:
                gameView.move("undo");
                break;


        }
    }




}
