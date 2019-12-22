package Gui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Server.Server;

public class InitialScreen extends JFrame {
	
	private JPanel initialPanel = new JPanel(new GridLayout(3,2));
	private JLabel idLabel = new JLabel("ID");
	private JLabel pwdLabel = new JLabel("Pwd");
	private JTextField idText = new JTextField();
	private JTextField pwdText = new JTextField();
	private JPanel btnPanel = new JPanel(new GridLayout(1,3));
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
		initialPanel.add(new JLabel());
		initialPanel.add(btnPanel);
		btnPanel.add(signInBtn);
		btnPanel.add(signUpBtn);
		btnPanel.add(findAccountBtn);
		
		idLabel.setHorizontalAlignment(NORMAL);
		pwdLabel.setHorizontalAlignment(NORMAL);
		
		this.setSize(500,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		signInBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "로그인 확인", "로그인 확인", JOptionPane.DEFAULT_OPTION);
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
