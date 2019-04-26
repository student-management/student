package Daoimpl;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import util.Grade;
import Dao.DBconnector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.sun.glass.events.WindowEvent;

public class GetGrade extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;	
	JLabel JL=new JLabel("查询学生成绩",JLabel.CENTER);//创建主标签对象
	JLabel JLNumber=new JLabel("请输入学号");
	JTextField JTNumber=new JTextField();
	
	JButton JB1=new JButton("查询");
	JButton JB2=new JButton("重置");
	JButton JB3=new JButton("退出");
	
	JLabel JLName=new JLabel("姓名:");//姓名标签组件
	JTextField JTName=new JTextField();//姓名文本框组件
	
	JLabel JLSex=new JLabel("性别:");//性别标签
	ButtonGroup BG=new ButtonGroup();//创建一个ButtonGroup标签对象
	JRadioButton JRB1=new JRadioButton("男");//创建单选按钮
	JRadioButton JRB2=new JRadioButton("女");
	
	JLabel JLClass=new JLabel("班级:");
	JTextField JTClass=new JTextField();
	
	JLabel JLMath=new JLabel("数学:");
	JTextField JTMath=new JTextField();
	
	JLabel JLChinese=new JLabel("语文:");
	JTextField JTChinese=new JTextField();	
	String sql="";
	public GetGrade()
	{
		this.setSize(500,400);
		this.setLocationRelativeTo(null);//设置JFrame窗口在屏幕的正中间
		this.setVisible(true);
		this.setTitle("查询学生成绩");
		this.setLayout(null);//设置窗口布局管理器为null,用setBounds()方法设置组件位置
		
		JL.setFont(new java.awt.Font("宋体",Font.PLAIN,20));
		JL.setBounds(150,30,200,40);
		this.add(JL);
		
		JLNumber.setBounds(100,80,100,20);
		this.add(JLNumber);
		JTNumber.setBounds(200,80,80,20);
		this.add(JTNumber);
		
		JB1.setBounds(80,120,90,20);
		this.add(JB1);
		JB1.addActionListener(this);
		JB2.setBounds(190,120,90,20);
		this.add(JB2);
		JB2.addActionListener(this);
		JB3.setBounds(300,120,90,20);
		this.add(JB3);
		JB3.addActionListener(this);
		
		JLSex.setBounds(100,200,100,20);//设置单选标签位置
		this.add(JLSex);
		JRB1.setBounds(200,200,40,20);
		JRB2.setBounds(300,200,40,20);
		this.add(JRB1);//添加到窗口
		this.add(JRB2);
		BG.add(JRB1);//将单选按钮添加到ButtonGroup组件
		BG.add(JRB2);
		
		JLName.setBounds(100,160,100,20);
		this.add(JLName);
		JTName.setBounds(200,160,80,20);
		this.add(JTName);
		
		JLMath.setBounds(100,240,100,20);
		this.add(JLMath);
		JTMath.setBounds(200,240,80,20);
		this.add(JTMath);
		
		JLClass.setBounds(100,280,100,20);
		this.add(JLClass);
		JTClass.setBounds(200,280,80,20);
		this.add(JTClass);
		
		JLChinese.setBounds(100,320,100,20);
		this.add(JLChinese);
		JTChinese.setBounds(200,320,80,20);
		this.add(JTChinese);
		
		addWindowListener(new WindowAdapter() {//设置窗口监听器
			@SuppressWarnings("unused")
			public void windowsClosing(WindowEvent e)
			{
				dispose();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB1)
		{
			Grade g=new Grade();
			g.setSid(Integer.parseInt(JTNumber.getText()));//返回输入的学号
			sql="select * from grade where sid="+g.getSid();
			//连接数据库
			DBconnector conn=new DBconnector();
			Connection con=(Connection) conn.getConnection();
			ResultSet rs=null;
			try {
				Statement stmt= (Statement) con.createStatement();
				rs=(ResultSet) stmt.executeQuery(sql);
				if(rs.next())
				{
					JTName.setText(rs.getString(2));
					if(rs.getString(3).equals("男"))
					{
						JRB1.setSelected(true);
					}
					else {
						JRB2.setSelected(true);
					}
					
					JTClass.setText(rs.getString(4));
					JTMath.setText(rs.getString(5));
					JTChinese.setText(rs.getString(6));
				}
				else{
					JOptionPane.showMessageDialog(null,"查无此人");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
		}else if(e.getSource()==JB2)//重置
		{
			JTNumber.setText(null);
			JTMath.setText(null);
			JTClass.setText(null);
			JTName.setText(null);
			JTChinese.setText(null);
		}else if(e.getSource()==JB3)
		{
			this.setVisible(false);//隐藏当前窗口
		}
		
	}
	
	public static void main(String[] args) {
		new GetGrade();
	}

}
