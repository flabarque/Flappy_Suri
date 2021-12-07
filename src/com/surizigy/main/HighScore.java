package com.surizigy.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {

	public static boolean saveExists = false;
	public static boolean saveGame = false;
	
	public void tick() {
		File file = new File("save.txt");
		if(file.exists()) {
			saveExists = true;
		}else {
			saveExists = false;
		}
	
	}
	
	public static void saveGame(String[] val1, int[] val2, int encode) {
		//salvar com sistema de criptografia
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("save.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < val1.length; i++) {
			String current = val1[i];
			current+=":";
			char[] value = Integer.toString(val2[i]).toCharArray(); //criptografia - nao se pode manipular o save
			for(int n = 0; n < value.length; n++) {
				value[n]+=encode; //salvando é possitivo
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

	public static String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null; //comeca com linha vacia
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine = reader.readLine()) != null) { //le linha por linha, se está vazio para != null
						String[] trans = singleLine.split(":"); //: para selecionar o level guardado, ex., level:3
						char[] val = trans[1].toCharArray();
						trans[1] = "";
						for(int i = 0; i < val.length; i++) {
							val[i]-=encode; //resgatando é negativo
							trans[1]+=val[i];
							}
						line+=trans[0];
						line+=":";
						line+=trans[1];
						line+="/"; //para poder ler de fato o arquivo save
						}
				}catch(IOException e){
					}
				
			}catch(FileNotFoundException e) {
				}
		}
		return line;
	}
	
}
