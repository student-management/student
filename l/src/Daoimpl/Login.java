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
	JLabel JLUserName=new JLabel("�û���:");//������ǩ����
	JLabel JLPaw=new JLabel("��  ��:");
	JLabel JL1=new JLabel("��  ��:");
	JTextField JTUserName= new JTextField();//�����ı������
	JPasswordField JTpaw=new JPasswordField();//������������
	JComboBox<String> JC=new JComboBox<String>();//������Ͽ�ܶ���
	
	
	
	JButton JB1=new JButton("��¼");//������ť����
	JButton JB2=new JButton("ȡ��");

	
	public Login()//���췽��
	{
		//���ô��ڱ���
		this.setTitle("ѧ������ϵͳ");
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		//ѧ�����������,������������ʼλ�� ��x��y��width��hight��
		JLUserName.setBounds(100,40,100,20);
		this.add(JLUserName);
		JTUserName.setBounds(200,40,80,20);
		this.add(JTUserName);
		//ѧ����������
		JLPaw.setBounds(100,100,60,20);
		this.add(JLPaw);
		JTpaw.setBounds(200,100,80,20);
		this.add(JTpaw);
		
		JL1.setBounds(100,160,80,20);
		this.add(JL1);
		JC.setBounds(200,160,80,20);
		this.add(JC);
		JC.addItem(new String("ѧ��"));
		JC.addItem(new String("��ʦ"));
		
		//��Ӱ�ť
		JB1.setBounds(100,200,60,20);
		this.add(JB1);
		JB1.addActionListener(this);//Ϊ��¼���ü�����
		JB2.setBounds(200,200,60,20);
		this.add(JB2);
		JB2.addActionListener(this);//Ϊȡ�����ü�����
		
		this.setVisible(true);//���ô��ڿɼ�
		this.setSize(400,250);
		this.setLocationRelativeTo(null);//���ô�������Ļ�м�
		//�������ڲ���ķ�ʽΪ�������ü����������ڹرմ���
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB1)//��¼��ť
		{
			String name=JTUserName.getText();//��ȡ���������
			String password=new String(JTpaw.getPassword());//������������
			String loginsql=null;
			String box=(String) JC.getSelectedItem();
			if(box.equals("��ʦ"))
			{
			loginsql="select password from teacher "
			+ "where name='"+name+"'";
			
			if(logindb(password,loginsql))
			{new StudentManage();
			this.setVisible(false);}//��¼�ɹ������õ�¼��������
			}
			else if(box.equals("ѧ��"))
			{
				loginsql="select password from students "
				+ "where name='"+name+"'";//���õ��û�����¼������
				
				if(logindb(password,loginsql))
				{new Student();	
				this.setVisible(false);}//��¼�ɹ������õ�¼��������
			}
		}
		else if(e.getSource()==JB2)
		{
			System.exit(0);
		}
	}
	//��ѯ���ݿ�
	private boolean logindb(String password,String loginsql){
		ResultSet rs=null;
		//�������ݿ�
		DBconnector conn=new DBconnector();
		Connection con=conn.getConnection();
		//System.out.println("���ӳɹ�");
		try {
			Statement stmt=(Statement) con.createStatement();
			rs=stmt.executeQuery(loginsql);
		} catch (SQLException e) {
			System.out.println("��ѯ����");
			e.printStackTrace();
		}
		//�ȶԽ��
		
		try {
			if(rs.next()&&rs.getString(1).equals(password))
			{
			rs.close();
			return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"������û����������");
		return false;
		
	}
	public static void main(String[] args) {
		new Login();
	}
}
