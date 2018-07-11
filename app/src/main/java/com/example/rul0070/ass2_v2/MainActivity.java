package com.example.rul0070.ass2_v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
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
        }
    }
}
