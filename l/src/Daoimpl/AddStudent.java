package Daoimpl;

import Dao.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import util.student;
import Dao.DBconnector;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;

public class AddStudent extends JFrame implements ActionListener{
	/*
	 * ʵ�����л��ӿ�,���ڿ�汾����
	 */
	private static final long SerialVersionUID =1L;
	
	JLabel JL=new JLabel("���ѧ����Ϣ",JLabel.CENTER);//��������ǩ
	JLabel JLNumber= new JLabel("ѧ��:");//ѧ�ű�ǩ���
	JTextField JTNumber= new JTextField();//ѧ���ı������
	
	JLabel JLName=new JLabel("����:");//������ǩ���
	JTextField JTName=new JTextField();//�����ı������
	
	JLabel JLSex=new JLabel("�Ա�:");//�Ա��ǩ
	ButtonGroup BG=new ButtonGroup();//����һ��ButtonGroup��ǩ����
	JRadioButton JRB1=new JRadioButton("��");//������ѡ��ť
	JRadioButton JRB2=new JRadioButton("Ů");
	
	JLabel JLClass=new JLabel("�༶:");
	JTextField JTClass=new JTextField();
	
	JLabel JLBirthday=new JLabel("����:");
	JTextField JTBirthday=new JTextField();
	
	JLabel JLSchool=new JLabel("ѧԺ:");
	JTextField JTSchool=new JTextField();
	
	JButton JB1=new JButton("���");
	JButton JB2=new JButton("����");
	JButton JB3=new JButton("�˳�");
	
	String sql="";
	//�������췽��
	public AddStudent()
	{	
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.setTitle("���ѧ����Ϣ");
		this.setLayout(null);//���ô��ڲ��ֹ�����Ϊnull,��setBounds()�����������λ��
		JL.setFont(new java.awt.Font("����",Font.PLAIN,20));
		JL.setBounds(100,30,200,40);
		this.add(JL);
		
		JLNumber.setBounds(100,80,100,20);
		this.add(JLNumber);
		JTNumber.setBounds(200,80,80,20);
		this.add(JTNumber);
		
		JLName.setBounds(100,120,100,20);
		this.add(JLName);
		JTName.setBounds(200,120,80,20);
		this.add(JTName);
		
		JLSex.setBounds(100,160,100,20);//���õ�ѡ��ǩλ��
		this.add(JLSex);
		JRB1.setBounds(200,160,40,20);
		JRB2.setBounds(300,160,40,20);
		this.add(JRB1);//��ӵ�����
		this.add(JRB2);
		BG.add(JRB1);//����ѡ��ť��ӵ�ButtonGroup���
		BG.add(JRB2);
		
		JLBirthday.setBounds(100,200,100,20);
		this.add(JLBirthday);
		JTBirthday.setBounds(200,200,80,20);
		this.add(JTBirthday);
		
		JLClass.setBounds(100,240,100,20);
		this.add(JLClass);
		JTClass.setBounds(200,240,80,20);
		this.add(JTClass);
		
		JLSchool.setBounds(100,280,100,20);
		this.add(JLSchool);
		JTSchool.setBounds(200,280,80,20);
		this.add(JTSchool);
		
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
			student stu=new student();//ʵ��������
			
			stu.setSid(Integer.parseInt(JTNumber.getText()));
			stu.setSname(JTName.getText());
			stu.setSsex("Ů");
			stu.setSbirthday(JTBirthday.getText());
			stu.setSclass(JTClass.getText());
			if(JRB1.isSelected())
				stu.setSsex("��");
			stu.setSschool(JTSchool.getText());
			
			sql="select * from students where id='"+stu.getSid()+"'";
			//�������ݿ�
			DBconnector conn=new DBconnector();
			Connection con=conn.getConnection();
			
			//����Statement����
			try {
				Statement stmt=(Statement) con.createStatement();
				ResultSet rs= (ResultSet) stmt.executeQuery(sql);
				System.out.println("��Դ��óɹ�");
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null,"��ѧ���Ѿ�����");
					rs.close();
				}
				else{
					sql="insert into students(id,name,sex,birthday,clas,school) "
							+ "values('"+stu.getSid()+"','"+stu.getSname()+"','"+stu.getSsex()+"','"+stu.getSbirthday()+"','"+
							stu.getSclass()+"','"+stu.getSschool()+ "');";
					int i=stmt.executeUpdate(sql);
					if(i>0)
					{
						JOptionPane.showMessageDialog(null,"��ӳɹ�");
					}
					else{
						JOptionPane.showMessageDialog(null,"���ʧ��");
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==JB2)
		{
			JTNumber.setText(null);
			JTBirthday.setText(null);
			JTClass.setText(null);
			JTName.setText(null);
			JTSchool.setText(null);
		}
		else if(e.getSource()==JB3)
		{
			this.setVisible(false);
		}
		
	}
	public static void main(String[] args) {
		new AddStudent();
	}
}
