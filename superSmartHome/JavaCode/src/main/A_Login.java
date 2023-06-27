import java.awt.BorderLayout;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class A_Login extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JPasswordField pswTextField;
	
	/**
	 * Create the frame.
	 */
	public A_Login() {
		setTitle("欢迎来到智慧家居系统");
		A_Login.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel headPanel = new JPanel();
		contentPane.add(headPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("登陆");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headPanel.add(lblNewLabel);
		
		JPanel bodyPanel = new JPanel();
		contentPane.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel idPanel = new JPanel();
		bodyPanel.add(idPanel);
		
		JLabel idLabel = new JLabel("账号:  ");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		idPanel.add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setColumns(25);
		idPanel.add(idTextField);
		
		JPanel pswPanel = new JPanel();
		bodyPanel.add(pswPanel);
		
		JLabel pswLabel = new JLabel("密码: ");
		pswLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		pswPanel.add(pswLabel);
		
		pswTextField = new JPasswordField();
		pswTextField.setColumns(25);
		pswTextField.setEchoChar('*');
		pswPanel.add(pswTextField);
		
		JPanel identifyPanel = new JPanel();
		bodyPanel.add(identifyPanel);
		
		JLabel identLabel = new JLabel("你的身份 ");
		identLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		identifyPanel.add(identLabel);
		
		JComboBox<String> identCombo = new JComboBox<String>();
		identCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"管理员", "制造商"}));
		identCombo.setToolTipText("");
		identCombo.setFont(new Font("宋体", Font.PLAIN, 14));
		identifyPanel.add(identCombo);
		
		JPanel loginPanel = new JPanel();
		bodyPanel.add(loginPanel);
		
		JButton loginBtn = new JButton("登陆");
	
		loginBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				boolean match = true;
				
				String id = idTextField.getText();
				//String pass = pswTextField.getText();
				int IndexofIdentify = identCombo.getSelectedIndex();
				
				try {                                                                              
					Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
					Statement stmt = conn.createStatement();
					if(IndexofIdentify==0) {
						ResultSet rs = stmt.executeQuery("SELECT a_adminId, a_adminPassword FROM admin");
						
						while(rs.next()){
				            if(rs.getString("a_adminId").equals(id) && rs.getString("a_adminPassword").equals(ParseMD5.parseStrToMd5L32(pswTextField.getText()))) {
				            	match = true;
				            	break;
				            }
				        }
						
					} else {
						ResultSet rs = stmt.executeQuery("SELECT m_manuId, m_manuPassword FROM manu");
						
						while(rs.next()){
				            if(rs.getString("m_manuId").equals(id) && rs.getString("m_manuPassword").equals(ParseMD5.parseStrToMd5L32(pswTextField.getText()))) {
				            	match = true;
				            	break;
				            }
				        }
					} 
				} catch(Exception e1) {
					System.out.println("Connection fails: " + e1.getMessage());
				}



				if(id.equals("") || pswTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "请输入全部信息", "warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(!match) {
					JOptionPane.showMessageDialog(contentPane, "账号与密码不匹配. ", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					A_Login.this.setVisible(false);
					if(IndexofIdentify == 0) {
						new B_Admin();
					}
					else if(IndexofIdentify == 1){
						new B_Manu(id);
					}
				}
			}
		});
		loginBtn.setFont(new Font("宋体", Font.BOLD, 18));
		loginPanel.add(loginBtn);
	}

}
