
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

public class D_TempHumi extends JFrame implements ActionListener{
	private int count = 0;
	private JLabel sumLabel = new JLabel();
	
	private JPanel panel;
	private JComboBox<String> comboBox;
	private JButton searchButton;
	private JButton showAllButton;
	private JButton deleteButton;
	private JButton returnButton;
	private JButton exitButton;
	private JTable table_1;
	private JScrollPane scrollPane; 
	
	private Object[] columnNames = new Object[]{"设备号", "时间", "温度", "湿度"};
	private Object[][] rowData = new Object[0][];
	private DefaultTableModel model;
	
	public D_TempHumi() {
		D_TempHumi.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 515);
		this.setTitle("温湿数据管理系统");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"设备号", "时间", "温度", "湿度"}));
		panel.add(comboBox);
		
		searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index == 0) {
					D_TempHumi.this.setVisible(false);
					new E_IDTHSearch(null,0);
					
				}
				else if(index == 1) {
					D_TempHumi.this.setVisible(false);
					new E_TimeTHSearch(null,0);
				}
				else if(index == 2){
					D_TempHumi.this.setVisible(false);
					new E_TempTHSearch(null,0);
				}
				else {
					D_TempHumi.this.setVisible(false);
					new E_HumiTHSearch(null,0);
				}
			}
		});
		panel.add(searchButton);
		
		deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							if(table_1.getSelectedRow()==-1) {
								JOptionPane.showMessageDialog(getContentPane(), "请选中想要删除的数据后点击删除按钮. ", "warning", JOptionPane.WARNING_MESSAGE);
							} else {
								new Q_DeleteTheTHdata(table_1, model);
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
				D_TempHumi.this.setVisible(false);
				new C_DataManage();
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
			Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM temphumi");
	        
	        while(rs.next()){
	        	if(rs.getInt("th_isDeleted")==0) {
	        		String[] newRow = {rs.getString("th_设备号"), rs.getString("th_时间"), rs.getString("th_温度"), rs.getString("th_湿度")};
	                model.addRow(newRow);
	        		count++;
	        	} 
	        } 
	        sumLabel.setText("总共 " + count + " 条数据");
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
					 ResultSet rs = stmt.executeQuery("SELECT * FROM temphumi");

			         while(rs.next()){
			        	if(rs.getInt("th_isDeleted")==0) {
			        		String[] newRow = {rs.getString("th_设备号"), rs.getString("th_时间"), rs.getString("th_温度"), rs.getString("th_湿度")};
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
				// TODO Auto-generated catch block

	}
	
}
