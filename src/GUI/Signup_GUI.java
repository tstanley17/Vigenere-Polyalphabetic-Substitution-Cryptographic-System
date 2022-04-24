package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import cryptography.algorithms;
import database.database_logins;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Signup_GUI {

	private JFrame frame;
	private JTextField UserName_text;
	private JTextField Password_text;
	private database_logins db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup_GUI window = new Signup_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Signup_GUI() {
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
		
		JLabel Title = new JLabel("Welcome to Chat Room");
		Title.setForeground(new Color(102, 51, 204));
		Title.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Title.setBackground(new Color(102, 51, 102));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(109, 23, 207, 37);
		panel.add(Title);
		
		JLabel UserName = new JLabel("Username");
		UserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UserName.setBounds(58, 89, 94, 28);
		panel.add(UserName);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Password.setBounds(58, 138, 94, 28);
		panel.add(Password);
		
		UserName_text= new JTextField();
		UserName_text.setBounds(148, 92, 199, 28);
		panel.add(UserName_text);
		UserName_text.setColumns(10);
		
		Password_text = new JTextField();
		Password_text.setBounds(148, 142, 199, 26);
		panel.add(Password_text);
		Password_text.setColumns(10);
		
		JLabel Username_note = new JLabel("(please enter your username)");
		Username_note.setBounds(158, 119, 177, 13);
		panel.add(Username_note);
		
		JLabel Password_note = new JLabel("(please enter your password)");
		Password_note.setBounds(158, 167, 177, 13);
		panel.add(Password_note);
		
		JButton done_button = new JButton("Done");
		done_button.setBounds(151, 205, 85, 21);
		done_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String Uname = UserName_text.getText();
				String Pword = Password_text.getText();
				
				//check the input is not empty 
				if (Uname.isEmpty() || Pword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please finish the filling in your information.");
					return;
				}else {
					//check the
					boolean check = db.isUniqueUser(Uname);
					if(check) {
						db.newUser(Uname, Pword);
						JOptionPane.showMessageDialog(null, "You have successfully registered!");
						frame.dispose();
						new Login_GUI();
					} else {
						JOptionPane.showMessageDialog(null, "This username already exists.");
					}
					
				}
			}
			
		});
		
		panel.add(done_button);
		
		frame.setVisible(true);
	}
}
