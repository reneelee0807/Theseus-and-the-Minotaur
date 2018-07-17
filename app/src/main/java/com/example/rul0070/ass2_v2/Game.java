package com.example.rul0070.ass2_v2;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {


	private Filer filer;
	public boolean[][] verticalLines;
	public boolean[][] horizontalLines;
	private int mapWidth, mapHeight;         //stores the width and height of the maze
	private int currentTheseusX, currentTheseusY;   //stores the current location of the theseus
	private int currentMinotaurX, currentMinotaurY;   //stores the current location of the theseus
	private int finalX, finalY;       //stores the finishing of the maze
	private boolean finish;
	private boolean fail;
	public String levelString = "";
	private int moveCount = 0; 
//	public ArrayList<Direction> theseusMoves = new ArrayList<Direction>();
////	public ArrayList<Direction> minotaurMoves = new ArrayList<Direction>();
//	public int[][] theseusMoves;
////	public int[][] minotaurMoves;
//	public ArrayList<ArrayList<Integer>> theseusMoves = new ArrayList<ArrayList<Integer>>();
//	public ArrayList<ArrayList<Integer>> minotaurMoves = new ArrayList<ArrayList<Integer>>();
	public ArrayList<Integer> theseusX = new ArrayList<Integer>();
	public ArrayList<Integer> theseusY = new ArrayList<Integer>();
	public ArrayList<Integer>  minotaurX = new ArrayList<Integer>();
	public ArrayList<Integer>  minotaurY = new ArrayList<Integer>();


	protected char[][] vLinesChar;
	protected char[][] hLinesChar;
	private int originalTheseusX, originalTheseusY;   
	private int originalMinotaurX, originalMinotaurY;
	private int cellSizeX, cellSizeY;
	private int sizeX, sizeY;
	
	
	public Game(Filer filer) {
		this.filer = filer;
	}

	public Game() {

	}


	public Game getGame(String level){
		Game game = null;
		game = new Game();
		this.setVerticalLines(level);
		this.setHorizontalLines(level);
		this.SetCurrentTheseus(level);
		this.SetCurrentMinotaur(level);
		this.SetExit(level);
		return game;
	}
	

	
	public void load(Context context, String newLevel) {
		this.filer = new Filer();
		levelString = filer.loadFile(context, newLevel);

		this.setVerticalLines(levelString);
		this.setHorizontalLines(levelString);
		this.SetCurrentTheseus(levelString);
		this.SetCurrentMinotaur(levelString);
		this.SetExit(levelString);
	}
	
	public void setVerticalLines(String levelString){
		String[] lines = levelString.split(";");
		String vLine = lines[1];
		String[] vLines = vLine.split(",");
		verticalLines = new boolean[vLines.length][vLines[0].length()];
		vLinesChar = new char[vLines.length][vLines[0].length()];
		for(int i = 0; i < vLines.length; i++)  {
			for (int j = 0; j< vLines[i].length(); j++) {				
				char c = vLines[i].charAt(j);
				if(c == 'o') {
					verticalLines[i][j] = false;
				}
				else if(c == 'x') {
					verticalLines[i][j] = true;
					System.out.println("verticalline");
					System.out.println(i);
					System.out.println(j);				}
			}
			vLinesChar[i] = vLines[i].toCharArray();
		}
//		return verticalLines;
		sizeY = verticalLines.length;
	}

	public boolean[][] getVerticalLines(){
		return verticalLines;
	}
	
	public void setHorizontalLines(String levelString){
		String[] lines = levelString.split(";");
		String hLine = lines[0];
		String[] hLines = hLine.split(",");
		horizontalLines = new boolean[hLines.length][hLines[0].length()];
		hLinesChar = new char[hLines.length][hLines[0].length()];
		for(int i = 0; i < hLines.length; i++)  {
			for (int j = 0; j< hLines[i].length(); j++) {				
				char c = hLines[i].charAt(j);
				if(c == 'o') {
					horizontalLines[i][j] = false;
				}
				else if(c == 'x') {
					horizontalLines[i][j] = true;
					System.out.println("horizontalLines");
					System.out.println(i);
					System.out.println(j);
				}
			}
			hLinesChar[i] = hLines[i].toCharArray();
		}
//		return horizontalLines;
		sizeX = horizontalLines[0].length;
	}

	public boolean[][] getHorizontalLines(){
		return horizontalLines;
	}

	public int getCellSizeX(){
		cellSizeX = horizontalLines[0].length;
		return cellSizeX;
	}

	public int getCellSizeY(){
		cellSizeY = verticalLines.length;
		return cellSizeY;
	}
	
	public void SetCurrentTheseus(String levelString) {
		String[] lines = levelString.split(";");
		String tPosition = lines[2];
		String[] tPositions = tPosition.split(",");
		currentTheseusX = Integer.parseInt(tPositions[0]);
		currentTheseusY = Integer.parseInt(tPositions[1]);
		System.out.println(currentTheseusX);
		System.out.println(currentTheseusY);
		originalTheseusX = Integer.parseInt(tPositions[0]);
		originalTheseusY = Integer.parseInt(tPositions[1]);
	}
	
	public int getCurrentTheseusX() {
		return currentTheseusX;
	}
	
	public int getCurrentTheseusY() {
		return currentTheseusY;
	}
	
	public void SetCurrentMinotaur(String levelString) {
		String[] lines = levelString.split(";");
		String tPosition = lines[3];
		String[] tPositions = tPosition.split(",");
		currentMinotaurX = Integer.parseInt(tPositions[0]);
		currentMinotaurY = Integer.parseInt(tPositions[1]);
		originalMinotaurX = Integer.parseInt(tPositions[0]);
		originalMinotaurY = Integer.parseInt(tPositions[1]);
	}
	
	public int getCurrentMinotaurX() {
		return currentMinotaurX;
	}
	
	public int getCurrentMinotaurY() {
		return currentMinotaurY;
	}
	
	
	public int getFinalX() {
		System.out.println(finalX);
		return finalX;

	}

	
	public int getFinalY() {
		System.out.println(finalY);
		return finalY;
	}
	
	public void SetExit(String levelString) {
		String[] lines = levelString.split(";");
		String tPosition = lines[4];
		String[] tPositions = tPosition.split(",");
		finalX = Integer.parseInt(tPositions[0]);
		finalY = Integer.parseInt(tPositions[1]);
	}
	
	public int getMapWidth() {
		return mapWidth;
	}
	
	public int getMapHeight() {
		return mapHeight;
	}
	
	public void setTheseusStartPosition(int x, int y) {
		currentTheseusX = x;
		currentTheseusY = y;
	}
	
	public void setMinotaurStartPosition(int x, int y) {
		currentMinotaurX = x;
		currentMinotaurY = y;
	}
	
	public int getMoveCount() {
		return moveCount;
	}

	public void getTheseusPreviousLocation(){
		this.theseusX.add(currentTheseusX);
		this.theseusY.add(currentTheseusY);
	}

	public void getMinotaurPreviousLocation(){
		this.minotaurX.add(currentMinotaurX);
		this.minotaurY.add(currentMinotaurY);
	}

	public void removeLatestTheseusLocation(){
		this.theseusX.remove(theseusX.size()-1);
		this.theseusY.remove(theseusY.size()-1);
	}

	public void removeLatestMinotaurLocation(){
		this.minotaurX.remove(minotaurX.size()-1);
		this.minotaurY.remove(minotaurY.size()-1);
	}

	public void undoTheseus(){
		if(theseusX.size()-1 >= 0){
			currentTheseusX = theseusX.get(theseusX.size()-1);
			currentTheseusY = theseusY.get(theseusY.size()-1);
			this.removeLatestTheseusLocation();
		}
		else if(theseusX.size()-1 < 0){
			currentTheseusX = currentTheseusX;
			currentTheseusY = currentTheseusY;
		}
	}

	public void undoMinotaur(){
		if(minotaurX.size()-2 >= 0){
			currentMinotaurX = minotaurX.get(minotaurX.size()-2);
			currentMinotaurY = minotaurY.get(minotaurY.size()-2);
			this.removeLatestMinotaurLocation();
			this.removeLatestMinotaurLocation();
		}
		else if(minotaurX.size()-2 < 0){
			currentMinotaurX = currentMinotaurX;
			currentMinotaurY = currentMinotaurY;
		}
	}

	public void undo(){
		this.undoTheseus();
		this.undoMinotaur();
		moveCount++;
	}
	
	public boolean moveTheseus(Direction direction) {
		boolean moved = false;
		this.getTheseusPreviousLocation();
		if(direction == Direction.UP) {
			if(currentTheseusY != 0 && !horizontalLines[currentTheseusY-1][currentTheseusX]) {
				currentTheseusY--;
				moved = true;
			}
		}
		if(direction == Direction.DOWN) {
			if(currentTheseusY != sizeY-1 && !horizontalLines[currentTheseusY][currentTheseusX]) {
				currentTheseusY++;
				moved = true;
			}
		}
		if(direction == Direction.RIGHT) {
			if(currentTheseusX != sizeX-1 && !verticalLines[currentTheseusY][currentTheseusX]) {
				currentTheseusX++;
				moved = true;
			}
		}
		if(direction == Direction.LEFT) {
			if(currentTheseusX != 0 && !verticalLines[currentTheseusY][currentTheseusX-1]) {
				currentTheseusX--;
				moved = true;
			}
		}

		if(moved) {
			if(currentTheseusX == finalX && currentTheseusY == finalY) {
				finish = true;
			}
		}
		moveCount++;
		this.moveMinotaur();
		this.moveMinotaur();
		this.checkFail();
		return moved;

	}
	
	
	public void moveMinotaur() {
		Direction direction = null;
		this.getMinotaurPreviousLocation();
		if(currentTheseusX < currentMinotaurX){
			direction = Direction.LEFT;
		}
		else if(currentTheseusX > currentMinotaurX){
			direction = Direction.RIGHT;
		}
		else if(currentTheseusY > currentMinotaurY){
			direction = Direction.DOWN;
		}
		else if(currentTheseusY < currentMinotaurY){
			direction = Direction.UP;
		}
		else{
			direction = Direction.Delay;
		}

		if(direction == Direction.UP) {
			if(currentMinotaurY != 0 && !horizontalLines[currentMinotaurY-1][currentMinotaurX]) {
				currentMinotaurY--;

			}
		}
		if(direction == Direction.DOWN) {
			if(currentMinotaurY != sizeY-1 && !horizontalLines[currentMinotaurY][currentMinotaurX]) {
				currentMinotaurY++;
			}
		}
		if(direction == Direction.RIGHT) {
			if(currentMinotaurX != sizeX-1 && !verticalLines[currentMinotaurY][currentMinotaurX]) {
				currentMinotaurX++;
			}
		}
		if(direction == Direction.LEFT) {
			if(currentMinotaurX > 0 && !verticalLines[currentMinotaurY][currentMinotaurX-1]) {
				currentMinotaurX--;
			}
		}
		if(direction == Direction.Delay){
			currentMinotaurX = currentMinotaurX;
		}

	}

	public void checkFail(){
		if(currentTheseusX == currentMinotaurX && currentTheseusY == currentMinotaurY) {
			fail = true;
		}
	}
	
	public boolean isGameFinish() {
		return finish;
	}

	public boolean isGameFail() {
		return fail;
	}
	
	public void save(String fileName) throws Exception {
		this.filer = new Filer();
		String[] TPositions = new String[2];
		TPositions[0] = Integer.toString(this.currentTheseusX);
		TPositions[1] = Integer.toString(this.currentTheseusY);
		String[] MPositions = new String[2];
		MPositions[0] = Integer.toString(this.currentMinotaurX);
		MPositions[1] = Integer.toString(this.currentMinotaurY);
		String[] FPositions = new String[2];
		FPositions[0] = Integer.toString(this.finalX);
		FPositions[1] = Integer.toString(this.finalY);
		filer.save(this.vLinesChar,this.hLinesChar, TPositions,MPositions,FPositions, fileName);
	}

	public void reset(){
		this.moveCount = 0;
		currentMinotaurX = originalMinotaurX ;
		currentMinotaurY = originalMinotaurY;
		currentTheseusX = originalTheseusX;
		currentTheseusY = originalTheseusY;
	}

	public boolean pause(){
		this.moveMinotaur();
		return true;
	}



}
