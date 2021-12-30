package com.surizigy.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {

	//initializing Variables
	public static boolean saveExists = false;
	public static boolean saveGame = false;
	
	public static int bestScore;
	
	//initializing Loop
	public void tick() {
		//checking if Save File exists
		File file = new File("save.txt");
		if(file.exists()) {
			saveExists = true;
		}else {
			saveExists = false;
		}
	
	}
	
	//saving High Score
	public static void saveGame(String[] val1, int[] val2, int encode) {		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("save.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < val1.length; i++) {
			String current = val1[i];
			current+=":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			for(int n = 0; n < value.length; n++) {
				value[n]+=encode;
				current+=value[n];
				}
			try {
				writer.write(current);
				if(i < val1.length - 1)
					writer.newLine();
			}catch(IOException e){
				}
		}
			
		try {
			writer.flush();
			writer.close();
		}catch(IOException e) {
			}
			
	}	

	//loading High Score
	public static String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						String[] trans = singleLine.split(":");
						char[] val = trans[1].toCharArray();
						trans[1] = "";
						for(int i = 0; i < val.length; i++) {
							val[i]-=encode;
							trans[1]+=val[i];
							}
						line+=trans[0];
						line+=":";
						line+=trans[1];
						line+="/";
						}
				}catch(IOException e){
					}
				
			}catch(FileNotFoundException e) {
				}
		}
		return line;
	}
	
	//executing Save Game
	public static void applySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {				
				case "bestScore":
					bestScore = Integer.parseInt(spl2[1]);
					break;
			}
		}
	}
	
	//initializing Best Score
	public static int bestScore() {
		File file = new File("save.txt");
		if(file.exists()) {			
			String saver = loadGame(10);
			applySave(saver);	
		}
		return bestScore;
	}
	
	//setting New Best Score
	public static void setNewBest (int newBestScore) {
		bestScore = newBestScore;
		String[] opt1 = {"bestScore"}; 
		int[] opt2 = {(int)Game.score};
		HighScore.saveGame(opt1, opt2, 10);
		System.out.println("New Best Score!");		
	}
	
}
