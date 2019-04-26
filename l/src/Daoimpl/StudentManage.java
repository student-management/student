package Daoimpl;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class StudentManage extends JFrame implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel JL=new JLabel("欢迎进入学生信息管理系统",JLabel.CENTER);
	
	JMenuBar jm=new JMenuBar();//创建菜单栏对象
	JMenu jm1=new JMenu("信息");//创建信息菜单项对象
	JMenuItem jmi1=new JMenuItem("增加信息");
	JMenuItem jmi2=new JMenuItem("删除信息");
	JMenuItem jmi3=new JMenuItem("修改信息");
	JMenuItem jmi4=new JMenuItem("录入成绩");
	JMenuItem jmi5=new JMenuItem("修改成绩");
	
	JMenu jm2=new JMenu("查询");//创建查询菜单项对象
	JMenuItem jmi21=new JMenuItem("个人基本信息查询");
	JMenuItem jmi22=new JMenuItem("个人成绩查询");
	JMenuItem jmi23=new JMenuItem("所有人信息查询");
	JMenuItem jmi24=new JMenuItem("所有人成绩查询");
	JMenu jm3=new JMenu("其它");//创建其它菜单项对象
	JMenuItem jmi31=new JMenuItem("退出系统");
	
	
	public StudentManage()//定义无参构造方法
	{

		this.setTitle("学生基本信息管理");
		JL.setForeground(Color.darkGray);
		JL.setFont(new java.awt.Font("微软雅黑",Font.ITALIC,40));
		JL.setBounds(150,30,200,40);
		this.add(JL);
		this.setJMenuBar(jm);//将菜单栏组件添加到容器
		//将菜单组件添加到菜单栏组件上
		jm.add(jm1);
		jm.add(jm2);
		jm.add(jm3);
		
		//添加菜单项到菜单组件,并添加监听器
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jm1.add(jmi5);
		jm1.addActionListener(this);
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi5.addActionListener(this);
		
		jm2.add(jmi21);
		jm2.add(jmi22);
		jm2.add(jmi23);
		jm2.add(jmi24);
		jm2.addActionListener(this);
		jmi21.addActionListener(this);
		jmi22.addActionListener(this);
		jmi23.addActionListener(this);
		jmi24.addActionListener(this);
		
		jm3.add(jmi31);
		jm3.addActionListener(this);
		jmi31.addActionListener(this);
		
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		//设置窗口监听器
		addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e)
			{
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jmi1)
		{
			new ImpleDao().AddStudent1();
		}
		else if(e.getSource()==jmi2)
		{
			 new ImpleDao().DeleteStudent1();
		}
		else if(e.getSource()==jmi3)
		{
			new ImpleDao().UpdateStudent1();
		}
		else if(e.getSource()==jmi4)
		{
			new ImpleDao().AddGrade1();
		}
		else if(e.getSource()==jmi5)
		{
			new ImpleDao().UpdateGrade1();
		}
		else if(e.getSource()==jmi21)
		{
			new ImpleDao().GetStudent1();//查询个人基本信息
		}
		else if(e.getSource()==jmi22)
		{
			new ImpleDao().GetGrade1();//查询个人成绩
		}
		else if(e.getSource()==jmi23)
		{
			new ImpleDao().GetStudents1();//查询所有人信息
		}
		else if(e.getSource()==jmi24)
		{
			new ImpleDao().GetGrades1();
		}
		else if(e.getSource()==jmi31)//退出
		{
			this.dispose();
		}
	}
		public static void main(String [] args)
		{
			new StudentManage();
		}
}
