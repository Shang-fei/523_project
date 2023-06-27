
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class C_MDetailSearch extends JFrame implements ActionListener{
	

	public C_MDetailSearch(String manuId) {
		C_MDetailSearch.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 470);
		this.setTitle("数据窗口");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel textLabel = new JLabel("你好，技术人员!");
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setFont(new Font("宋体", Font.BOLD, 25));
		getContentPane().add(textLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton tempHumiButton = new JButton("温湿");
		tempHumiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_MDetailSearch.this.setVisible(false);
				new D_Mtemphumi(manuId);
			}
		});
		tempHumiButton.setBounds(120, 20, 200, 30);
		panel_1.add(tempHumiButton);
		
		JButton lightButton = new JButton("灯光");
		lightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_MDetailSearch.this.setVisible(false);
				new D_Mlight(manuId);
			}
		});
		lightButton.setBounds(120, 60, 200, 30);
		panel_1.add(lightButton);
		
		JButton doorButton = new JButton("门");
		doorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_MDetailSearch.this.setVisible(false);
				new D_Mdoor(manuId);
			}
		});
		doorButton.setBounds(120, 100, 200, 30);
		panel_1.add(doorButton);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("宋体", Font.BOLD, 12));
		exitButton.setBounds(125, 80, 95, 25);
		panel.add(exitButton);
		
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				C_MDetailSearch.this.setVisible(false);
				new B_Manu(manuId);
			}
		});
		returnButton.setFont(new Font("宋体", Font.BOLD, 12));
		returnButton.setBounds(230, 80, 95, 25);
		panel.add(returnButton);
		
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
