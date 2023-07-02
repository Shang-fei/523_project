import java.awt.EventQueue;

public class Main {

	static String driver = "com.mysql.cj.jdbc.Driver";

	public static final String URL = "jdbc:mysql://rm-cn-zxu3aaxo4000tono.rwlb.rds.aliyuncs.com/shujuku2";
	public static final String USER = "wendetaer";
	public static final String PASSWORD = "123456789Qq!!";
	
	public static void main(String[] args) {
		
		try {
        	Class.forName(driver);
    		//Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(java.lang.ClassNotFoundException e) {
			System.out.println("Sorry, driver is not available. ");
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new A_Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
