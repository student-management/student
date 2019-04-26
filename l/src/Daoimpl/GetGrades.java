package Daoimpl;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import util.Grade;
import Dao.DBconnector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.sun.glass.events.WindowEvent;

public class GetGrades extends JFrame {
	private static final long serialVersionUID = 1L;
	Vector all=new Vector();//�������ѧ����Ϣ
	Vector tablehead=new Vector();//�洢��ͷ��Ϣ����
		
	JLabel JL=new JLabel("��ѯѧ���ɼ�",JLabel.CENTER);//��������ǩ����
	JScrollPane jsl=new JScrollPane();//���ù������
		
	String sql="";
	
	public GetGrades()
	{
		sql="select * from grade order by sid ;";
		//�������ݿ�
		DBconnector conn=new DBconnector();
		Connection con=(Connection) conn.getConnection();
		ResultSet rs=null;
		Statement stmt;
		try {
			stmt=(Statement) con.createStatement();
			rs=(ResultSet) stmt.executeQuery(sql);
			tablehead.add("ѧ��");
			tablehead.add("����");
			tablehead.add("�Ա�");
			tablehead.add("�༶");
			tablehead.add("��ѧ");
			tablehead.add("����");
			
			while(rs.next())//��ȡ������
			{
				Grade g=new Grade();
				
				g.setSid(rs.getInt("sid"));
				g.setSname(rs.getString("name"));
				g.setSsex(rs.getString("sex"));
				g.setSclas(rs.getString("clas"));
				g.setSchinese(Integer.parseInt(rs.getString("chinese")));
				g.setSmath(Integer.parseInt(rs.getString("math")));
				
				
				Vector vc=new Vector();
				vc.add(g.getSid());
				vc.add(g.getSname());
				vc.add(g.getSsex());
				vc.add(g.getSclas());
				vc.add(g.getSmath());
				vc.add(g.getSchinese());
				
				/*vc.add(rs.getInt("sid"));
				vc.add(rs.getString("name"));
				vc.add(rs.getString("sex"));
				vc.add(rs.getString("clas"));
				vc.add(rs.getString("math"));
				vc.add(rs.getString("chinese"));*/
				all.add(vc);
			}
			
			JTable JT=new JTable(all,tablehead);
			JT.setRowHeight(20);//���ñ���и�
			JT.setEnabled(false);//���ñ�񲻿ɱ༭
			jsl.setViewportView(JT);//��ʾ�ڹ��������
			
			
			this.add(jsl,BorderLayout.CENTER);
			this.setTitle("��ѯ���г�Ա�ɼ�");
			this.setSize(600,400);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			addWindowListener(new WindowAdapter() {//���ô��ڼ�����
				@SuppressWarnings("unused")
				public void windowsClosing(WindowEvent e)
				{
					dispose();
				}
			});
		} catch (SQLException e) {
			System.out.println("��Դ��ȡʧ��");
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new GetGrades();
	}

}
