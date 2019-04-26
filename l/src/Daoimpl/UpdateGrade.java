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
	
	JLabel JL=new JLabel("�޸�ѧ���ɼ�",JLabel.CENTER);//��������ǩ
	JLabel JLNumber= new JLabel("��ѧ��ѧ��:");//ѧ�ű�ǩ���
	JTextField JTNumber= new JTextField();//ѧ���ı������
	
	JLabel JLName=new JLabel("����:");//������ǩ���
	JTextField JTName=new JTextField();//�����ı������

	JLabel JLMath=new JLabel("��ѧ:");
	JTextField JTMath=new JTextField();
	
	JLabel JLChinese=new JLabel("����:");
	JTextField JTChinese=new JTextField();
	
	JButton JB1=new JButton("�޸�");
	JButton JB2=new JButton("����");
	JButton JB3=new JButton("�˳�");
	
	String sql="";
	public UpdateGrade()
	{
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("�޸�ѧ����Ϣ");
		this.setLayout(null);//���ô��ڲ��ֹ�����Ϊnull,��setBounds()�����������λ��
		JL.setFont(new java.awt.Font("����",Font.PLAIN,20));
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
			//���ı������ݴ������
			Grade stu=new Grade();
			stu.setSid(Integer.parseInt(JTNumber.getText()));
			stu.setSname(JTName.getText());
			stu.setSmath(Integer.parseInt(JTMath.getText()));
			stu.setSchinese(Integer.parseInt(JTChinese.getText()));
		

			sql="select * from grade where sid="+stu.getSid();
			//�������ݿ�
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
						JOptionPane.showMessageDialog(null,"���³ɹ�");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "����ʧ��");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "���û�������");
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
