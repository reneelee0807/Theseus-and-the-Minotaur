package com.example.rul0070.ass2_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SelectLevelActivity extends AppCompatActivity {
    private LevelDataBase levelDB;
    private Level level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_levels);
        setTitle("Choose a Level");
        levelDB = new LevelDataBase(this);

    }

    public void onClick(View view){
        EditText levelNumber = findViewById(R.id.levelNumber);
        int levelNum = Integer.parseInt(levelNumber.getText().toString());
        if(levelNum >=1 && levelNum<=3) {
            this.getOriginalLevel(levelNum);
            Intent intentForDB = new Intent(this, GameActivity.class);
            intentForDB.putExtra("levelName", levelNum);
            intentForDB.putExtra("levelId", level.getLevelId());
            intentForDB.putExtra("levelContent", level.getLevelContent());
            startActivity(intentForDB);
        }

        else{
            Context context = getApplicationContext();
            CharSequence message = "Please enter a number between 1-3";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, message, duration).show();
        }
    }

    public void getOriginalLevel(int id){
        level = levelDB.getOriginalLevel(id);
    }
}
