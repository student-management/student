package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver"; // ������
	private static final String DBURL = "jdbc:mysql://localhost:3306/demo?characterEncoding=utf-8";// ����URL
	private static final String DBUSER = "root"; // �û���
	private static final String DBPASSWORD = "123456"; // ����
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DBDRIVER);//��������
			//��ȡ����
			conn = (Connection) DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���������쳣");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�����쳣");
		}
		return conn;
	}
}
