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
    private ArrayList<String> levelList;
    private String fileName;
    private String file;
    private ListView listView;
    private ArrayAdapter adapter;
    private Filer filer;
    private TextView editText;
    private Game game;
    private GameCreator gameCreator;
    protected String levelGameString;
    private GameView gameView;
    private GameController gameControl = new GameController(gameView);
    public String levelFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_game);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Object level = extras.get("level");
        int item = Integer.parseInt(String.valueOf(level));
        final String[] files = {"level1.txt", "level2.txt", "level3.txt"};
        filer = new Filer();
        levelFile = files[item];
        game = new Game();
        gameView= new GameView(this, game);
        gameView.setId(R.id.custom_view);
        gameView.setGameLevelFile(levelFile);
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

//    public String getLevelGameString()
//    {
//        return this.levelGameString;
//    }

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
