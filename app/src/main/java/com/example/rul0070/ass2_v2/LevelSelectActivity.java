//package com.example.rul0070.ass2_v2;
//
//import android.content.Intent;
//import android.content.res.AssetManager;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
///**
// * Created by rul0070 on 12/06/2018.
// */
//
//public class LevelSelectActivity extends AppCompatActivity {
//
//    private ArrayList<String> levelList;
//    private String fileName;
//    private String path;
//    private ListView listView;
//    private ArrayAdapter adapter;
//    private Filer filer;
//    private TextView editText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_game);
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        Object level = extras.get("level");
//        int item = Integer.parseInt(String.valueOf(level));
//
//        filer = new Filer();
//        final String[] files = {"level1.txt", "level2.txt", "level3.txt"};
//
//        editText = findViewById(R.id.textView2);
//        String file = files[item];
//        editText.setText(filer.loadFile(this,file));
//
//    }
//
////        AssetManager assetManager = getAssets();
//////        levelList = new ArrayList<String>();
////        listView = (ListView) findViewById(R.id.level_list);
//////
//////        adapter = new ArrayAdapter<String>(this, R.layout.activity_select_level,R.id.level_list_item,levelList);
////
////
////        try{
////            String[] levels = assetManager.list("");
////            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_select_level,levels);
////            listView.setAdapter(adapter);
////
////
////
////        }catch (IOException e){
////            System.out.print("something wrong");
////        }
////        ArrayList<String> items = new ArrayList<String>();
////        AssetManager assetManager = getApplicationContext().getAssets();
////        try {
////            items.addAll(Arrays.asList(assetManager.list("")));
////            adapter = new ArrayAdapter<String>(this, R.layout.activity_select_level,R.id.level_list_item,items);
////        } catch (IOException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////        }
////        EditText levelNumber = findViewById(R.id.levelNumber);
////        int levelNum = Integer.parseInt(levelNumber.getText().toString());
////        if(levelNum >=1 && levelNum<=2) {
////            Intent intent = new Intent(this, GameActivity.class);
////            intent.putExtra("levelName", levelNum);
////            startActivity(intent);
////        }
//
////
////    public void onClick(View view){
////        Intent intent = new Intent();
////        //intent.putExtra("gameLevel", game.get_levelContent() );
////        switch(view.getId()){
////            case R.id.level_list_item:
////                intent = new Intent(this, GameActivity.class);
//////                intent.putExtra("game level", )
////                break;
////
////
////        }
////        startActivity(intent);
////
////
//////        EditText levelNumber = findViewById(R.id.levelNumber);
//////        int levelNum = Integer.parseInt(levelNumber.getText().toString());
//////        if(levelNum >=1 && levelNum<=2){
//////            Intent intent = new Intent(this,GameActivity.class);
//////            startActivity(intent);
//////        }
////    }
//
//}
