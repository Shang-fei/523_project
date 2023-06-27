import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Q_DeleteTheFamily {
	public static void delete(JTable table,DefaultTableModel model) throws SQLException {
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
			
			model.removeRow(numrow);
			String sql = "update family set f_isDeleted = 1 where f_家庭号 = '" + familyId + "';";
			String sql1 = "update userfamily set isDeleted = 1 where uf_familyId = '" + familyId + "';";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			JOptionPane.showMessageDialog(null, "成功删除!", "完成",JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
