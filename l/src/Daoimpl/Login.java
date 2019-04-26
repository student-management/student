package Daoimpl;
import Dao.DBconnector;
import Daoimpl1.*;
import Daoimpl.*;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.jdbc.Statement;

import Daoimpl.*;
public class Login extends Frame implements ActionListener {
	private static final long serialVersionUID=1L;
	JLabel JLUserName=new JLabel("用户名:");//创建标签对象
	JLabel JLPaw=new JLabel("密  码:");
	JLabel JL1=new JLabel("身  份:");
	JTextField JTUserName= new JTextField();//创建文本框对象
	JPasswordField JTpaw=new JPasswordField();//创建密码框对象
	JComboBox<String> JC=new JComboBox<String>();//创建组合框架对象
	
	
	
	JButton JB1=new JButton("登录");//创建按钮对象
	JButton JB2=new JButton("取消");

	
	public Login()//构造方法
	{
		//设置窗口标题
		this.setTitle("学生管理系统");
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		//学生姓名的组件,设置姓名：初始位置 （x，y，width，hight）
		JLUserName.setBounds(100,40,100,20);
		this.add(JLUserName);
		JTUserName.setBounds(200,40,80,20);
		this.add(JTUserName);
		//学生密码的组件
		JLPaw.setBounds(100,100,60,20);
		this.add(JLPaw);
		JTpaw.setBounds(200,100,80,20);
		this.add(JTpaw);
		
		JL1.setBounds(100,160,80,20);
		this.add(JL1);
		JC.setBounds(200,160,80,20);
		this.add(JC);
		JC.addItem(new String("学生"));
		JC.addItem(new String("教师"));
		
		//添加按钮
		JB1.setBounds(100,200,60,20);
		this.add(JB1);
		JB1.addActionListener(this);//为登录设置监听器
		JB2.setBounds(200,200,60,20);
		this.add(JB2);
		JB2.addActionListener(this);//为取消设置监听器
		
		this.setVisible(true);//设置窗口可见
		this.setSize(400,250);
		this.setLocationRelativeTo(null);//设置窗口在屏幕中间
		//以匿名内部类的方式为窗口设置监听器，用于关闭窗口
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB1)//登录按钮
		{
			String name=JTUserName.getText();//获取输入的名字
			String password=new String(JTpaw.getPassword());//获得输入的密码
			String loginsql=null;
			String box=(String) JC.getSelectedItem();
			if(box.equals("教师"))
			{
			loginsql="select password from teacher "
			+ "where name='"+name+"'";
			
			if(logindb(password,loginsql))
			{new StudentManage();
			this.setVisible(false);}//登录成功后设置登录窗口隐藏
			}
			else if(box.equals("学生"))
			{
				loginsql="select password from students "
				+ "where name='"+name+"'";//设置的用户名登录！！！
				
				if(logindb(password,loginsql))
				{new Student();	
				this.setVisible(false);}//登录成功后设置登录窗口隐藏
			}
		}
		else if(e.getSource()==JB2)
		{
			System.exit(0);
		}
	}
	//查询数据库
	private boolean logindb(String password,String loginsql){
		ResultSet rs=null;
		//连接数据库
		DBconnector conn=new DBconnector();
		Connection con=conn.getConnection();
		//System.out.println("连接成功");
		try {
			Statement stmt=(Statement) con.createStatement();
			rs=stmt.executeQuery(loginsql);
		} catch (SQLException e) {
			System.out.println("查询出错");
			e.printStackTrace();
		}
		//比对结果
		
		try {
			if(rs.next()&&rs.getString(1).equals(password))
			{
			rs.close();
			return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"密码或用户名输入错误");
		return false;
		
	}
	public static void main(String[] args) {
		new Login();
	}
}
