import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class P_AddADevice extends JFrame implements ActionListener{


	private JPanel contentPane;
	private JTextField textField3;
	private JTextField textField2;
	private JTextField textField1;
	private JPanel panel3;
	private JPanel panel2;
	private JPanel panel1;
	private JPanel panel4;
	private JPanel panel5;
	private JLabel label4;
	private JPanel panel;
	private JButton newButton1;
	private JButton newButton2;
	private JButton newButton3;
	private JPanel panel2_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_3;
//	
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					P_AddADevice frame = new P_AddADevice();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public P_AddADevice() {		  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		label4 = new JLabel("请添加一个设备");
		label4.setPreferredSize(new Dimension(60, 40));
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setHorizontalTextPosition(SwingConstants.CENTER);
		label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		contentPane.add(label4);
		
		panel5 = new JPanel();
		panel5.setMinimumSize(new Dimension(10, 70));
		contentPane.add(panel5);
		panel5.setLayout(new GridLayout(3, 1, 0, 2));
		
		panel1 = new JPanel();
		panel5.add(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JLabel label1 = new JLabel("设备名:");
		panel1.add(label1, BorderLayout.WEST);
		
		textField1 = new JTextField();
		panel1.add(textField1, BorderLayout.EAST);
		textField1.setColumns(30);
		
		panel2 = new JPanel();
		panel5.add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JLabel label2 = new JLabel("家庭号:");
		panel2.add(label2, BorderLayout.WEST);
		
		textField2 = new JTextField();
		panel2.add(textField2, BorderLayout.EAST);
		textField2.setColumns(30);
		
		panel3 = new JPanel();
		panel5.add(panel3);
		panel3.setLayout(new BorderLayout(0, 0));
		
		JLabel label3 = new JLabel("描述细节:");
		panel3.add(label3, BorderLayout.WEST);
		
		textField3 = new JTextField();
		panel3.add(textField3, BorderLayout.EAST);
		textField3.setColumns(30);
		
		panel4 = new JPanel();
		contentPane.add(panel4);
		panel4.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel4.add(panel, BorderLayout.SOUTH);
		
		newButton1 = new JButton("清除");
		newButton1.setActionCommand("Clear");
		panel.add(newButton1);
		
		newButton2 = new JButton("返回");
		panel.add(newButton2);
		
		newButton3 = new JButton("确认");
		panel.add(newButton3);
		
		panel2_1 = new JPanel();
		panel4.add(panel2_1, BorderLayout.NORTH);
		
      
        
        try {
	    Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);

  		String[] classes = new String[100];
  		String[] manu = new String[100];
  		int i = 0;
  		
  		Statement stmt = conn.createStatement();
  		ResultSet rs = stmt.executeQuery("SELECT c_类, c_类名 FROM class");
  		  while(rs.next()){
  			  classes[i] = rs.getString("c_类") + " " + rs.getString("c_类名");
  			  i++;
  		  }
  		i = 0;
  		rs = stmt.executeQuery("SELECT m_manuId, m_manuName FROM manu");
  		  while(rs.next()){
  			  manu[i] = rs.getString("m_manuId") + " " + rs.getString("m_manuName");
  			  i++;
  		  }
  		  
  		
  		comboBox_2 = new JComboBox<String>(classes);
  		comboBox_2.setPreferredSize(new Dimension(130, 50));
  		comboBox_2.setBorder(new TitledBorder(null, "Class:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
  		panel2_1.add(comboBox_2);
  		
  		comboBox_3 = new JComboBox<String>(manu);
  		comboBox_3.setPreferredSize(new Dimension(130, 50));
  		comboBox_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "manufacturer:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
  		panel2_1.add(comboBox_3);

  		
        } catch (Exception eConn) {
          System.err.println("Got an exception!");
          System.err.println(eConn.getMessage());
        }
		
        // listening register 
        newButton1.addActionListener(this);
        newButton2.addActionListener(this);
        newButton3.addActionListener(this);
	}
	
	public P_AddADevice(String manuId) {		  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		label4 = new JLabel("请添加一个设备");
		label4.setPreferredSize(new Dimension(60, 40));
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setHorizontalTextPosition(SwingConstants.CENTER);
		label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		contentPane.add(label4);
		
		panel5 = new JPanel();
		panel5.setMinimumSize(new Dimension(10, 70));
		contentPane.add(panel5);
		panel5.setLayout(new GridLayout(3, 1, 0, 2));
		
		panel1 = new JPanel();
		panel5.add(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JLabel label1 = new JLabel("设备名:");
		panel1.add(label1, BorderLayout.WEST);
		
		textField1 = new JTextField();
		panel1.add(textField1, BorderLayout.EAST);
		textField1.setColumns(30);
		
		panel2 = new JPanel();
		panel5.add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JLabel label2 = new JLabel("家庭号:");
		panel2.add(label2, BorderLayout.WEST);
		
		textField2 = new JTextField();
		panel2.add(textField2, BorderLayout.EAST);
		textField2.setColumns(30);
		
		panel3 = new JPanel();
		panel5.add(panel3);
		panel3.setLayout(new BorderLayout(0, 0));
		
		JLabel label3 = new JLabel("描述细节:");
		panel3.add(label3, BorderLayout.WEST);
		
		textField3 = new JTextField();
		panel3.add(textField3, BorderLayout.EAST);
		textField3.setColumns(30);
		
		panel4 = new JPanel();
		contentPane.add(panel4);
		panel4.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel4.add(panel, BorderLayout.SOUTH);
		
		newButton1 = new JButton("清除");
		newButton1.setActionCommand("Clear");
		panel.add(newButton1);
		
		newButton2 = new JButton("返回");
		panel.add(newButton2);
		
		newButton3 = new JButton("确认");
		panel.add(newButton3);
		
		panel2_1 = new JPanel();
		panel4.add(panel2_1, BorderLayout.NORTH);
		
      
        
        try {
	    Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD); 

  		String[] classes = new String[100];
  		int i = 0;
  		
  		Statement stmt = conn.createStatement();
  		ResultSet rs = stmt.executeQuery("SELECT c_类, c_类名 FROM class");
  		  while(rs.next()){
  			  classes[i] = rs.getString("c_类") + " " + rs.getString("c_类名");
  			  i++;
  		  }
//  		i = 0;
//  		rs = stmt.executeQuery("SELECT m_manuId, m_manuName FROM manu");
//  		  while(rs.next()){
//  			  manu[i] = rs.getString("m_manuId") + " " + rs.getString("m_manuName");
//  			  i++;
//  		  }
  		  
  		
  		comboBox_2 = new JComboBox<String>(classes);
  		comboBox_2.setPreferredSize(new Dimension(130, 50));
  		comboBox_2.setBorder(new TitledBorder(null, "类:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
  		panel2_1.add(comboBox_2);
  		
        } catch (Exception eConn) {
          System.err.println("Got an exception!");
          System.err.println(eConn.getMessage());
        }
		
        // listening register 
        newButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField3.setText(""); 
	            textField1.setText(""); 
	            textField2.setText(""); 
	            
	            comboBox_2.setSelectedIndex(0);
			}
		});
        newButton2.addActionListener(this);
        newButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            // create a mysql database connection
			            //Class.forName(DRIVER_CLASS);
			            Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);        
			            Statement stmt = conn.createStatement();
			  		  ResultSet rs = stmt.executeQuery("SELECT f_家庭号 FROM family");
			  		  int flag = 0;
			  		  while(rs.next()){
			  			  if(rs.getString("f_家庭号").equals(textField2.getText())) {
			  				  flag = 1;
			  			  }
			  		  }
			  		  if(flag == 0) {
			  			  JOptionPane.showMessageDialog(null, "对不起，这不是一个有效的家庭，请换另一个!");
			  		  }
			                          
			  		  rs = stmt.executeQuery("SELECT d_设备号 FROM device ORDER BY d_设备号");
			  		  String st = null;
			  		  String id = null;
			  		  while(rs.next()){
			  			  st = rs.getString("d_设备号");
			  		  }
			  		  
			  		  if(st == null) {
			  			  id = "d000";
			  		  } else {
			  			  StringBuffer string1=new StringBuffer(st.substring(1));//get the 3 numbers after 'd'
			  			  string1.insert(0,"1");
			  			  String sb = string1.toString();
			  			  Integer num = Integer.parseInt(sb);
			  			  num++;
			  			  StringBuffer string2=new StringBuffer(num.toString());
			  			  string2.replace(0, 1, "d");
			  			  id = string2.toString();
			  		  }
			          
			  		  String cId = ((String)comboBox_2.getSelectedItem()).substring(0, 5);
			  		  
			            // the mysql insert statement
			          String query = " insert into device (d_设备号, d_设备名, d_类, d_规格, d_手动号码, d_家庭号, d_isDeleted)"
				                + " values (?, ?, ?, ?, ?, ?, ?)";

				              // create the mysql insert preparedstatement
				              PreparedStatement preparedStmt = conn.prepareStatement(query);
				              preparedStmt.setString (1, id);
				              preparedStmt.setString (2, textField1.getText());
				              preparedStmt.setString (3, cId);
				              preparedStmt.setString (4, textField3.getText());
				              preparedStmt.setString (5, manuId);
				              preparedStmt.setString(6, textField2.getText());
				              preparedStmt.setInt(7, 0);

				              // execute the preparedstatement
				              preparedStmt.execute();
			            
			            conn.close();
			            JOptionPane.showMessageDialog(null, "You have successfully add a device! \nYour ID is " + id + ". ");
			            setVisible(false);
			          }
			  		  catch (Exception eEnterClass)
			            {
			              System.err.println("Got an exception!");
			              System.err.println(eEnterClass.getMessage());
			            }
			}
		});
        
       
	}
	
	public void actionPerformed(ActionEvent e) {
        //When the JButton "Clear" is clicked, the program will erase the entered information
        if (e.getActionCommand().equals("清除")) {
            textField3.setText(""); 
            textField1.setText(""); 
            textField2.setText(""); 
            
            comboBox_2.setSelectedIndex(0);
            comboBox_3.setSelectedIndex(0);
        } 
        //When the JButton "Return" is clicked, the program will exit
        else if (e.getActionCommand().equals("返回")) {
            this.setVisible(false);
        }       
        //When the JButton "Confirm" is clicked, the program will go to another frame
        else if (e.getActionCommand().equals("确认")) {
        	
            try {

              Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);        
              Statement stmt = conn.createStatement();
    		  ResultSet rs = stmt.executeQuery("SELECT f_家庭号 FROM family");
    		  int flag = 0;
    		  while(rs.next()){
    			  if(rs.getString("f_家庭号").equals(textField2.getText())) {
    				  flag = 1;
    			  }
    		  }
    		  if(flag == 0) {
    			  JOptionPane.showMessageDialog(this, "对不起，这不是一个有效的家庭, 请更换另一个!");
    		  }
                            
    		  rs = stmt.executeQuery("SELECT d_设备号 FROM device ORDER BY d_设备号");
    		  String st = null;
    		  String id = null;
    		  while(rs.next()){
    			  st = rs.getString("d_设备号");
    		  }
    		  
    		  if(st == null) {
    			  id = "d000";
    		  } else {
    			  StringBuffer string1=new StringBuffer(st.substring(1));//get the 3 numbers after 'd'
    			  string1.insert(0,"1");
    			  String sb = string1.toString();
    			  Integer num = Integer.parseInt(sb);
    			  num++;
    			  StringBuffer string2=new StringBuffer(num.toString());
    			  string2.replace(0, 1, "d");
    			  id = string2.toString();
    		  }
            
    		  String cId = ((String)comboBox_2.getSelectedItem()).substring(0, 5);
    		  String mId = ((String)comboBox_3.getSelectedItem()).substring(0, 5);
    		  
              // the mysql insert statement
            String query = " insert into device (d_设备号, d_设备名, d_类, d_规格, d_手动号码, d_家庭号, d_isDeleted)"
	                + " values (?, ?, ?, ?, ?, ?, ?)";

	              // create the mysql insert preparedstatement
	              PreparedStatement preparedStmt = conn.prepareStatement(query);
	              preparedStmt.setString (1, id);
	              preparedStmt.setString (2, textField1.getText());
	              preparedStmt.setString (3, cId);
	              preparedStmt.setString (4, textField3.getText());
	              preparedStmt.setString (5, mId);
	              preparedStmt.setString(6, textField2.getText());
	              preparedStmt.setInt(7, 0);

	              // execute the preparedstatement
	              preparedStmt.execute();
              
              conn.close();
              JOptionPane.showMessageDialog(this, "你成功地增加了一个设备! \n你的设备号是 " + id + ". ");
              this.setVisible(false);
            }
    		  catch (Exception eEnterClass)
              {
                System.err.println("Got an exception!");
                System.err.println(eEnterClass.getMessage());
              }
        }
	}

}
