
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class C_DeviceManage extends JFrame implements ActionListener{
	private int count = 0;
	
	private JTable table;
	private JTextField textField;
	private JPanel panel;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JButton searchButton;
	private JButton showAllButton;
	private JButton addButton;
	private JButton deleteButton;
	//private JButton reviseButton;
	private JButton returnButton;
	private JButton exitButton;
	
	Object[] columnNames = new Object[]{"d_设备号", "d_设备名", "d_类", "d_规格", "d_手动号码", "d_家庭号"};
	Object[][] rowData = new Object[0][];
	private DefaultTableModel model;
	
	private JLabel sumLabel = new JLabel();
	
	public C_DeviceManage() {
		C_DeviceManage.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 559);
		this.setTitle("管理员系统");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"设备号", "设备名", "类", "手动号码", "家庭号"}));
		panel.add(comboBox);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		searchButton = new JButton("搜索");
		panel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText() == "") {
					JOptionPane.showMessageDialog(getContentPane(), "请填写全部信息", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					
					model.setRowCount(0);
					count = 0;
					try {                                                                              
						Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM device");
						
						while(rs.next()){
				            if(rs.getString("d_" + (String)comboBox.getSelectedItem()).equals(textField.getText()) && rs.getInt("d_isDeleted")==0) {
				            	String[] newRow = {rs.getString("d_设备号"), rs.getString("d_设备名"), rs.getString("d_类"), rs.getString("d_规格"), rs.getString("d_手动号码"), rs.getString("d_家庭号")};
					            model.addRow(newRow);
					            count++;
				            }
				        }
				        sumLabel.setText("总共 " + count + " 条记录");
					} catch(Exception e1) {
						System.out.println("连接失败: " + e1.getMessage());
					}				
				}
			}
		});
		
		addButton = new JButton("添加");
		panel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							P_AddADevice frame = new P_AddADevice();
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
							if(table.getSelectedRow()==-1) {
								JOptionPane.showMessageDialog(getContentPane(), "请选中想要删除的数据后点击删除按钮. ", "warning", JOptionPane.WARNING_MESSAGE);
							} else {
					        Q_DeleteTheDevice.delete(table, model);
							}
						} catch (SQLException e1) {
					         e1.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(deleteButton);
		
//		reviseButton = new JButton("Revise");
//		reviseButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				EventQueue.invokeLater(new Runnable() {
//					public void run() {
//						try {
//							
//							if(table.getSelectedRow()==-1) {
//								JOptionPane.showMessageDialog(getContentPane(), "Please click a data in table to choose the record you want to revise. \nAnd this user's password will be changed randomly. ", "warning", JOptionPane.WARNING_MESSAGE);
//							} else {
//								new ReviseTheUserPsw(table, model);
//							}
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//		});
//		panel.add(reviseButton);
		
		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_DeviceManage.this.setVisible(false);
				new B_Admin();
			}
		});
		returnButton.setFont(new Font("宋体", Font.BOLD, 12));
		panel.add(returnButton);
		
		exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("宋体", Font.BOLD, 12));
		panel.add(exitButton);
		getContentPane().add(panel);
		
		model = new DefaultTableModel(rowData, columnNames);
		table = new JTable(model);
		
		try {       
			count = 0;
			
			Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM device");
	        
	        while(rs.next()){
	        	if(rs.getInt("d_isDeleted")==0) {
		            String[] newRow = {rs.getString("d_设备号"), rs.getString("d_设备名"), rs.getString("d_类"), rs.getString("d_规格"), rs.getString("d_手动号码"), rs.getString("d_家庭号"), };
	        		model.addRow(newRow);
	        		count++;
	        	}
	        } 
	        sumLabel.setText("总共 " + count + " 条记录");
		} catch(Exception e) {
			System.out.println("连接失败: " + e.getMessage());
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);	
		getContentPane().add(scrollPane);
		
		sumLabel = new JLabel("总共 " + count + " 条记录");
		panel.add(sumLabel);
		
		showAllButton = new JButton("展示全部记录");
		panel.add(showAllButton);
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				count = 0;
				
				try {                                                                              
					Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
					Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery("SELECT * FROM device");
			        
			        while(rs.next()){
			        	if(rs.getInt("d_isDeleted")==0) {
				            String[] newRow = {rs.getString("d_设备号"), rs.getString("d_设备名"), rs.getString("d_类"), rs.getString("d_规格"), rs.getString("d_手动号码"), rs.getString("d_家庭号"), };
			        		model.addRow(newRow);
			        		count++;
			        	}
			        } 
			        sumLabel.setText("总共 " + count + " 条记录");
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
