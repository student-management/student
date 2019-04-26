package Daoimpl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import util.Grade;
import util.student;
import Dao.DBconnector;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class UpdateGrade extends JFrame implements ActionListener{

	private static final long SerialVersionUID =1L; 
	
	JLabel JL=new JLabel("修改学生成绩",JLabel.CENTER);//创建主标签
	JLabel JLNumber= new JLabel("该学生学号:");//学号标签组件
	JTextField JTNumber= new JTextField();//学号文本框组件
	
	JLabel JLName=new JLabel("姓名:");//姓名标签组件
	JTextField JTName=new JTextField();//姓名文本框组件

	JLabel JLMath=new JLabel("数学:");
	JTextField JTMath=new JTextField();
	
	JLabel JLChinese=new JLabel("语文:");
	JTextField JTChinese=new JTextField();
	
	JButton JB1=new JButton("修改");
	JButton JB2=new JButton("重置");
	JButton JB3=new JButton("退出");
	
	String sql="";
	public UpdateGrade()
	{
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("修改学生信息");
		this.setLayout(null);//设置窗口布局管理器为null,用setBounds()方法设置组件位置
		JL.setFont(new java.awt.Font("宋体",Font.PLAIN,20));
		JL.setBounds(150,30,200,40);
		this.add(JL);
		
		JLNumber.setBounds(100,80,100,20);
		this.add(JLNumber);
		JTNumber.setBounds(200,80,80,20);
		this.add(JTNumber);
		
		JLName.setBounds(100,120,100,20);
		this.add(JLName);
		JTName.setBounds(200,120,80,20);
		this.add(JTName);
		
		
		JLMath.setBounds(100,200,100,20);
		this.add(JLMath);
		JTMath.setBounds(200,200,80,20);
		this.add(JTMath);
		
		
		JLChinese.setBounds(100,280,100,20);
		this.add(JLChinese);
		JTChinese.setBounds(200,280,80,20);
		this.add(JTChinese);
		
		JB1.setBounds(80,320,90,20);
		this.add(JB1);
		JB1.addActionListener(this);
		JB2.setBounds(190,320,90,20);
		this.add(JB2);
		JB2.addActionListener(this);
		JB3.setBounds(300,320,90,20);
		this.add(JB3);
		JB3.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB1)
		{
			//将文本框内容存入对象
			Grade stu=new Grade();
			stu.setSid(Integer.parseInt(JTNumber.getText()));
			stu.setSname(JTName.getText());
			stu.setSmath(Integer.parseInt(JTMath.getText()));
			stu.setSchinese(Integer.parseInt(JTChinese.getText()));
		

			sql="select * from grade where sid="+stu.getSid();
			//连接数据库
			DBconnector conn=new DBconnector();
			Connection con=conn.getConnection();
			try {
				Statement stmt=(Statement) con.createStatement();
				ResultSet rs=(ResultSet) stmt.executeQuery(sql);
				if(rs.next())
				{
					sql="update grade set math="+stu.getSmath()+", chinese="+stu.getSchinese()
							+" where sid="+stu.getSid();
					int n=stmt.executeUpdate(sql);
					if(n>0)
					{
						JOptionPane.showMessageDialog(null,"更新成功");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "更新失败");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "此用户不存在");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==JB2)
		{
			JTNumber.setText(null);
			JTMath.setText(null);
			JTName.setText(null);
			JTChinese.setText(null);
		}
		else if(e.getSource()==JB3)
		{
			this.setVisible(false);
		}
		

	}
	
	public static void main(String[] args) {
		new UpdateGrade();
	}
}
