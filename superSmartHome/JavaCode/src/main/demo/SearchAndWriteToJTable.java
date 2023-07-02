import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author Botao
 * used by class C_UserManage, C_FamilyManage, C_DeviceManage, C_DeviceClassManage and
 * E_IDTHSearch, E_IDLSearch, E_IDDSearch
 */

public class SearchAndWriteToJTable {
	private int count = 0;
	
	public static void search(DefaultTableModel model, String keyWord, String searchWhat, String tableName) {
		if (tableName == "user") {
			try {                                                                              
				Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user");
				
				while(rs.next()){
		            if(rs.getString("u_" + searchWhat).equals(keyWord) && rs.getInt("u_isDeleted")==0) {
		            	String[] newRow = {rs.getString("u_phone"), rs.getString("u_avatar")};
			            model.addRow(newRow);
		            }
		        }
			} catch(Exception e1) {
				System.out.println("Connection fails: " + e1.getMessage());
			}
		} else if(tableName == "family") {
			try {                                                                              
				Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM family");
				
				while(rs.next()){
		            if(rs.getString("f_" + searchWhat).equals(keyWord) && rs.getInt("f_isDeleted") == 0) {
		            	String[] newRow = {rs.getString("f_familyId"), rs.getString("f_province")};
			            model.addRow(newRow);
		            }
		        }
			} catch(Exception e1) {
				System.out.println("Connection fails: " + e1.getMessage());
			}
		} else if(tableName == "device") {
			try {                                                                              
				Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM device");
				
				while(rs.next()){
		            if(rs.getString("d_" + searchWhat).equals(keyWord) && rs.getInt("d_isDeleted") == 0) {
		            	String[] newRow = {rs.getString("d_deviceId"), rs.getString("d_deviceName"), rs.getString("d_classId"), rs.getString("d_specification"), rs.getString("d_manuId"), rs.getString("d_familyId")};
			            model.addRow(newRow);
		            }
		        }
			} catch(Exception e1) {
				System.out.println("Connection fails: " + e1.getMessage());
			}
		} else if(tableName == "class") {
			try {                                                                              
				Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM class");
				
				while(rs.next()){
		            if(rs.getString("c_" + searchWhat).equals(keyWord) && rs.getInt("c_isDeleted") == 0) {
		            	String[] newRow = {rs.getString("c_classId"), rs.getString("c_className")};
			            model.addRow(newRow);
		            }
		        }
			} catch(Exception e1) {
				System.out.println("Connection fails: " + e1.getMessage());
			}
		} else if(tableName == "temphumi") {
			try {                                                                              
				Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM temphumi");
				
				while(rs.next()){
		            if(rs.getString("th_" + searchWhat).equals(keyWord) && rs.getInt("th_isDeleted") == 0) {
		            	String[] newRow = {rs.getString("th_deviceId"), rs.getString("th_time"), rs.getString("th_temperature"), rs.getString("th_humidity")};
			            model.addRow(newRow);
		            }
		        }
			} catch(Exception e1) {
				System.out.println("Connection fails: " + e1.getMessage());
			}
		} else if(tableName == "light") {
			try {                                                                              
				Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM light");
				
				while(rs.next()){
		            if(rs.getString("l_" + searchWhat).equals(keyWord) && rs.getInt("l_isDeleted") == 0) {
		            	String[] newRow = {rs.getString("l_deviceId"), rs.getString("l_time"), rs.getString("l_light")};
			            model.addRow(newRow);
		            }
		        }
			} catch(Exception e1) {
				System.out.println("Connection fails: " + e1.getMessage());
			}
		} else if(tableName == "door") {
			try {                                                                              
				Connection conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM door");
				
				while(rs.next()){
		            if(rs.getString("do_" + searchWhat).equals(keyWord) && rs.getInt("do_isDeleted") == 0) {
		            	String[] newRow = {rs.getString("do_deviceId"), rs.getString("do_time"), rs.getString("do_state")};
			            model.addRow(newRow);
		            }
		        }
			} catch(Exception e1) {
				System.out.println("Connection fails: " + e1.getMessage());
			}
		}
	}
}
