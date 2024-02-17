package main;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	
	@SuppressWarnings("deprecation")
	public Sound() {
		try {
			soundURL[0] = new File("res/sound/BlueBoyAdventure.wav").toURL();
			soundURL[1] = new File("res/sound/coin.wav").toURL();
			soundURL[2] = new File("res/sound/powerup.wav").toURL();
			soundURL[3] = new File("res/sound/unlock.wav").toURL();
			soundURL[4] = new File("res/sound/fanfare.wav").toURL();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch(Exception e) {
			
		}
	}
	public void play() {
		
		clip.start();
	}
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		
		clip.stop();
	}

}
