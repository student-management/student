package Daoimpl1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import Dao.DBconnector;

public class Updatepassword extends JFrame  implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel JL = new JLabel("�޸�����");//��������ǩ
	JLabel JL1=new JLabel("ԭ����");
	JTextField JT1=new JTextField();
	JLabel JL2=new JLabel("������");
	JTextField JT2=new JTextField();
	JLabel JLNumber= new JLabel("ѧ��:");//ѧ�ű�ǩ���
	JTextField JTNumber= new JTextField();
	
	
	JButton JB1=new JButton("�޸�");
	JButton JB2=new JButton("����");
	JButton JB3=new JButton("�˳�");
	
	String sql="";
	public Updatepassword() {
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//���ô��ڲ�������
		this.setVisible(true);
		
		this.setTitle("�޸ĸ�������");
		this.setLayout(null);//���ô��ڲ��ֹ�����Ϊnull,��setBounds()�����������λ��
		JL.setFont(new java.awt.Font("΢���ź�",Font.PLAIN,20));
		JL.setForeground(Color.red);
		JL.setBounds(200,50,200,40);
		this.add(JL);
		
		JLNumber.setBounds(150,100,100,20);
		this.add(JLNumber);
		JTNumber.setBounds(250,100,100,20);
		this.add(JTNumber);
		
		JL1.setBounds(150,150,100,20);
		this.add(JL1);
		JT1.setBounds(250,150,100,20);
		this.add(JT1);
		
		JL2.setBounds(150,200,100,20);
		this.add(JL2);
		JT2.setBounds(250,200,100,20);
		this.add(JT2);
		
		JB1.setBounds(80,250,90,20);
		this.add(JB1);
		JB1.addActionListener(this);
		JB2.setBounds(190,250,90,20);
		this.add(JB2);
		JB2.addActionListener(this);
		JB3.setBounds(300,250,90,20);
		this.add(JB3);
		JB3.addActionListener(this);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==JB1)
		{
			int Sid=Integer.parseInt(JTNumber.getText()) ;
			String paw1=JT1.getText();
			String paw2=JT2.getText();
			if(JTNumber.getText().equals("")||paw1.equals("")||paw2.equals(""))//���δ�����κ���Ϣ
			{
				JOptionPane.showMessageDialog(null,"����������ѧ����Ϣ");
			}
			//�������ݿ�
			DBconnector conn=new DBconnector();
			Connection con=(Connection) conn.getConnection();
			System.out.println("���ӳɹ�");
			ResultSet rs=null;
			
			try {
				Statement stmt=(Statement) con.createStatement();
				sql="select password from students where id="+Sid;
				rs=(ResultSet) stmt.executeQuery(sql);
				if(rs.next())
				{
					if(rs.getString(1).equals(paw1))
					{
						sql="update students set password = '"+paw2+ "' where id="+Sid;
						int i=stmt.executeUpdate(sql);
						if(i>0)
						{
							JOptionPane.showMessageDialog(null,"��ӳɹ�");
						}
						else{
							JOptionPane.showMessageDialog(null,"���ʧ��");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"������������");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"ѧ����������");
				}
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}else if(e.getSource()==JB2)
		{
			JT1.setText(null);
			JT2.setText(null);
			JTNumber.setText(null);
		}else if(e.getSource()==JB3)
		{
			System.exit(0);
		}
	}
	public static void main(String [] args)
	{
		new Updatepassword();
	}
}
