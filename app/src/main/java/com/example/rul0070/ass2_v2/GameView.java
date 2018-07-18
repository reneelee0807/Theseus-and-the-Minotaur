package com.example.rul0070.ass2_v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by rul0070 on 3/06/2018.
 */

public class GameView extends View {

    private int mapWidth, mapHeight, lineWidth; 	//width and height of the whole maze and width of lines which
                                                //make the walls
    private int cellSizeX, cellSizeY;       //size of the maze i.e. number of cells in it
    float cellWidth, cellHeight;    //width and height of cells in the map
    float totalCellWidth, totalCellHeight;     //store result of cellWidth+lineWidth
                                                //and cellHeight+lineWidth respectively
    private int mapFinishX, mapFinishY;     //the exist point of the map
    private Canvas mazeCanvas;
    private GameController gameController = new GameController(this);
    private Activity context;
    private Paint line, theseus, minotaur, background, exit;
    private int intCellWidth, intCellHeight, currentTheseusX, currentTheseusY, currentMinotaurX,currentMinotaurY;
    private boolean[][] hLines, vLines;
    private TextView moveCountId;


    public GameView(Context context, Game game) {
        super(context);
        this.context = (Activity)context;
        line = new Paint();
        line.setColor(getResources().getColor(R.color.line));
        theseus = new Paint();
        minotaur = new Paint();
        background = new Paint();
        exit = new Paint();
        background.setColor(getResources().getColor(R.color.game_bg));
        setFocusable(true);
        this.setFocusableInTouchMode(true);
    }


    public void setGameLevelFile(String fileName ){
        gameController.setGameLevel(context, fileName);
        this.getLevel();
    }

    public void getLevel(){
        cellSizeX = gameController.getCellSizeX();
        cellSizeY = gameController.getCellSizeY();
        hLines = gameController.getHLines();
        vLines = gameController.getVLines();
        mapFinishX = gameController.getFinalX();
        mapFinishY = gameController.getFinalY();
    }

    public void setGameLevelDB(String levelContent){
        gameController.setDBLevel(context,levelContent );
        this.getLevel();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mapWidth = (w < h)?w:h;
        mapHeight = mapWidth;         //for now square mazes
        lineWidth = 1;          //for now 1 pixel wide walls
        cellWidth = (mapWidth - ((float)cellSizeX*lineWidth)) / cellSizeX;
        totalCellWidth = cellWidth+lineWidth;
        cellHeight = (mapHeight - ((float)cellSizeY*lineWidth)) / cellSizeY;
        totalCellHeight = cellHeight+lineWidth;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void setCellInt(){
        intCellWidth = Math.round(cellWidth*1f);
        intCellHeight = Math.round(cellHeight*0.8f);
    }

    protected void setWalls(){
        for (int i = 0; i < cellSizeX; i++) {
            for (int j = 0; j < cellSizeY; j++) {
                float x = j * totalCellWidth;
                float y = i * totalCellHeight;
                if (j < cellSizeX - 1 && vLines[i][j]) {
                    //we'll draw a vertical line
                    mazeCanvas.drawLine(x + cellWidth,   //start X
                            y,               //start Y
                            x + cellWidth,   //stop X
                            y + cellHeight,  //stop Y
                            line);
                }
                if (i < cellSizeY - 1 && hLines[i][j]) {
                    //we'll draw a horizontal line
                    mazeCanvas.drawLine(x,               //startX
                            y + cellHeight,  //startY
                            x + cellWidth,   //stopX
                            y + cellHeight,  //stopY
                            line);
                }
            }
        }
    }

    protected void setExit(){
        //draw the Exit point
        Bitmap f = BitmapFactory.decodeResource(getResources(), R.drawable.exit);
        Bitmap resizeF = Bitmap.createScaledBitmap(f, intCellWidth, intCellHeight, true);
        mazeCanvas.drawBitmap(resizeF, (mapFinishX * totalCellWidth) + (cellWidth / 8), mapFinishY * totalCellHeight, exit);
    }

    protected void setTheseus(){
        //draw the theseus
        Bitmap t = BitmapFactory.decodeResource(getResources(), R.drawable.theseus);
        Bitmap resizeT = Bitmap.createScaledBitmap(t, intCellWidth, intCellHeight, true);
        mazeCanvas.drawBitmap(resizeT, (currentTheseusX * totalCellWidth) + (cellWidth / 8), currentTheseusY * totalCellHeight, theseus);

    }

    protected void setMinotaur(){
        //draw the minotaur
        Bitmap m = BitmapFactory.decodeResource(getResources(), R.drawable.minotaur);
        Bitmap resizeM = Bitmap.createScaledBitmap(m, intCellWidth, intCellHeight, true);
        mazeCanvas.drawBitmap(resizeM, (currentMinotaurX * totalCellWidth) + (cellWidth / 8), currentMinotaurY * totalCellHeight, minotaur);

    }

    protected void onDraw(Canvas canvas) {
        mazeCanvas = canvas;
        //fill in the background
        mazeCanvas.drawRect(0, 0, mapWidth, mapHeight, background);
        currentTheseusX = gameController.getTheseusX();
        currentTheseusY = gameController.getTheseusY();
        currentMinotaurX = gameController.getMinotaurX();
        currentMinotaurY = gameController.getMinotaurY();
        this.setCellInt();
        this.setWalls();
        this.setExit();
        this.setMinotaur();
        this.setTheseus();

    }

    public void buildFinishDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_game_finish, null);
        builder.setView(view);
        View closeButton =view.findViewById(R.id.closeGame);
        View playButton = view.findViewById(R.id.startGame);
        closeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.closeGame) {
                    context.finish();
                }
            }
        });
        playButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.startGame) {
                    Intent intent=new Intent(view.getContext(),MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        AlertDialog finishDialog = builder.create();
        finishDialog.show();
    }

    public boolean move(String direction) {
        boolean moved = false;
        switch(direction) {
            case "up":
                moved = gameController.moveTheseus(Direction.UP);
                break;
            case "down":
                moved = gameController.moveTheseus(Direction.DOWN);
                break;
            case "left":
                moved = gameController.moveTheseus(Direction.LEFT);
                System.out.println(moved);
                break;
            case "right":
               moved = gameController.moveTheseus(Direction.RIGHT);
                System.out.println(moved);
                break;
            case "pause":
                moved = gameController.pause();
                break;
            case "reset":
                moved = gameController.reset();
                break;
            case "undo":
                moved = gameController.undo();
                break;

        }
        if(moved) {
            invalidate();
            if(gameController.isWin()) {
                this.playWin();
                this.buildFinishDialog(context.getText(R.string.win_title).toString());
            }
            else if (gameController.isFail()) {
                this.playFail();
                this.buildFinishDialog(context.getText(R.string.fail_title).toString());
            }
        }
        return true;
    }
    public void playWin() {

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.you_win_sound_effect);
        mediaPlayer.start();
    }

    public void playFail() {

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.applus);
        mediaPlayer.start();
    }

    public void setMoveId(TextView id){
        moveCountId = id;
    }

    public void updateCount(int count){
        String countString = Integer.toString(count);
        moveCountId.setText(countString);
    }

}
