import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.*;

public class MusicPlayer extends JDialog {

	// Variables
	private Music music = new Music();						// Music Class
	
	private String genre = "";								// Music Genre
				
	private Boolean playing[] = new Boolean[16];			// Music Components
	private int list[] = new int[48];
	private int randomList[] = new int[16];
	private Random rand = new Random();						// for Random
	
	private ActionListener listener;						// Action Listener

	private MusicPlayer loopPanel;							// Panels
	private JFrame selectGenre;
	
	// Button Setting ( Color, Array )
	private JButton btnLogo;								// button
	private JButton buttons[] = new JButton[16];
	
	private Color defaultColor = new Color(218, 196, 255);	// Color
	private Color chordColor[] = {new Color(208, 60, 139), new Color(208, 60, 139), new Color(208, 60, 139), new Color(208, 60, 139),
			new Color(208, 60, 139), new Color(209, 91, 173), new Color(208, 60, 139), new Color(208, 60, 139), new Color(211, 80, 222),
			new Color(208, 60, 139), new Color(208, 60, 139), new Color(208, 60, 139), new Color(208, 60, 139), new Color(208, 60, 139),
			new Color(224, 139, 189), new Color(208, 60, 139)};
	private Color beatColor[] = {new Color(235, 83, 59), new Color(235, 83, 59), new Color(235, 83, 59), new Color(223, 140, 127),
			new Color(212, 62, 54), new Color(235, 83, 59), new Color(235, 83, 59), new Color(235, 83, 59), new Color(235, 83, 59),
			new Color(234, 51, 35), new Color(235, 83, 59), new Color(235, 83, 59), new Color(235, 83, 59), new Color(235, 83, 59),
			new Color(235, 83, 59), new Color(235, 83, 59)};
	private Color voiceColor[] = {new Color(49, 46, 219), new Color(49, 46, 219), new Color(49, 46, 219), new Color(49, 46, 219),
			new Color(49, 46, 219), new Color(47, 110, 186), new Color(49, 46, 219), new Color(49, 46, 219), new Color(49, 46, 219),
			new Color(49, 46, 219), new Color(49, 46, 219), new Color(49, 46, 219), new Color(21, 19, 139), new Color(49, 46, 219),
			new Color(49, 46, 219), new Color(79, 173, 234)};
	
	
	// Constructor
	public MusicPlayer(JFrame frame, JFrame selectGenre) {
		super(frame, true);					// Modal Dialog
		this.selectGenre = selectGenre;

		music.reset();						// Reset Music Class variables
		
		// Reset Lists, flag (playing), buttonList
		for (int i = 0; i < 16; i++ ) {
			playing[i] = false;
			buttons[i] = null;
			randomList[i] = 0;
		}
		
		for (int i = 0; i < 48; i++) {
			list[i] = i;
		}

		// Bring 16 num randomly from 48 numList
		int temp, index = -1;
		for (int i = 0; i < 16; i++) {
			temp = 50;
			while(temp == 50) {
				index = rand.nextInt(48); // 0~47
				temp = list[index];
			}
			list[index] = 50;
			randomList[i] = index;
		}
		
		music.setFile();					// Setting Files
		homeFrame();						// Dialog Setting
		setComponents();					// Setting Components
	}
	
	// Get Genre
	public void getGenre(String genre, MusicPlayer loopPanel) {
		this.genre = genre;
		this.loopPanel = loopPanel;
	}

	// Set Dialog
	public void homeFrame() {
		setTitle("Loop Station : 16 team");
		setSize(750, 500);
		setResizable(false);

		setLocationRelativeTo(null);

		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
	}
	
