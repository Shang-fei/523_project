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


public class C_FamilyManage extends JFrame implements ActionListener{
	private int count = 0;
	
	private JTable table_1;
	private JTextField textField;
	private JPanel panel;
	private JLabel familyIDLabel;
	private JButton searchButton;
	private JButton showAllButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton reviseButton;
	private JButton exitButton;
	private JButton returnButton;
	private JComboBox<String> comboBox = new JComboBox<String>();
	
	Object[] columnNames = new Object[]{"家庭号", "省份"};
	Object[][] rowData = new Object[0][];
	DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
	
	private JLabel sumLabel;

	public C_FamilyManage() {
		sumLabel = new JLabel("总共 " + count + " 条记录");
		
		C_FamilyManage.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 517);
		this.setTitle("管理员系统");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"家庭号", "省份"}));
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
						ResultSet rs = stmt.executeQuery("SELECT * FROM family");
						
						while(rs.next()){
				            if(rs.getString("f_" + (String)comboBox.getSelectedItem()).equals(textField.getText()) && rs.getInt("f_isDeleted") == 0) {
				            	String[] newRow = {rs.getString("f_家庭号"), rs.getString("f_省份")};
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
							P_AddAFamily frame = new P_AddAFamily();
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
					        Q_DeleteTheFamily.delete(table_1, model);
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
				C_FamilyManage.this.setVisible(false);
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
		
		table_1 = new JTable(model);
		
		try {         
			count = 0;
			
			Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM family ORDER BY f_家庭号");
	        
	        while(rs.next()){
	            if(rs.getInt("f_isDeleted")==0) {
	            	String[] newRow = {rs.getString("f_家庭号"), rs.getString("f_省份")};
		            model.addRow(newRow);
		            count++;
	            }
	        }
	        sumLabel.setText("总共" + count + " 条记录");
		} catch(Exception e) {
			System.out.println("连接失败: " + e.getMessage());
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table_1);	
		getContentPane().add(scrollPane);
		
		panel.add(sumLabel);
		
		showAllButton = new JButton("展示全部记录");
		panel.add(showAllButton);
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				count = 0;
				
				try {   
					model.setRowCount(0);
					
					Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
					Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery("SELECT * FROM family ORDER BY f_家庭号");
			        
			        while(rs.next()){
			        	if(rs.getInt("f_isDeleted")==0) {
			            	String[] newRow = {rs.getString("f_家庭号"), rs.getString("f_省份")};
				            model.addRow(newRow);
				            count++;
			            }
			        }     
			        sumLabel.setText("总共 " + count + " 条记录");
				} catch(Exception e) {
					System.out.println("连接失败: " + e.getMessage());
				}
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
