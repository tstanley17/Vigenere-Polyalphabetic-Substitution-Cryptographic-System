package GUI;

import java.awt.EventQueue;
import java.awt.MediaTracker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import GUI_sockets.client_interface;
import cryptography.algorithms;
import database.database_logins;
import messaging.thread_chat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignupGUI {

	private BufferedImage img; 
	private ImageIcon background;
	private JFrame frame;
	private JTextField UserName_text;
	private JTextField Password_text;
	private static java.sql.Connection connection;
	public database_logins db;
	
	String url = "jdbc:mysql://localhost:3306/vigeneresystem";
	String username = "root";
	String password = "Basketball14!";

	/**
	 * Create the application.
	 */
	public SignupGUI() {
		db = new database_logins();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		UserName_text = new JTextField();
		UserName_text.setBounds(129, 68, 200, 36);
		panel.add(UserName_text);
		UserName_text.setColumns(10);
		
		Password_text = new JTextField();
		Password_text.setBounds(129, 129, 200, 36);
		panel.add(Password_text);
		Password_text.setColumns(10);
		
		JLabel UserName = new JLabel("USERNAME");
		UserName.setBounds(54, 67, 75, 36);
		panel.add(UserName);
		
		JLabel Password = new JLabel("PASSWORD");
		Password.setBounds(54, 128, 75, 36);
		panel.add(Password);
		
		JButton Signup_Button = new JButton("Sign Up");
		Signup_Button.setBounds(93, 198, 91, 21);
		Signup_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get username and password
				String Uname = UserName_text.getText();
				String Pword = Password_text.getText();
				
				if(db.isUniqueUser(Uname)) {
					boolean checkInsert = db.newUser(Uname, Pword);
					if(checkInsert) {
						frame.dispose();
						algorithms alg = new algorithms();
						User_Interface ui = new User_Interface(alg);
					} else {
						JOptionPane.showMessageDialog(null, "Error occured, please try again.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username already exists.");
				}
				
				

			}
		});
		panel.add(Signup_Button);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(229, 10, 10, 10);
		panel.add(panel_1);
		
		JButton Login_Button = new JButton("Log In");
		Login_Button.setBounds(229, 198, 91, 21);
		Login_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.dispose();
				new LoginGUI();
			}
			
		});
		
		panel.add(Login_Button);
		
		frame.setVisible(true);
	}
}
