
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

public class D_Mlight extends JFrame implements ActionListener{
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
	
	private Object[] columnNames = new Object[]{"设备号", "时间", "灯光"};
	private Object[][] rowData = new Object[0][];
	private DefaultTableModel model;

	public D_Mlight(String manuId) {
		D_Mlight.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 537);
		this.setTitle("灯光数据查看");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel = new JPanel();
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"设备号", "时间", "灯光"}));
		panel.add(comboBox);
		
		searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index == 0) {
					D_Mlight.this.setVisible(false);
					new E_IDLSearch(manuId,1);
					
				}
				else if(index == 1) {
					D_Mlight.this.setVisible(false);
					new E_TimeLSearch(manuId,1);
				}
				else {
					D_Mlight.this.setVisible(false);
					new E_LightLSearch(manuId,1);
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
								new Q_DeleteTheLightdata(table_1, model);
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
				D_Mlight.this.setVisible(false);
				new C_MData(manuId);
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
	        ResultSet rs = stmt.executeQuery("SELECT * FROM light,device where d_手动号码 = \"" + manuId + "\" and l_设备号 = d_设备号;");
	        
	        while(rs.next()){
	        	if(rs.getInt("l_isDeleted")==0) {
	        		String[] newRow = {rs.getString("l_设备号"), rs.getString("l_时间"), rs.getString("l_灯光")};
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
					ResultSet rs = stmt.executeQuery("SELECT * FROM light,device where d_手动号码 = \"" + manuId + "\" and l_设备号 = d_设备号;");

			        while(rs.next()){
			        	if(rs.getInt("l_isDeleted")==0) {
			        		String[] newRow = {rs.getString("l_设备号"), rs.getString("l_时间"), rs.getString("l_灯光")};
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
		if(e.getSource() == deleteButton) {
			
		}
		
	}

}
