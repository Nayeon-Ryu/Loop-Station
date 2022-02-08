import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class Select_Genre extends JFrame {
	
	// Action Listener
	ActionListener aListener;
	
	JFrame frame = new JFrame();			// Dialog Frame
	JPanel selectGenrePanel = new JPanel();	// Select Genre Panel
	JPanel logoPanel = new JPanel() {public void paintComponent(Graphics g){	// Logo Panel
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("img/logo.png");
			g.drawImage(image.getImage(), 0, 0, d.width,d.height,null);
		}
	};
	
	// Genre Button - Pop/ Beat/ Voice/ Random
	JButton chordBtn = new JButton("CHORD", new ImageIcon("img/chord.png"));
	JButton beatBtn = new JButton("BEAT", new ImageIcon("img/beat.png"));
	JButton voiceBtn = new JButton("VOICE", new ImageIcon("img/voice.png"));
	JButton randomBtn = new JButton("RANDOM", new ImageIcon("img/random.png"));
	JLabel lblChord = new JLabel("Chord");
	JLabel lblBeat = new JLabel("Beat");
	JLabel lblVoice = new JLabel("Voice");
	JLabel lblRandom = new JLabel("Random");
	
	// Constructor
	public Select_Genre() {
		homeframe();
		setComponents();
	}

	// Set Main Frame
	public void homeframe() {
		setTitle("Loop Station : 16 team");
		setSize(750, 500);
		setResizable(false);
		
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		setVisible(true);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

	// Set Components
	public void setComponents() {
		// Button LIstener
		aListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				switching(button);			// Switch
			}
		};
	
		// Select Genre Panel
		selectGenrePanel.setBounds(0, 0, 750, 500);
		selectGenrePanel.setLayout(null);
		
		// Logo Panel
		logoPanel.setLayout(null);
		logoPanel.setBounds(164, 65, 400, 205);
		
		// Pop Button
		chordBtn.addActionListener(aListener);
		chordBtn.setBounds(75, 319, 134, 95);
		chordBtn.setOpaque(true);
		chordBtn.setBackground(new Color(255, 255, 255, 242));
		chordBtn.setForeground(new Color(255, 0, 0, 0));
		lblChord.setBounds(125, 424, 45, 15);
		
		// Rock Button
		beatBtn.addActionListener(aListener);
		beatBtn.setBounds(225, 319, 134, 95);
		beatBtn.setOpaque(true);
		beatBtn.setBackground(new Color(255, 255, 255, 242));
		beatBtn.setForeground(new Color(255, 0, 0, 0));
		lblBeat.setBounds(277, 424, 30, 15);
		
		// Ballad Button
		voiceBtn.addActionListener(aListener);
		voiceBtn.setBounds(375, 319, 134, 95);
		voiceBtn.setOpaque(true);
		voiceBtn.setBackground(new Color(255, 255, 255, 242));
		voiceBtn.setForeground(new Color(255, 0, 0, 0));
		lblVoice.setBounds(426, 424, 36, 15);
		
		// Random Button
		randomBtn.addActionListener(aListener);
		randomBtn.setBounds(525, 319, 134, 95);
		randomBtn.setOpaque(true);
		randomBtn.setBackground(new Color(255, 255, 255, 242));
		randomBtn.setForeground(new Color(255, 0, 0, 0));
		lblRandom.setBounds(568, 424, 48, 15);

		// Add Components to Select Genre Panel
		selectGenrePanel.add(logoPanel);
		selectGenrePanel.add(chordBtn);
		selectGenrePanel.add(beatBtn);
		selectGenrePanel.add(voiceBtn);
		selectGenrePanel.add(randomBtn);
		selectGenrePanel.add(lblChord);
		selectGenrePanel.add(lblBeat);
		selectGenrePanel.add(lblVoice);
		selectGenrePanel.add(lblRandom);
		
		// Add Components to Main Frame
		add(selectGenrePanel);
	}
	
	// Change Screen
	public void switching(JButton button) {

		// Create Loop Panel
		MusicPlayer loopPanel = new MusicPlayer(frame, this);
		
		// Set genre
		if (button == chordBtn) {
			loopPanel.getGenre("Chord", loopPanel);
		} else if(button == beatBtn) {
			loopPanel.getGenre("Beat", loopPanel);
		} else if(button == voiceBtn) {
			loopPanel.getGenre("Voice", loopPanel);
		} else if(button == randomBtn) {
			loopPanel.getGenre("Random", loopPanel);
		}
		
		// Switch
		setVisible(false);
		loopPanel.setVisible(true);
	}
}
