package com.example.rul0070.ass2_v2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class  Filer extends AppCompatActivity {
	
	public Filer() {
		
	}
	
	public String loadFile(Context context, String fileName){
		String content = "";
		StringBuilder fileContent = new StringBuilder();
		try {
			InputStream inputStream = context.getAssets().open(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
//				content += line;
				fileContent.append(line);
			}
			}

		catch (IOException e) {
		e.printStackTrace();
		}
	return fileContent.toString();
//		return content;
	}

	public void save(char[][] vLinesChar, char[][] hLinesChar, String[] tPositions, String[] mPositions,
                     String[] fPositions, String fileName) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < vLinesChar.length; i++)//for each row
		{
		   for(int j = 0; j < vLinesChar[0].length; j++)//for each column
		   {
		      builder.append(vLinesChar[i][j]+"");//append to the output string
		   }
		   if(i != vLinesChar.length-1) {
			   builder.append(",");
		   }
		}
		builder.append(";");
		for(int i = 0; i < hLinesChar.length; i++)//for each row
		{
		   for(int j = 0; j < hLinesChar[0].length; j++)//for each column
		   {
		      builder.append(hLinesChar[i][j]+"");//append to the output string
		   }
		   if(i != hLinesChar.length-1) {
			   builder.append(",");
		   }
		}
		builder.append(";");
		for(int i = 0; i < tPositions.length; i++)//for each row
		{
		   builder.append(tPositions[i]+"");//append to the output string

		   if(i != tPositions.length-1) {
			   builder.append(",");
		   }
		}
		builder.append(";");
		for(int i = 0; i < mPositions.length; i++)//for each row
		{
		   builder.append(mPositions[i]+"");//append to the output string

		   if(i != mPositions.length-1) {
			   builder.append(",");
		   }
		}
		builder.append(";");
		for(int i = 0; i < fPositions.length; i++)//for each row
		{
		   builder.append(fPositions[i]+"");//append to the output string

		   if(i != fPositions.length-1) {
			   builder.append(",");
		   }
		}
		builder.append(";");
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(builder.toString());//save the string representation of the board
		writer.close();
	}

}
