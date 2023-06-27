import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Q_DeleteTheDeviceClass {
	public Q_DeleteTheDeviceClass(JTable table,DefaultTableModel model) throws SQLException
	{
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
			String classId = (String) model.getValueAt(numrow,0);

			model.removeRow(numrow);
			String sql = "update class set c_isDeleted = 1 where c_类 = '" + classId + "';";
			String sql1 = "update device set d_isDeleted = 1 where d_类 = '" + classId + "';";
			String sql2 = "update temphumi set th_isDeleted = 1 where th_设备号 in (select d_设备号 from device where d_类 = "
					+ "'" + classId + "');";
			String sql3 = "update light set l_isDeleted = 1 where l_设备号 in (select d_设备号 from device where d_类 = "
					+ "'" + classId + "');";
			String sql4 = "update door set do_isDeleted = 1 where do_设备号 in (select d_设备号 from device where d_类 = "
					+ "'" + classId + "')";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql4);
			JOptionPane.showMessageDialog(null, "成功删除!", "完成",JOptionPane.PLAIN_MESSAGE);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		
	}

}
