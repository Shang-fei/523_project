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


public class C_MDeviceManage extends JFrame implements ActionListener{
	private int count = 0;
	private JLabel sumLabel = new JLabel();
	
	private JTable table_1;
	private JTextField textField;
	private JPanel panel;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JButton searchButton;
	private JButton showAllButton;
	private JButton addButton;
	private JButton deleteButton;
	private JButton reviseButton;
	private JButton returnButton;
	private JButton exitButton;
	
	private Object[] columnNames = new Object[]{"d_设备号", "d_设备名", "d_类", "d_规格", "d_手动号码", "d_家庭号"};
	private Object[][] rowData = new Object[0][];
	private DefaultTableModel model;
	

	public C_MDeviceManage(String manuId) {
		C_MDeviceManage.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 627);
		this.setTitle("设备管理");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"设备号", "设备名", "类", "规格", "手动号码", "家庭号"}));
		panel.add(comboBox);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		searchButton = new JButton("搜索");
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
				            if(rs.getString("d_" + (String)comboBox.getSelectedItem()).equals(textField.getText()) && rs.getString("d_手动号码").equals(manuId)) {
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
		panel.add(searchButton);
		
		addButton = new JButton("增加");
		panel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							P_AddADevice frame = new P_AddADevice(manuId);
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
					        Q_DeleteTheDevice.delete(table_1, model);
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
				C_MDeviceManage.this.setVisible(false);
				new B_Manu();
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
		table_1 = new JTable(model);
		
		try {       
			count = 0;
			
			Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM device");
	        
	        while(rs.next()){
	        	if(rs.getInt("d_isDeleted")==0 && rs.getString("d_手动号码").equals(manuId)) {
		            String[] newRow = {rs.getString("d_设备号"), rs.getString("d_设备名"), rs.getString("d_类"), rs.getString("d_规格"), rs.getString("d_手动号码"), rs.getString("d_家庭号"), };
	        		model.addRow(newRow);
	        		count++;
	        	}
	        } 
	        sumLabel.setText("总共 " + count + " 条记录");
		} catch(Exception e) {
			System.out.println("连接失败: " + e.getMessage());
		}
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
			        	if(rs.getInt("d_isDeleted")==0 && rs.getString("d_手动号码").equals(manuId)) {
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table_1);	
		getContentPane().add(scrollPane);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
