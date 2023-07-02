
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcDemo {

	static String driver = "com.mysql.jdbc.Driver";
	
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/shujuku2";
    public static final String USER = "wendetaer";
    public static final String PASSWORD = "123456qq";
    
	public static void main(String[] args) throws Exception{
		
		try {
        	Class.forName(driver);
		} catch(java.lang.ClassNotFoundException e) {
			System.out.println("�޷���������.");
		}
        
		try {                                                                              
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT f_familyId, f_province FROM family");
	        
	        while(rs.next()){
	            System.out.println(rs.getString("f_familyId")+" from "+rs.getString("f_province"));
	        }          
		} catch(Exception e) {
			System.out.println("����ʧ��:" + e.getMessage());
		}
	}
}

