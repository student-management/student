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
	Vector all=new Vector();//存放所有学生信息
	Vector tablehead=new Vector();//存储表头信息向量
	//head [] headers= {"学号","密码","姓名","性别","生日","班级","学院"}; 
	
	JLabel JL=new JLabel("查询学生基本信息",JLabel.CENTER);//创建主标签对象
	JScrollPane jsl=new JScrollPane();//设置滚动面板
	
	String sql="";
	public GetStudents()
	{
		
		sql="select * from students ;";
		//连接数据库
		DBconnector conn=new DBconnector();
		Connection con=(Connection) conn.getConnection();
		ResultSet rs=null;
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			rs=(ResultSet) stmt.executeQuery(sql);
			
			tablehead.add("学号");
			tablehead.add("姓名");
			tablehead.add("性别");
			tablehead.add("生日");
			tablehead.add("班级");
			tablehead.add("学院");
			
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
			JT.setRowHeight(20);//设置表格行高
			JT.setEnabled(false);//设置表格不可编辑
			jsl.setViewportView(JT);//显示在滚动面板
			
			
			
			this.add(jsl,BorderLayout.CENTER);
			this.setTitle("查询所有成员信息");
			this.setSize(600,400);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter() {//设置窗口监听器
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
