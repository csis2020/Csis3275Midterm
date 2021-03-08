package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.BattleShipController;


public class BattleShipView {

	private JFrame frame;
	private JPanelWithBackgroundImage panel;
	private JButton east, west, north, south;
	private JPanel pannel2;
	JButton[] buttonList;
	
	private String imageFile = ".\\resources\\images\\board.jpg";
	private String imageShip = ".\\resources\\images\\ship.png";
	private String imageMiss = ".\\resources\\images\\miss.png";
	
	private BattleShipController controller;

	/**
	 * Create the application.
	 */
	public BattleShipView() {
		controller = null;
		initialize();
		//this.frame.setVisible(true);
	}

	public void setVisible() {
		this.frame.setVisible(true);
	}
	
	public void addController(BattleShipController bsController) {
		controller = bsController;
		
		east.addActionListener(controller);
		west.addActionListener(controller);
		north.addActionListener(controller);
		south.addActionListener(controller);
		
		for(int i=0; i < buttonList.length; i++) {
			buttonList[i].addActionListener(controller);
		}
	}
	
	public void displayMessage(String message) {

		JOptionPane.showMessageDialog(panel, message);
	}
	
	public void displayHit(int position) {

		System.out.println("HIT! position: " +position);
		try {
			
			buttonList[position].setIcon(new ImageIcon(imageShip));
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

	public void displayMiss(int position) {

		System.out.println("MISS! position: " +position);
		try {
			
			buttonList[position].setIcon(new ImageIcon(imageMiss));
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void clear() {
		for(int i=0; i < buttonList.length; i++) {
			buttonList[i].setIcon(null);
		}		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Battle Ship");
		frame.setBounds(100, 100, 1040, 902);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(100, 100));
		frame.setResizable(false);
		
		try {
			panel = new JPanelWithBackgroundImage(imageFile);
			panel.setLayout(new BorderLayout());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
	
		east = new JButton("EAST");
		west = new JButton("WEST");
		north = new JButton("NORTH");
		south = new JButton("SOUTH");
				
		east.setPreferredSize(new Dimension(177,100));
		west.setPreferredSize(new Dimension(171,100));
		north.setPreferredSize(new Dimension(100,97));
		south.setPreferredSize(new Dimension(100,90));
		
		//It is moved to the addController().
		/*
		east.addActionListener(this);
		west.addActionListener(this);
		north.addActionListener(this);
		south.addActionListener(this);
		 */
	

		east.setBorderPainted(false);
		east.setContentAreaFilled(false);
		west.setBorderPainted(false);
		west.setContentAreaFilled(false);
		north.setBorderPainted(false);
		north.setContentAreaFilled(false);
		south.setBorderPainted(false);
		south.setContentAreaFilled(false);

		panel.add(east, BorderLayout.EAST);
		panel.add(west, BorderLayout.WEST);
		panel.add(north, BorderLayout.NORTH);
		
		panel.add(south, BorderLayout.SOUTH);		
		
		
		pannel2 = new JPanel();
		pannel2.setLayout(new GridLayout(7,7));
		pannel2.setOpaque(false);

		
		panel.add(pannel2, BorderLayout.CENTER);
		
		buttonList = new JButton[49];
		for(int i=0; i < buttonList.length; i++) {
			buttonList[i] = new JButton("SHOOT"+i);
			buttonList[i].setBorderPainted(false);
			buttonList[i].setContentAreaFilled(false);
			//buttonList[i].addActionListener(this);
			pannel2.add(buttonList[i], i);
		}

	}


}
