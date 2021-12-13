package com.surizigy.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

	public static class Clips{ //uma clase dentro da outra com acceso
		public Clip[] clips;
		private int p;
		private int count;
		public double vol;
		
		public Clips(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException { //byte é uma medida menor do que int
			if(buffer == null)
				return;
			
			clips = new Clip[count];
			this.count = count;
			
			for(int i = 0; i < count; i++) {
				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
				}
		}
	
		public void play(double vol) {
			if(clips == null) 
				return;
			setVol(vol, clips[p]);
			clips[p].stop();
			clips[p].setFramePosition(0); //começar do inicio
			clips[p].start();
			p++;
			if(p >= count)
				p = 0; //terminou o son, volve a começar
			}
		
		public void loop(double vol) {
			if(clips == null)
				return;
			setVol(vol, clips[p]);
			clips[p].stop();
			clips[p].setFramePosition(0);
			clips[p].loop(300); //roda infinitamente			
			}
		
		public void stop(double vol) {
			if(clips == null)
				return;
			setVol(vol, clips[p]);
			clips[p].stop();
		}
		
	}

	private static void setVol (double vol, Clip clips) {
		FloatControl gain = (FloatControl)clips.getControl(FloatControl.Type.MASTER_GAIN);;
		float dB = (float) (Math.log(vol) / Math.log(10) * 20);
		gain.setValue(dB);
		
	}
	
	private static Clips load(String name, int count) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(Sounds.class.getResourceAsStream(name));
		
			byte[] buffer = new byte[1024];//almacenar os valores de son
			int read = 0;
			while((read = dis.read(buffer)) >= 0) { //ainda tenho valores para colocar no meu Array
				baos.write(buffer, 0, read);
			}
			dis.close();
			byte[] data = baos.toByteArray();
			return new Clips(data, count); //pega os dados da clase anterior
			
		}catch(Exception e) {
			try {
				return new Clips (null, 0);
			}catch(Exception e2) {
				return null;
				}
			}
	}

	public static Clips musicBackground = load("/Background_54.wav", 1);
	public static Clips flyingSuri = load("/Helicopter_Suri_10.wav", 3);
	public static Clips points = load("/Pickup_Coin_01.wav", 1);
	public static Clips tubeHit = load("/Kitten_Mew_01.wav", 2);
	public static Clips medal = load("/Medal_01.wav", 4);
	
}
