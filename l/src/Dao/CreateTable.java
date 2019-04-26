 package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Daoimpl.AddStudent;
import sun.applet.Main;

public class CreateTable {

	public CreateTable(){
		DBconnector conn=new DBconnector();
		Connection con=conn.getConnection();
		if(con!=null)
		System.out.println("数据库连接成功");
		try {
			Statement stmt=con.createStatement();
			String sql1="create table students"
					+ "(id int(11) primary key,"
					+ "password varchar(10) default 123456,"
					+ "name varchar(10),"
					+ "sex varchar(4),"
					+ "birthday varchar(15),"
					+ "clas varchar (15),"
					+ "school varchar(20));";
			String sql2="create table teacher"
					+ "(name varchar(10) primary key,"
					+ "password varchar(10));";
			String sql="insert into teacher(name,password) "
					+ "values('teacher','654321')";
			String sql3="create table grade"
					+ "(sid int(11) primary key,"
					+ "name varchar(10),"
					+ "sex varchar(4),"
					+ "clas varchar (15),"
					+ "math int(3),"
					+ "chinese int(3));";
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql3);
			
			System.out.println("表建立完成");
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	public static void main(String[] args) {
		new CreateTable();
	}
}
