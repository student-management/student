package Daoimpl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import util.student;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import Dao.DBconnector;
import Daoimpl1.Student;

public class DeleteStudent extends JFrame implements ActionListener{

	private static final long serialVersionUID=1L;
	
	JLabel JL=new JLabel("删除学生基本信息",JLabel.CENTER);
	JLabel JLNumber=new JLabel("学号:");
	JTextField JTNumber=new JTextField();
	JLabel	JLName=new JLabel("姓名:");
	JTextField JTName=new JTextField();
	
	JButton JB1=new JButton("删除");
	JButton JB2=new JButton("重置");
	JButton JB3=new JButton("退出");
	
	String sql="";
	//定义构造函数
	public DeleteStudent()
	{
		//设置窗口
		this.setTitle("删除学生信息");
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		
		JL.setForeground(Color.red);
		JL.setFont(new java.awt.Font("宋体",Font.PLAIN,19));
		JL.setBounds(150,30,200,40);
		this.add(JL);
		
		JLNumber.setBounds(100,120,100,20);
		this.add(JLNumber);
		JTNumber.setBounds(200,120,80,20);
		this.add(JTNumber);
		
		JLName.setBounds(100,160,100,20);
		this.add(JLName);
		JTName.setBounds(200,160,80,20);
		this.add(JTName);
		
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
			student stu=new student();
			stu.setSid(Integer.parseInt(JTNumber.getText()));//获得id
			sql="select * from students where id="+stu.getSid();
			DBconnector conn=new DBconnector();
			Connection con=conn.getConnection();
			try {
				Statement stmt=(Statement) con.createStatement();
				ResultSet rs=(ResultSet) stmt.executeQuery(sql);
				if(rs.next())//如果查询结果存在
				{
					sql="delete from students where id="+stu.getSid();
					int n=stmt.executeUpdate(sql);//更新
					if(n>0)JOptionPane.showMessageDialog(null, "删除成功");
					else JOptionPane.showMessageDialog(null, "删除失败");
				}
				else
				{
					rs.close();
					JOptionPane.showMessageDialog(null, "此用户不存在");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==JB2)
		{
			JTNumber.setText(null);
			JTName.setText(null);
		}
		else
		{
			this.setVisible(false);//隐藏当前窗口
		}
		
	}
	public static void main(String[] args) {
		new DeleteStudent();
	}
}
