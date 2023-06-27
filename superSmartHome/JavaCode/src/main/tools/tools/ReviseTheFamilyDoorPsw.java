import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ReviseTheFamilyDoorPsw {
	public ReviseTheFamilyDoorPsw(JTable table,DefaultTableModel model) throws SQLException {
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
			String familyId = (String) model.getValueAt(numrow,0);
			//model.removeRow(numrow);
			
			String newPsw = RandomUtil.generateStr(5);

			String sql = "update family set f_doorPassword = '" + ParseMD5.parseStrToMd5L32(newPsw) +"' where f_familyId = '" + familyId + "' and f_isDeleted = 0;";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Password revise successfully!\nThe new door password is :" + newPsw, "Completion",JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
