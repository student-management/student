package Daoimpl1;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Daoimpl.*;
import Daoimpl1.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Student extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	JButton JB1=new JButton();
	JButton JB2=new JButton();
	JButton JB3=new JButton();
	public Student()
	{
		this.setTitle("学生基本信息");
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		JB1.setText("个人信息查询");
		JB1.setBounds(150,80,150,20);
		JB1.addActionListener(this);
		this.add(JB1);
		
		JB2.setText("个人成绩查询");
		JB2.setBounds(150,160,150,20);
		JB2.addActionListener(this);
		this.add(JB2);
		
		JB3.setText("修改密码");
		JB3.setBounds(150,240,150,20);
		JB3.addActionListener(this);
		this.add(JB3);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e)
			{
				dispose();
			}
		});

	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB1)
		{
			new GetStudent();
		}
		else if(e.getSource()==JB2)
		{
			new GetGrade();
		}
		else if(e.getSource()==JB3)
		{
			new Updatepassword();
		}
	}
	public static void main(String[] args) {
		new Student();
	}
}
