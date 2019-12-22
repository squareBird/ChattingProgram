package Gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Server.Server;

public class SignUpDialog extends JDialog  {
	
	//ID �ߺ�üũ�� �����ߴ��� Ȯ������
	private boolean duplicateCheck = false;
	
	private JPanel signUpPanel = new JPanel(new GridLayout(7,2));
	private JPanel duplicateCheckPanel = new JPanel(new GridLayout(1,2));
	
	private JLabel[] labels = new JLabel[6];
	private JTextField[] textFields = new JTextField[6];
	private String[] labelsText = {"ID", "Pwd", "PwdCheck", "Name", "Age", "Phone"};
	
	private JButton duplicateBtn = new JButton("Duplicate");
	private JButton signUpBtn = new JButton("SignUP");
	private JButton closeBtn = new JButton("close");
	
	public SignUpDialog(JFrame frame, String title, Server dbServer) {
		
		super(frame, title);
		this.setContentPane(signUpPanel);
		
		for(int i=0;i<labels.length;i++) {
			labels[i] = new JLabel(labelsText[i]);
			textFields[i] = new JTextField();
			signUpPanel.add(labels[i]);
			if(i==0) {
				signUpPanel.add(duplicateCheckPanel);
				duplicateCheckPanel.add(textFields[i]);
				duplicateCheckPanel.add(duplicateBtn);
			} else {
				signUpPanel.add(textFields[i]);
			}
		}
		
		signUpPanel.add(signUpBtn);
		signUpPanel.add(closeBtn);
		
		this.setSize(500,150);
		this.setLocationRelativeTo(null);
		
		textFields[0].getDocument().addDocumentListener(new DocumentListener() {
			
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
					ResultSet rs = dbServer.sendExecuteQuery("select id from account where id = '" + textFields[0].getText() +"';");
					if(rs.next()) {
						if(rs.getString(1).equals(textFields[0].getText())) {
							textFields[0].setText(null);
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
					
					// null���� �ؽ�Ʈ �ʵ尡 �����ϸ� "please not null" ���.
					if(!nullCheck()) {
						
						// id�ߺ�üũ�� �ǽ����� �ʾ����� "please id duplicate check" ���.
						if(duplicateCheck) {
							
							// pwd�� pwdCheck��ġ�ϴ��� Ȯ��
							if(pwdCheck()) {
								
								int result = dbServer.signUp(textFields[0].getText(), textFields[1].getText(), textFields[3].getText(), Integer.parseInt(textFields[4].getText()), textFields[5].getText());
								
								// ������ ������ ȸ������ ���� ��� �� ���̾�α� ����
								if(result!=0) {
									JOptionPane.showMessageDialog(null, "SignUp Success!!", "SignUp CHeck", JOptionPane.DEFAULT_OPTION);
									init();
									setVisible(false);
								}
							} else {
								JOptionPane.showMessageDialog(null, "PwdCheck not same Pwd.", "SignUp CHeck", JOptionPane.DEFAULT_OPTION);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Please ID duplicate check.", "SignUp CHeck", JOptionPane.DEFAULT_OPTION);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Please not null.", "SignUp CHeck", JOptionPane.DEFAULT_OPTION);
					}
				} catch (NumberFormatException e1) { // age �ؽ�Ʈ�ʵ尡 ���ڰ� �ƴϸ� ���� ���
					textFields[3].setText(null);
					JOptionPane.showMessageDialog(null, "Age need Number", "SignUp Check", JOptionPane.DEFAULT_OPTION);
				} catch (SQLException e1) { // ���� �̻��ϸ� ȸ������ ���� ���.
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
	
	
	// �ؽ�Ʈ�ʵ� �ʱ�ȭ
	void init() {
		for(int i=0 ; i<labels.length ; i++) {
			textFields[i].setText(null);
		}	
	}
	
	// �� ���� �ִ��� üũ
	boolean nullCheck() {
		for(JTextField tf : textFields) {
			if(tf.getText().equals("")) {
				System.out.println("�ΰ� ����");
				return true;
			}
		}
		return false;
	}
	
	boolean pwdCheck() {
		return textFields[1].getText().equals(textFields[2].getText());
	}
}
