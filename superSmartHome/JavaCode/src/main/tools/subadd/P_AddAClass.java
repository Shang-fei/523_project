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
import java.awt.FlowLayout;

/**
 * 
 * @author XinyaoYu
 *
 */
public class P_AddAClass extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 955952428993693629L;
	private JPanel contentPane;
	private JTextField textField1;
	private JPanel panel1;
	private JPanel panel4;
	private JPanel panel5;
	private JLabel label4;
	private JPanel panel;
	private JButton newButton1;
	private JButton newButton2;
	private JButton newButton3;

		/**
		 * Create the frame.
		 */
		public P_AddAClass() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new GridLayout(3, 1, 0, 0));
			
			label4 = new JLabel("请添加一个类");
			label4.setPreferredSize(new Dimension(60, 40));
			label4.setHorizontalAlignment(SwingConstants.CENTER);
			label4.setHorizontalTextPosition(SwingConstants.CENTER);
			label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
			contentPane.add(label4);
			
			panel5 = new JPanel();
			panel5.setMinimumSize(new Dimension(10, 70));
			contentPane.add(panel5);
			panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			panel1 = new JPanel();
			panel5.add(panel1);
			panel1.setLayout(new BorderLayout(0, 0));
			
			JLabel label1 = new JLabel("新类的名字是: ");
			panel1.add(label1, BorderLayout.WEST);
			
			textField1 = new JTextField();
			panel1.add(textField1, BorderLayout.EAST);
			textField1.setColumns(25);
			
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

			// listening register 
	        newButton1.addActionListener(this);
	        newButton2.addActionListener(this);
	        newButton3.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
		    //When the JButton "Clear" is clicked, the program will erase the entered information
		    if (e.getActionCommand().equals("清除")) {
		        textField1.setText(""); 
		    } 
		    //When the JButton "Return" is clicked, the program will exit
		    if (e.getActionCommand().equals("返回")) {
	        	System.exit(EXIT_ON_CLOSE);
		    }       
		    //When the JButton "Confirm" is clicked, the program will go to another frame
		    if (e.getActionCommand().equals("确认")) {
	            try
	            {
	              // create a mysql database connection
	              Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
	              
	              
	              
	              Statement stmt = conn.createStatement();
	    		  ResultSet rs = stmt.executeQuery("SELECT c_类名, c_类 FROM class");
	    		  String st = null; 
	    		  String id = null;
	    		  while(rs.next()){
	    			  if(rs.getString("c_类名").equals(textField1.getText())) {
	    				  JOptionPane.showMessageDialog(this, "抱歉，这个类已存在，请换另外一个!");
	    			  }
	    			  st = rs.getString("c_类");
	    		  }
	    		  if(st == null) {
	    			  id = "c000";
	    		  }
	    		  else {
	    			StringBuffer string1=new StringBuffer(st.substring(1));
	    			string1.insert(0,"1");
	    			String sb = string1.toString();
	  	            Integer num = Integer.parseInt(sb);
	  	            num++;
	  	            StringBuffer string2=new StringBuffer(num.toString());
	  	            string2.replace(0, 1, "c");
	  	            id = string2.toString();
	    		  }
	            
	              
	            String query = " insert into class (c_类, c_类名, c_isDeleted)"
		                + " values (?, ?, ?)";

		              // create the mysql insert preparedstatement
		              PreparedStatement preparedStmt = conn.prepareStatement(query);
		              preparedStmt.setString (1, id);
		              preparedStmt.setString (2, textField1.getText());
		              preparedStmt.setInt(3, 0);

		              // execute the preparedstatement
		              preparedStmt.execute();
		              conn.close();
		              JOptionPane.showMessageDialog(this, "你成功地创建了一个类! \n你的类号是 " + id + ". ");
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
