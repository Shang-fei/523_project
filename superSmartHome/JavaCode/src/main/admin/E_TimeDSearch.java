import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class E_TimeDSearch extends JFrame implements ActionListener{
	private int count = 0;
	private JLabel sumLabel = new JLabel();
	
	private JPanel panel;
	private JLabel stLabel, etLabel;
	private JTextField textField, textField_1;
	private JButton searchButton;
	private JButton showAllButton;
	private JButton returnButton;
	private JButton exitButton;
	private JButton mapButton;
	private JTable table_1;
	private JScrollPane scrollPane; 
	
	private Object[] columnNames = new Object[]{"设备号", "时间", "状态"};
	private Object[][] rowData = new Object[0][];
	private DefaultTableModel model;

	public E_TimeDSearch(String manuId,int flag) {
		E_TimeDSearch.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		if(flag == 0) {
			this.setTitle("门时间搜索系统");
		}
		else {
			this.setTitle("门时间搜索系统");
		}
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		stLabel = new JLabel("开始时间:");
		panel.add(stLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		etLabel = new JLabel("   结束时间:");
		panel.add(etLabel);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		searchButton = new JButton("搜索");
		panel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("") || textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "请填写全部数据", "warning", JOptionPane.WARNING_MESSAGE);
				} else {
					model.setRowCount(0);
					count = 0;
					try {
						Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
						Statement stmt = conn.createStatement();
				        ResultSet rs = null;
				        if(flag == 0) {
				        	rs = stmt.executeQuery("SELECT * FROM door WHERE do_时间 BETWEEN \"" + textField.getText() + "\" AND \"" + textField_1.getText()+ "\"");
				        }
				        else {
				        	rs = stmt.executeQuery("SELECT * FROM door,device WHERE do_时间 BETWEEN \"" + textField.getText() + "\" AND \"" + textField_1.getText()+ "\" AND d_手动号码 = \"" + manuId + "\" AND d_设备号 = do_设备号;");
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
				
			}
		});
				
		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				E_TimeDSearch.this.setVisible(false);
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
	        	rs = stmt.executeQuery("SELECT * FROM door ");
	        }
	        else {
	        	rs = stmt.executeQuery("SELECT * FROM door,device where d_手动号码 = \"" + manuId + " \"and do_设备号 = d_设备号; ");
			}
	        
	        while(rs.next()){
	        	if(rs.getInt("do_isDeleted")==0) {
	        		String[] newRow = {rs.getString("do_设备号"), rs.getString("do_时间"), rs.getString("do_状态")};
	        		model.addRow(newRow);
	        		count++;
	        	}
	        }   
	        sumLabel.setText("总共 " + count + " 条记录");
		} catch(Exception e) {
			System.out.println("连接失败: " + e.getMessage());
		}
		
		panel.add(sumLabel);
		
		showAllButton = new JButton("展示全部记录");
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = 0;
				model.setRowCount(0);

				try {
					Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
					Statement stmt = conn.createStatement();
					ResultSet rs = null;
			        if(flag == 0) {
			        	rs = stmt.executeQuery("SELECT * FROM door ");
			        }
			        else {
			        	rs = stmt.executeQuery("SELECT * FROM door,device where d_手动号码 = \"" + manuId + " \"and do_设备号 = d_设备号; ");
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
		// TODO Auto-generated method stub
		
	}

}

