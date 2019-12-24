package Gui;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Server.Server;

public class InitialScreen extends JFrame {
	
	private JPanel initialPanel = new JPanel(new GridLayout(3,2));
	private JLabel idLabel = new JLabel("ID");
	private JLabel pwdLabel = new JLabel("Pwd");
	private JTextField idText = new JTextField();
	private JTextField pwdText = new JTextField();
	private JPanel exitPanel = new JPanel(new GridLayout(1,2));
	private JPanel btnPanel = new JPanel(new GridLayout(1,3));
	private JButton exitBtn = new JButton("Exit");
	private JButton signInBtn = new JButton("SignIn");
	private JButton signUpBtn = new JButton("SignUp");
	private JButton findAccountBtn = new JButton("FindAccount");
	private SignUpDialog signUpDialog;
	
	public InitialScreen(Server dbServer) {
		
		super("Chatting Program");
		this.setContentPane(initialPanel);
		initialPanel.add(idLabel);
		initialPanel.add(idText);
		initialPanel.add(pwdLabel);
		initialPanel.add(pwdText);
		initialPanel.add(exitPanel);
		initialPanel.add(btnPanel);
		exitPanel.add(exitBtn);
		exitPanel.add(new JPanel());
		btnPanel.add(signInBtn);
		btnPanel.add(signUpBtn);
		btnPanel.add(findAccountBtn);
		
		idLabel.setHorizontalAlignment(NORMAL);
		pwdLabel.setHorizontalAlignment(NORMAL);
		
		this.setSize(500,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		signInBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = dbServer.signIn(idText.getText(), pwdText.getText());
					if(rs.next()) {
						if(rs.getString(1).equals(idText.getText()))
								System.out.println("Login Success");
					} else {
//						System.out.println("Login Fail");
						idText.setText(null);
						pwdText.setText(null);
						JOptionPane.showMessageDialog(null, "SignIn Fail. Please check id or password!!", "SignIn Check", JOptionPane.DEFAULT_OPTION);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		signUpDialog = new SignUpDialog(this, "SignUp", dbServer);
		signUpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				signUpDialog.setVisible(true);
			}
			
		});
	}
	
}
