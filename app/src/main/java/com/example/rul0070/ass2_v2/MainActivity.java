package com.example.rul0070.ass2_v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
    private LevelDataBase levelDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Insert:", "Inserting..."); // to log debug messages
        levelDB = new LevelDataBase(this);
        levelDB.addOriginalLevel(new Level(1,"xxxo,oxox,oxox,xxxo;ooooo,ooxoo,oxooo,ooxoo;1,1;1,3;3,2"));
        levelDB.addOriginalLevel(new Level(2,"oxxxo,xoxoo,xoxoo,oxxxo,ooooo;ooooo,xooxo,oxoxo,xooxo,ooooo;2,3;2,1;0,2"));
        levelDB.addOriginalLevel(new Level(3,"xxxxxxxx,xxooooox,xxoxxoox,oooxooox,xxoxxoox,xxooooox,xxooooox,xxooooox,xxxxxxxx;ooooo,xooxo,oxoxo,xooxo,ooooo;4,7;2,1;0,3"));

    }

    public void onClick(View view){
//        Intent intent = new Intent();
        switch(view.getId()){
            case R.id.startBtn:
                String[] levels = {"level 1", "level 2" , "level 3"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Level");
                builder.setItems(levels, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int item) {
                                Intent intent = new Intent(MainActivity.this, GameActivity.class);  //create an Intent to launch the Game Activity
                                int i  = item;
                                intent.putExtra("level", i);
                                startActivity(intent);
                            }

            });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.load_db_btn:
                Intent intent = new Intent(MainActivity.this, SelectLevelActivity.class );
                startActivity(intent);
                break;
        }

    }
}
