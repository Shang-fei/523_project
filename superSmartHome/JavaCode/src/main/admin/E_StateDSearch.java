
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class E_StateDSearch extends JFrame implements ActionListener{
	private int count = 0;
	private JLabel sumLabel = new JLabel();
	
	private JTable table_1;
	private JTextField textField;
	
	private JComboBox<String> comboBox;
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


	public E_StateDSearch(String manuId,int flag) {
		E_StateDSearch.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 539);
		if(flag == 0) {
			this.setTitle("门状态搜索系统");
		}
		else {
			this.setTitle("门状态搜索系统");
		}
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		idLabel = new JLabel("状态");
		panel.add(idLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"0 (开启)", "1 (关闭)"}));
		panel.add(comboBox);
		
		
		searchButton = new JButton("搜索");
		panel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					model.setRowCount(0);
					count = 0;
					try {                                                                              
						Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
						Statement stmt = conn.createStatement();
						
				        ResultSet rs = null;
				        if(flag ==0) {
				        	rs = stmt.executeQuery("SELECT * FROM door WHERE do_状态 = " + (int)comboBox.getSelectedIndex());
				        }
				        else {
				        	rs = stmt.executeQuery("SELECT * FROM door,device WHERE do_状态 = " + (int)comboBox.getSelectedIndex() + " AND d_手动号码 = \"" + manuId +  "\" AND do_设备号 = d_设备号; ");
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
		
		returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				E_StateDSearch.this.setVisible(false);
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
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table_1 = new JTable(model);
		
		try {
			count = 0;
			Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
	        if(flag ==0) {
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
			        if(flag ==0) {
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
		
	}

}
