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
	Vector all=new Vector();//存放所有学生信息
	Vector tablehead=new Vector();//存储表头信息向量
		
	JLabel JL=new JLabel("查询学生成绩",JLabel.CENTER);//创建主标签对象
	JScrollPane jsl=new JScrollPane();//设置滚动面板
		
	String sql="";
	
	public GetGrades()
	{
		sql="select * from grade order by sid ;";
		//连接数据库
		DBconnector conn=new DBconnector();
		Connection con=(Connection) conn.getConnection();
		ResultSet rs=null;
		Statement stmt;
		try {
			stmt=(Statement) con.createStatement();
			rs=(ResultSet) stmt.executeQuery(sql);
			tablehead.add("学号");
			tablehead.add("姓名");
			tablehead.add("性别");
			tablehead.add("班级");
			tablehead.add("数学");
			tablehead.add("语文");
			
			while(rs.next())//获取总行数
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
			JT.setRowHeight(20);//设置表格行高
			JT.setEnabled(false);//设置表格不可编辑
			jsl.setViewportView(JT);//显示在滚动面板上
			
			
			this.add(jsl,BorderLayout.CENTER);
			this.setTitle("查询所有成员成绩");
			this.setSize(600,400);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			addWindowListener(new WindowAdapter() {//设置窗口监听器
				@SuppressWarnings("unused")
				public void windowsClosing(WindowEvent e)
				{
					dispose();
				}
			});
		} catch (SQLException e) {
			System.out.println("资源获取失败");
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new GetGrades();
	}

}
