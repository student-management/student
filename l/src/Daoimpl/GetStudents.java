package Daoimpl;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import util.student;
import Dao.DBconnector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.sun.glass.events.WindowEvent;

public class GetStudents extends JFrame{
	private static final long serialVersionUID = 1L;

	//static int rowCount=0;
	Vector all=new Vector();//�������ѧ����Ϣ
	Vector tablehead=new Vector();//�洢��ͷ��Ϣ����
	//head [] headers= {"ѧ��","����","����","�Ա�","����","�༶","ѧԺ"}; 
	
	JLabel JL=new JLabel("��ѯѧ��������Ϣ",JLabel.CENTER);//��������ǩ����
	JScrollPane jsl=new JScrollPane();//���ù������
	
	String sql="";
	public GetStudents()
	{
		
		sql="select * from students ;";
		//�������ݿ�
		DBconnector conn=new DBconnector();
		Connection con=(Connection) conn.getConnection();
		ResultSet rs=null;
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			rs=(ResultSet) stmt.executeQuery(sql);
			
			tablehead.add("ѧ��");
			tablehead.add("����");
			tablehead.add("�Ա�");
			tablehead.add("����");
			tablehead.add("�༶");
			tablehead.add("ѧԺ");
			
			while(rs.next())
			{
				student stu=new student();
				
				stu.setSid(rs.getInt("id"));
				stu.setSname(rs.getString("name"));
				stu.setSsex(rs.getString("sex"));
				stu.setSbirthday(rs.getString("birthday"));
				stu.setSclass(rs.getString("clas"));
				stu.setSschool(rs.getString("school"));
				
				Vector vc=new Vector();
				
				vc.add(stu.getSid());
				vc.add(stu.getSname());
				vc.add(stu.getSsex());
				vc.add(stu.getSbirthday());
				vc.add(stu.getSclass());
				vc.add(stu.getSschool());
				
				/*vc.add(rs.getInt("id"));
				vc.add(rs.getString("name"));
				vc.add(rs.getString("sex"));
				vc.add(rs.getString("birthday"));
				vc.add(rs.getString("clas"));
				vc.add(rs.getString("school"));*/
				all.add(vc);
			}
			
			JTable JT=new JTable(all,tablehead);
			JT.setRowHeight(20);//���ñ���и�
			JT.setEnabled(false);//���ñ�񲻿ɱ༭
			jsl.setViewportView(JT);//��ʾ�ڹ������
			
			
			
			this.add(jsl,BorderLayout.CENTER);
			this.setTitle("��ѯ���г�Ա��Ϣ");
			this.setSize(600,400);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter() {//���ô��ڼ�����
			@SuppressWarnings("unused")
			public void windowsClosing(WindowEvent e)
			{
				dispose();
			}
		});
	}
	

	public static void main(String[] args) {
		new GetStudents();
	}
}
