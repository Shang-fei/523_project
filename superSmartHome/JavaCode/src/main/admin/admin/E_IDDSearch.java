
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class E_IDDSearch extends JFrame implements ActionListener{
	private int count = 0;
	private JLabel sumLabel = new JLabel();
	
	private JTable table_1;
	private JTextField textField;
	
	private JPanel panel;
	private JLabel idLabel;
	private JButton searchButton;
	private JButton showAllButton;
	private JButton returnButton;
	private JButton exitButton;
	private JScrollPane scrollPane;
	
	private Object[] columnNames = new Object[]{"设备号", "时间", "状态"};
	private Object[][] rowData = new Object[0][];
	private DefaultTableModel model;


	public E_IDDSearch(String manuId,int flag) {
		E_IDDSearch.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 539);
		if(flag == 0) {
			this.setTitle("门设备号搜索系统");
		}
		else {
			this.setTitle("门设备号搜索系统");
		}
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		idLabel = new JLabel("设备号:");
		panel.add(idLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		searchButton = new JButton("搜索");
		panel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "请填写全部信息", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					model.setRowCount(0);
					count = 0;
					try {                                                                              
						Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
						Statement stmt = conn.createStatement();
						ResultSet rs = null;
						if(flag == 0) {
							rs = stmt.executeQuery("SELECT * FROM door");
						}
						else {
							rs = stmt.executeQuery("SELECT * FROM door,device where d_manuId = \"" + manuId + " \"and do_deviceId = d_deviceId; ");
						}
						
						while(rs.next()){
				            if(rs.getString("do_设备号").equals(textField.getText()) && rs.getInt("do_isDeleted")==0) {
				            	String[] newRow = {rs.getString("do_设备号"), rs.getString("do_时间"), rs.getString("do_状态")};
					            model.addRow(newRow);
					            count++;
				            }
				        }
				        sumLabel.setText("总共 " + count + " 记录");
					} catch(Exception e1) {
						System.out.println("连接失败: " + e1.getMessage());
					}
				}
			}
		});
		
		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				E_IDDSearch.this.setVisible(false);
				if(flag == 0){
					new D_Door();
				}
				else {
					new D_Mdoor(manuId);
				}
				
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
			ResultSet rs = null;
			if(flag == 0) {
				rs = stmt.executeQuery("SELECT * FROM door");
			}
			else {
				rs = stmt.executeQuery("SELECT * FROM door,device where d_manuId = \"" + manuId + " \"and do_deviceId = d_deviceId; ");						       
			}
			
	        while(rs.next()){
	            if(rs.getInt("do_isDeleted")==0) {
	            	String[] newRow = {rs.getString("do_设备号"), rs.getString("do_时间"), rs.getString("do_状态")};
		            model.addRow(newRow);
		            count++;
	            }
	        }
	        sumLabel.setText("总共 " + count + " 条记录");
		} catch(Exception e1) {
			System.out.println("连接失败: " + e1.getMessage());
		}
		
		panel.add(sumLabel);
		
		showAllButton = new JButton("展示全部记录");
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);

				try {
					count = 0;
					Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
					if(flag == 0) {
						rs = stmt.executeQuery("SELECT * FROM door");
					}
					else {
						rs = stmt.executeQuery("SELECT * FROM door,device where d_manuId = \"" + manuId + " \"and do_deviceId = d_deviceId; ");
					}

			        while(rs.next()){
			            if(rs.getInt("do_isDeleted")==0) {
			            	String[] newRow = {rs.getString("do_设备号"), rs.getString("do_时间"), rs.getString("do_状态")};
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
		panel.add(showAllButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table_1);	
		getContentPane().add(scrollPane);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			if(textField.getText().equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "请填写全部信息", "warning", JOptionPane.WARNING_MESSAGE);
			} else {
				model.setRowCount(0);
				SearchAndWriteToJTable.search(model, textField.getText(), "设备号", "门");
			}
		}
		
	}

}
