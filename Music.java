import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;

public class Music {
	
	// Music Title
	private String musics[] = {"/chord/acousticguitar.wav", "/chord/AnyConv.com__a.wav", "/chord/AnyConv.com__b.wav", "/chord/AnyConv.com__c.wav",
							   "/chord/AnyConv.com__Deliverance.wav", "/chord/AnyConv.com__e.wav", "/chord/AnyConv.com__EK-07 - Now.wav", "/chord/AnyConv.com__PIANO DUBSTEP.wav",
							   "/chord/AnyConv.com__The stars in the night are like tears.wav", "/chord/AnyConv.com_d.wav", "/chord/piano+cello.wav", "/chord/piano1.wav",
							   "/chord/piano2.wav", "/chord/piano-cello.wav", "/chord/sadpiano.wav", "/chord/windpiano.wav",
							   "/beat/8bit_hardcorebeat.wav", "/beat/8bit_robotbeat.wav", "/beat/bassbeat.wav", "/beat/beat.wav",
							   "/beat/beat1.wav", "/beat/beat1_2.wav", "/beat/beat2.wav", "/beat/beatbox2.wav",
							   "/beat/beatdrum.wav", "/beat/beatdrum2.wav", "/beat/drumbeat.wav", "/beat/drumbeat_2.wav",
							   "/beat/drumbeat2.wav", "/beat/drumbeat3.wav", "/beat/machinebeat.wav", "/beat/ticktockbeat.wav",
							   "/voice/AnyConv.com__basevoice.wav", "/voice/AnyConv.com__beatbox.wav", "/voice/AnyConv.com__beatbox1.wav", "/voice/AnyConv.com__kid.wav",
							   "/voice/AnyConv.com__lol.wav", "/voice/AnyConv.com__lololo.wav", "/voice/AnyConv.com__malevoice.wav", "/voice/AnyConv.com__mockchoir.wav",
							   "/voice/AnyConv.com__oohah.wav", "/voice/AnyConv.com__singingmal.wav", "/voice/AnyConv.com__songvoid.wav", "/voice/AnyConv.com__voice4.wav",
							   "/voice/AnyConv.com__womanvoice.wav", "/voice/basevoice.wav", "/voice/beatbox.wav", "/voice/canon.wav"
								};
	private File files[] = new File[48];							
	private AudioInputStream streams[] = new AudioInputStream[48];	
	private Clip clips[] = new Clip[48];							
	
	// Constructor
	public Music() {
	}
	
	// Reset variables
	public void reset() {
		for (int i = 0; i < 16; i++ ) {
			
			files[i] = null;
			files[16 + i] = null;
			files[32 + i] = null;
			
			streams[i] = null;
			streams[16 + i] = null;
			streams[32 + i] = null;
			
			clips[i] = null;
			clips[16 + i] = null;
			clips[32 + i] = null;
		}
	}
	
	// set Files
	public void setFile() {
		for (int i = 0; i < 48; i++) {
			try {
				files[i] = new File("sound/" + musics[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Play / Pause Music
	public void play(Boolean playing, int index) {
		
		// Play Music
		if (playing) {
			try {
				streams[index] = AudioSystem.getAudioInputStream(files[index]);
				clips[index] = AudioSystem.getClip();
				clips[index].open(streams[index]);	
				clips[index].start();
				clips[index].loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// Pause Music
		} else {
			// Pause Music
			clips[index].stop();
		}
	}

	// Getter / Setter
	public String[] getMusics() {
		return musics;
	}

	public void setMusics(String[] musics) {
		this.musics = musics;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public AudioInputStream[] getStreams() {
		return streams;
	}

	public void setStreams(AudioInputStream[] streams) {
		this.streams = streams;
	}

	public Clip[] getClips() {
		return clips;
	}

	public void setClips(Clip[] clips) {
		this.clips = clips;
	}	
}