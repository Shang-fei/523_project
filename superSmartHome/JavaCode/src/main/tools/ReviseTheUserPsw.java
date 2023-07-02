import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ReviseTheUserPsw {
	public ReviseTheUserPsw(JTable table,DefaultTableModel model) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Main.URL, Main.USER, Main.PASSWORD);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement stmt;
		try {
			stmt = conn.createStatement();
			int numrow=table.getSelectedRow();
			String phone = (String) model.getValueAt(numrow,0);
			//model.removeRow(numrow);

			String newPsw = RandomUtil.generateStr(5);
			String sql = "update user set u_password = '" + ParseMD5.parseStrToMd5L32(newPsw) +"' where u_phone = '" + phone + "' and u_isDeleted = 0;";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Password revise successfully!\nThe new password is :" + newPsw, "Completion",JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
