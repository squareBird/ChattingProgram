package Gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Server.Server;

public class SignUpDialogBackup extends JDialog  {
	
	//ID 중복체크를 실행했는지 확인절차
	private boolean duplicateCheck = false;
	
	private JPanel signUpPanel = new JPanel(new GridLayout(6,2));
	private JPanel duplicateCheckPanel = new JPanel(new GridLayout(1,2));
	
	private JLabel idLabel = new JLabel("ID");
	private JTextField idText = new JTextField();
	private JButton duplicateBtn = new JButton("Duplicate");

	private JLabel pwdLabel = new JLabel("Pwd");
	private JTextField pwdText = new JTextField();

	private JLabel nameLabel = new JLabel("Name");
	private JTextField nameText = new JTextField();

	private JLabel ageLabel = new JLabel("Age");
	private JTextField ageText = new JTextField();

	private JLabel phoneLabel = new JLabel("Phone");
	private JTextField phoneText = new JTextField();
	
	private JButton signUpBtn = new JButton("SignUP");
	private JButton closeBtn = new JButton("close");
	
	public SignUpDialogBackup(JFrame frame, String title, Server dbServer) {
		
		super(frame, title);
		this.setContentPane(signUpPanel);
		
		
		signUpPanel.add(idLabel);
		signUpPanel.add(duplicateCheckPanel);
		duplicateCheckPanel.add(idText);
		duplicateCheckPanel.add(duplicateBtn);

		signUpPanel.add(pwdLabel);
		signUpPanel.add(pwdText);

		signUpPanel.add(nameLabel);
		signUpPanel.add(nameText);

		signUpPanel.add(ageLabel);
		signUpPanel.add(ageText);

		signUpPanel.add(phoneLabel);
		signUpPanel.add(phoneText);
		
		signUpPanel.add(signUpBtn);
		signUpPanel.add(closeBtn);
		
		this.setSize(500,150);
		this.setLocationRelativeTo(null);
		
		idText.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {	}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				duplicateCheck = false;
				System.out.println(duplicateCheck);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});		
		
		duplicateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = dbServer.sendExecuteQuery("select id from account where id = '" + idText.getText() +"';");
					if(rs.next()) {
						if(rs.getString(1).equals(idText.getText())) {
							idText.setText(null);
							JOptionPane.showMessageDialog(null, "This id is duplicate.", "Duplicate Check", JOptionPane.DEFAULT_OPTION);
						}
					} else {
						duplicateCheck = true;
						System.out.println(duplicateCheck);
						JOptionPane.showMessageDialog(null, "You can use this ID.", "Duplicate Check", JOptionPane.DEFAULT_OPTION);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		signUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)  {
				try {
					
					if(duplicateCheck) {
						
						int result = dbServer.signUp(idText.getText(), pwdText.getText(), nameText.getText(), Integer.parseInt(ageText.getText()), phoneText.getText());
						
						if(result!=0) {
							JOptionPane.showMessageDialog(null, "SignUp Success!!", "SignUp CHeck", JOptionPane.DEFAULT_OPTION);
							init();
							setVisible(false);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Please ID duplicate check.", "SignUp CHeck", JOptionPane.DEFAULT_OPTION);
					}
					
					
					
				} catch (NumberFormatException e1) {
					ageText.setText(null);
					JOptionPane.showMessageDialog(null, "Age need Number", "SignUp Check", JOptionPane.DEFAULT_OPTION);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "SignUp Fail!!", "SignUp Check", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
	void init() {
		idText.setText(null);
		pwdText.setText(null);
		nameText.setText(null);
		ageText.setText(null);
		phoneText.setText(null);	
	}
	
}