	// Set Components
	public void setComponents() {
		
		// Button Listener
		listener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int index = 0;
				JButton button = (JButton) arg0.getSource();
				
				// Logo button => back home
				if (button == btnLogo) {
					loopPanel.dispose();
					selectGenre.setVisible(true);
					return;
				}
				
				// find location of pressed Button
				for (int i = 0; i < 16; i++) {
					if (buttons[i] == button) {
						index = i;
						break;
					}
				}

				// Change State
				playing[index] = !playing[index];
				
				// Change Button Color depend on Genre
				// Start playing
				if(playing[index] == true) {
					if (genre == "Chord") {									// Chord
						buttons[index].setBackground(chordColor[index]);
					} else if (genre == "Beat") {							// Beat
						buttons[index].setBackground(beatColor[index]);
					} else if (genre == "Voice") {							// Voice
						buttons[index].setBackground(voiceColor[index]);
					} else if (genre == "Random") {							// Random
						
						int tempIndex = randomList[index];
						if (tempIndex < 16) {
							buttons[index].setBackground(chordColor[tempIndex]);
						} else if (15 < tempIndex && tempIndex < 32) {
							tempIndex = tempIndex - 16;
							buttons[index].setBackground(beatColor[tempIndex]);
						} else if (31 < tempIndex && tempIndex < 48) {
							tempIndex = tempIndex - 32;
							buttons[index].setBackground(voiceColor[tempIndex]);
						}
					}
				// Pause playing
				} else {
					buttons[index].setBackground(defaultColor);
				}
				
				// Set Index dependent on Genre
				int genreIndex = 0;
				if (genre == "Beat")					// Rock
					genreIndex = index + 16;
				else if (genre == "Voice")				// Ballad
					genreIndex = index + 32;
				else if (genre == "Random") 			// Random
					genreIndex = randomList[index];
				else
					genreIndex = index;
				
				// Playing / Pause
				music.play(playing[index], genreIndex);
			}		
		};
		
		// Set Logo Button
		btnLogo = new JButton(new ImageIcon("img/logo_s.png"));
		btnLogo.setBounds(27, 17, 179, 53);
		btnLogo.addActionListener(listener);
		add(btnLogo);
		
		// Set Music gif
		JButton musicgif = new JButton();
		musicgif.setBounds(260, 17, 214, 55);
		musicgif.setIcon(new ImageIcon("img/music.gif"));
		musicgif.setBorder(null);
		add(musicgif);
		
		// Button Setting (16)
		JButton btnNewButton_1 = new JButton("btn1");
		btnNewButton_1.setForeground(new Color(255,0,0,0));
		btnNewButton_1.setBounds(160, 100, 94, 64);		
		buttons[0] = btnNewButton_1;
		
		JButton btnNewButton_2 = new JButton("btn2");
		btnNewButton_2.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_2.setBounds(266, 100, 94, 64);
		buttons[1] = btnNewButton_2;
		
		JButton btnNewButton_3 = new JButton("btn3");
		btnNewButton_3.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_3.setBounds(372, 100, 94, 64);
		buttons[2] = btnNewButton_3;
		
		JButton btnNewButton_4 = new JButton("btn4");
		btnNewButton_4.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_4.setBounds(478, 100, 94, 64);
		buttons[3] = btnNewButton_4;
		
		JButton btnNewButton_5 = new JButton("btn5");
		btnNewButton_5.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_5.setBounds(160, 176, 94, 64);
		buttons[4] = btnNewButton_5;
		
		JButton btnNewButton_6 = new JButton("btn6");
		btnNewButton_6.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_6.setBounds(266, 176, 94, 64);
		buttons[5] = btnNewButton_6;
		
		JButton btnNewButton_7 = new JButton("btn7");
		btnNewButton_7.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_7.setBounds(372, 176, 94, 64);
		buttons[6] = btnNewButton_7;
		
		JButton btnNewButton_8 = new JButton("btn8");
		btnNewButton_8.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_8.setBounds(478, 176, 94, 64);
		buttons[7] = btnNewButton_8;
		
		JButton btnNewButton_9 = new JButton("btn9");
		btnNewButton_9.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_9.setBounds(160, 252, 94, 64);
		buttons[8] = btnNewButton_9;;
		
		JButton btnNewButton_10 = new JButton("btn10");
		btnNewButton_10.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_10.setBounds(266, 252, 94, 64);
		buttons[9] = btnNewButton_10;
		
		JButton btnNewButton_11 = new JButton("btn11");
		btnNewButton_11.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_11.setBounds(372, 252, 94, 64);
		buttons[10] = btnNewButton_11;
		
		JButton btnNewButton_12 = new JButton("btn12");
		btnNewButton_12.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_12.setBounds(478, 252, 94, 64);
		buttons[11] = btnNewButton_12;
		
		JButton btnNewButton_13 = new JButton("btn13");
		btnNewButton_13.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_13.setBounds(160, 328, 94, 64);
		buttons[12] = btnNewButton_13;
		
		JButton btnNewButton_14 = new JButton("btn14");
		btnNewButton_14.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_14.setBounds(266, 328, 94, 64);
		buttons[13] = btnNewButton_14;
		
		JButton btnNewButton_15 = new JButton("btn15");
		btnNewButton_15.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_15.setBounds(372, 328, 94, 64);
		buttons[14] = btnNewButton_15;
		
		JButton btnNewButton_16 = new JButton("btn16");
		btnNewButton_16.setForeground(new Color(255, 0, 0, 0));
		btnNewButton_16.setBounds(478, 328, 94, 64);
		buttons[15] = btnNewButton_16;
		
		// Add ActionListener on 16 button
		for (int i = 0; i < 16; i++ ) {
			buttons[i].addActionListener(listener);
			buttons[i].setBackground(defaultColor);
			add(buttons[i]);
		}
	}
}