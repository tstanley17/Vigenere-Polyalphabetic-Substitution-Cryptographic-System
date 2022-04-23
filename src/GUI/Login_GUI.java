package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.MediaTracker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

public class Login_GUI {

	private BufferedImage img; 
	private ImageIcon background;
	private JFrame frame;
	private JTextField UserName_text;
	private JTextField Password_text;
	private static java.sql.Connection connection;
	public database_logins db;
	
	String url = "jdbc:mysql://localhost:3306/vigeneresystem";
	String username = "root";
	String password = "longer000";

	/**
	 * Create the application.
	 */
	public Login_GUI() {
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
		
		JLabel Title = new JLabel("Welcome to Chat Room");
		Title.setForeground(new Color(102, 51, 204));
		Title.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Title.setBackground(new Color(102, 51, 102));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(109, 23, 207, 37);
		panel.add(Title);
		
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
				
				if (Uname.isEmpty() || Pword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "The username or password you entered was incorrect.");
					return;
				}
				//Check that the password and username are correct
				if(db.checkCredentials(Uname, Pword) == true) {
					//if correct, open the chat room
					int port = 4021;

					try {
						thread_chat chat = new thread_chat("230.0.0.0", port, new client_interface());
						Thread t = new Thread(chat);
						t.start();

						// This second thread is for demostration
						thread_chat chat2 = new thread_chat("230.0.0.0", port, new client_interface());
						Thread t2 = new Thread(chat2);
						t2.start();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "\r\n"
							+ "The username or password you entered was incorrect.");
					return;
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
				new Signup_GUI();
			}
			
		});
		
		panel.add(Login_Button);
		
		frame.setVisible(true);
	}
}
