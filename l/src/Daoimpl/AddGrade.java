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
import Dao.DBconnector;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class AddGrade extends JFrame implements ActionListener{
	
		JLabel JL=new JLabel("添加成绩",JLabel.CENTER);//主标签
		JLabel JLNumber= new JLabel("学号:");//学号标签组件
		JTextField JTNumber= new JTextField();
		
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
		
		JButton JB1=new JButton("添加");
		JButton JB2=new JButton("重置");
		JButton JB3=new JButton("退出");
		String sql="";
		public AddGrade()
		{
			this.setSize(500,400);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setTitle("添加学生信息");
			
			this.setLayout(null);//设置窗口布局管理器为null,用setBounds()方法设置组件位置
			
			JL.setFont(new java.awt.Font("宋体",Font.PLAIN,20));
			JL.setBounds(125,30,200,40);
			this.add(JL);
			
			JLNumber.setBounds(100,80,100,20);
			this.add(JLNumber);
			JTNumber.setBounds(200,80,80,20);
			this.add(JTNumber);
			
			JLName.setBounds(100,120,100,20);
			this.add(JLName);
			JTName.setBounds(200,120,80,20);
			this.add(JTName);
			
			JLSex.setBounds(100,160,100,20);//设置单选标签位置
			this.add(JLSex);
			JRB1.setBounds(200,160,40,20);
			JRB2.setBounds(300,160,40,20);
			this.add(JRB1);//添加到窗口
			this.add(JRB2);
			BG.add(JRB1);//将单选按钮添加到ButtonGroup组件
			BG.add(JRB2);
			
			JLMath.setBounds(100,240,100,20);
			this.add(JLMath);
			JTMath.setBounds(200,240,80,20);
			this.add(JTMath);
			
			JLClass.setBounds(100,200,100,20);
			this.add(JLClass);
			JTClass.setBounds(200,200,80,20);
			this.add(JTClass);
			
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB1)
		{
			Grade g=new Grade();
			g.setSid(Integer.parseInt(JTNumber.getText()));
			g.setSname(JTName.getText());
			g.setSsex("女");
			g.setSmath(Integer.parseInt(JTMath.getText()));
			g.setSchinese(Integer.parseInt(JTChinese.getText()));
			
			if(JRB1.isSelected())
				g.setSsex("男");
			
			sql="select * from grade where sid='"+g.getSid()+"'";
			//连接数据库
			DBconnector conn=new DBconnector();
			Connection con=conn.getConnection();
			
			//创建Statement对象
			try {
				Statement stmt=(Statement) con.createStatement();
				ResultSet rs= (ResultSet) stmt.executeQuery(sql);
				System.out.println("资源获得成功");
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null,"该学生成绩已经添加");
					rs.close();
				}
				else{
					sql="insert into grade(sid,name,sex,clas,math,chinese) "
							+ "values('"+g.getSid()+"','"+g.getSname()+"','"+g.getSsex()+"','"
							+g.getSclas()+"','"+g.getSmath()+"','"+g.getSchinese()+ "');";
					int i=stmt.executeUpdate(sql);
					if(i>0)
					{
						JOptionPane.showMessageDialog(null,"添加成功");
					}
					else{
						JOptionPane.showMessageDialog(null,"添加失败");
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==JB2)
		{
			JTNumber.setText(null);
			JTChinese.setText(null);
			JTClass.setText(null);
			JTName.setText(null);
			JTMath.setText(null);
		}
		else if(e.getSource()==JB3)
		{
			this.setVisible(false);
		}
	}		
	public static void main(String[] args) {
		new AddGrade();
	}
}
