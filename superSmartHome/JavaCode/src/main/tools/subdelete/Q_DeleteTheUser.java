import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Q_DeleteTheUser {
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
			String phone = (String) model.getValueAt(numrow,0);
			model.removeRow(numrow);

			String sql = "update user set u_isDeleted = 1 where u_手机号码 = '" + phone + "';";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功删除!", "完成",JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
