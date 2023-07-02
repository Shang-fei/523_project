import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C_UserManage extends JFrame implements ActionListener{
	
	private int count = 0;
	
	private JTextField textField;
	private JLabel sumLabel;
	private JButton searchButton;
	private JButton showAllButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton reviseButton;
	private JButton returnButton;
	private JButton exitButton;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JTable table_1;
	
	private Object[] columnNames = new Object[]{"手机号码", "姓名"};
	private Object[][] rowData = new Object[0][];
	private DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

	public C_UserManage() {
		C_UserManage.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 535);
		this.setTitle("管理员系统");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel panel = new JPanel();
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"手机号码", "姓名"}));
		panel.add(comboBox);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		searchButton = new JButton("搜索");
		panel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText() == "") {
					JOptionPane.showMessageDialog(getContentPane(), "请填写所有信息", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					model.setRowCount(0);
					count = 0;
					try {                                                                              
						Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM user");
						
						while(rs.next()){
				            if(rs.getString("u_" + (String)comboBox.getSelectedItem()).equals(textField.getText()) && rs.getInt("u_isDeleted")==0) {
				            	String[] newRow = {rs.getString("u_手机号码"), rs.getString("u_姓名")};
					            model.addRow(newRow);
					            count++;
				            }
				        }
				        sumLabel.setText("总共 " + count + " 条记录");
					} catch(Exception e1) {
						System.out.println("连接失败: " + e1.getMessage());
					}				}
			}
		});
		
		addButton = new JButton("添加");
		panel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							P_AddAUser frame = new P_AddAUser();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							if(table_1.getSelectedRow()==-1) {
								JOptionPane.showMessageDialog(getContentPane(), "请选中想要删除的数据后点击删除按钮. ", "warning", JOptionPane.WARNING_MESSAGE);
							} else {
					        Q_DeleteTheUser.delete(table_1, model);
							}
						} catch (SQLException e1) {
					         e1.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(deleteButton);

		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_UserManage.this.setVisible(false);
				new B_Admin();
			}
		});
		returnButton.setFont(new Font("宋体", Font.BOLD, 13));
		panel.add(returnButton);
		
		exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("宋体", Font.BOLD, 13));
		panel.add(exitButton);
		getContentPane().add(panel);
		
		sumLabel = new JLabel();
		
		table_1 = new JTable(model);
		
		try {      
			count = 0;
			Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT u_手机号码, u_姓名, u_isDeleted FROM user ORDER BY u_手机号码");
	        
	        while(rs.next()){
	        	
	        	if(rs.getInt("u_isDeleted")==0) {
	        		String[] newRow = {rs.getString("u_手机号码"), rs.getString("u_姓名")};
	        		model.addRow(newRow);
	        		count++;
	        	}
	        }   
	        sumLabel.setText("总共 " + count + " 条记录");
		} catch(Exception e) {
			System.out.println("连接错误: " + e.getMessage());
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table_1);	
		getContentPane().add(scrollPane);
		
		panel.add(sumLabel);
		
		showAllButton = new JButton("查看所有记录");
		panel.add(showAllButton);
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = 0;
				model.setRowCount(0);
				
				try {
					count = 0;
					Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
					Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery("SELECT u_手机号码, u_姓名, u_isDeleted FROM user ORDER BY u_手机号码");
			        
			        while(rs.next()){
			        	
			        	if(rs.getInt("u_isDeleted")==0) {
			        		String[] newRow = {rs.getString("u_手机号码"), rs.getString("u_姓名")};
			        		model.addRow(newRow);
			        		count++;
			        	}
			        }   
			        sumLabel.setText("总共 " + count + " 记录");
				} catch(Exception e1) {
					System.out.println("连接失败: " + e1.getMessage());
				}
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
