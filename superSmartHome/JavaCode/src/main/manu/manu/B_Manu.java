import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class B_Manu extends JFrame implements ActionListener{
	
	private static String manuId;
	
	public B_Manu(String manuId) {
		
		this.manuId = manuId;
		
		B_Manu.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		setTitle("后台管理系统");
		getContentPane().setLayout(null);
		
		JLabel manuLabel = new JLabel("你好，技术人员");
		manuLabel.setFont(new Font("宋体", Font.BOLD, 28));
		manuLabel.setBounds(74, 10, 309, 60);
		getContentPane().add(manuLabel);
		
		JButton deviceButton = new JButton("管理设备");
		
		deviceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B_Manu.this.setVisible(false);
				new C_MDeviceManage(manuId);
			}
		});
		deviceButton.setFont(new Font("宋体", Font.PLAIN, 20));
		deviceButton.setBounds(105, 80, 207, 40);
		getContentPane().add(deviceButton);
		
		JButton dataButton = new JButton("搜索数据细节");
		dataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B_Manu.this.setVisible(false);
				new C_MData(manuId);
			}
		});
		dataButton.setFont(new Font("宋体", Font.PLAIN, 20));
		dataButton.setBounds(105, 154, 207, 40);
		getContentPane().add(dataButton);
		
		JButton exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("宋体", Font.BOLD, 15));
		exitButton.setBounds(85, 219, 120, 25);
		getContentPane().add(exitButton);

		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B_Manu.this.setVisible(false);
				new A_Login();
			}
		});
		returnButton.setFont(new Font("宋体", Font.BOLD, 15));
		returnButton.setBounds(215, 219, 120, 25);
		getContentPane().add(returnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public B_Manu() {
		this(manuId);
	}
}
