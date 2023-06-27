import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Delete{

		
	public static void delete(String table,String pk,String preTable) throws SQLException{
	    String sql1,sql2,sql3;
	    boolean match = false;
	    
		String str = JOptionPane.showInputDialog(null," Please input the "+pk+"��\n","删除",JOptionPane.PLAIN_MESSAGE);
		
		Connection con=DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
		Statement stmt=con.createStatement();
		
		String sql = "select " + pk + " from " + table;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			if(rs.getString(pk).equals(str)) {
				match = true;
				break;
			}
		}

		if(str.equals("")) {
			match = true;
		}
		
		if(!match) {
			sql="update "+table+" set "+preTable+"_isDeleted = 1 where "+pk+" = '"+str+"';";
//			str = "'"+str+"'";
//			sql="update ? set ?_isDeleted = 1 where ? = \'?\'";
//			PreparedStatement pstmt=con.prepareStatement(sql);
//			pstmt.setString(1,table);
//			pstmt.setString(2,preTable);
//			pstmt.setString(3,pk);
//			pstmt.setString(4,str);
			stmt.executeUpdate(sql);
			
			if(table.equals("FAMILY")){
				sql1 = "update userfamily set isDeleted = 1 where uf_familyId = " + str;
				stmt.executeUpdate(sql1);
			}
			else if(table.equals("CLASS")){
				sql1 = "update device set d_isDeleted = 1 where class_c_classId= " + str;
				stmt.executeUpdate(sql1);
			}
			else if(table.equals("DEVICE")){
	
				sql1 = "update temphumi set th_isDeleted = 1 where exists(select * from device where d_设备号= " + str +")";
				sql2 = "update light set l_isDeleted = 1 where exists(select * from device where d_设备号= " + str + ")";
				sql3 = "update door set do_isDeleted = 1 where exists(select * from device where d_设备号= " + str + ")";
				stmt.executeUpdate(sql1);
				stmt.executeUpdate(sql2);
				stmt.executeUpdate(sql3);
	        }
			
			JOptionPane.showMessageDialog(null, "成功删除!", "完成",JOptionPane.PLAIN_MESSAGE);
			
		}
		else {
			JOptionPane.showMessageDialog(null, "错误. ", "失败", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
}