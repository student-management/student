package Daoimpl1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import Dao.DBconnector;

public class Updatepassword extends JFrame  implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel JL = new JLabel("修改密码");//设置主标签
	JLabel JL1=new JLabel("原密码");
	JTextField JT1=new JTextField();
	JLabel JL2=new JLabel("新密码");
	JTextField JT2=new JTextField();
	JLabel JLNumber= new JLabel("学号:");//学号标签组件
	JTextField JTNumber= new JTextField();
	
	
	JButton JB1=new JButton("修改");
	JButton JB2=new JButton("重置");
	JButton JB3=new JButton("退出");
	
	String sql="";
	public Updatepassword() {
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//设置窗口不可拉伸
		this.setVisible(true);
		
		this.setTitle("修改个人密码");
		this.setLayout(null);//设置窗口布局管理器为null,用setBounds()方法设置组件位置
		JL.setFont(new java.awt.Font("微软雅黑",Font.PLAIN,20));
		JL.setForeground(Color.red);
		JL.setBounds(200,50,200,40);
		this.add(JL);
		
		JLNumber.setBounds(150,100,100,20);
		this.add(JLNumber);
		JTNumber.setBounds(250,100,100,20);
		this.add(JTNumber);
		
		JL1.setBounds(150,150,100,20);
		this.add(JL1);
		JT1.setBounds(250,150,100,20);
		this.add(JT1);
		
		JL2.setBounds(150,200,100,20);
		this.add(JL2);
		JT2.setBounds(250,200,100,20);
		this.add(JT2);
		
		JB1.setBounds(80,250,90,20);
		this.add(JB1);
		JB1.addActionListener(this);
		JB2.setBounds(190,250,90,20);
		this.add(JB2);
		JB2.addActionListener(this);
		JB3.setBounds(300,250,90,20);
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
			int Sid=Integer.parseInt(JTNumber.getText()) ;
			String paw1=JT1.getText();
			String paw2=JT2.getText();
			if(JTNumber.getText().equals("")||paw1.equals("")||paw2.equals(""))//如果未输入任何信息
			{
				JOptionPane.showMessageDialog(null,"请输入完整学生信息");
			}
			//连接数据库
			DBconnector conn=new DBconnector();
			Connection con=(Connection) conn.getConnection();
			System.out.println("连接成功");
			ResultSet rs=null;
			
			try {
				Statement stmt=(Statement) con.createStatement();
				sql="select password from students where id="+Sid;
				rs=(ResultSet) stmt.executeQuery(sql);
				if(rs.next())
				{
					if(rs.getString(1).equals(paw1))
					{
						sql="update students set password = '"+paw2+ "' where id="+Sid;
						int i=stmt.executeUpdate(sql);
						if(i>0)
						{
							JOptionPane.showMessageDialog(null,"添加成功");
						}
						else{
							JOptionPane.showMessageDialog(null,"添加失败");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"密码输入有误");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"学号输入有误");
				}
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}else if(e.getSource()==JB2)
		{
			JT1.setText(null);
			JT2.setText(null);
			JTNumber.setText(null);
		}else if(e.getSource()==JB3)
		{
			System.exit(0);
		}
	}
	public static void main(String [] args)
	{
		new Updatepassword();
	}
}
