package Daoimpl;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class StudentManage extends JFrame implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel JL=new JLabel("��ӭ����ѧ����Ϣ����ϵͳ",JLabel.CENTER);
	
	JMenuBar jm=new JMenuBar();//�����˵�������
	JMenu jm1=new JMenu("��Ϣ");//������Ϣ�˵������
	JMenuItem jmi1=new JMenuItem("������Ϣ");
	JMenuItem jmi2=new JMenuItem("ɾ����Ϣ");
	JMenuItem jmi3=new JMenuItem("�޸���Ϣ");
	JMenuItem jmi4=new JMenuItem("¼��ɼ�");
	JMenuItem jmi5=new JMenuItem("�޸ĳɼ�");
	
	JMenu jm2=new JMenu("��ѯ");//������ѯ�˵������
	JMenuItem jmi21=new JMenuItem("���˻�����Ϣ��ѯ");
	JMenuItem jmi22=new JMenuItem("���˳ɼ���ѯ");
	JMenuItem jmi23=new JMenuItem("��������Ϣ��ѯ");
	JMenuItem jmi24=new JMenuItem("�����˳ɼ���ѯ");
	JMenu jm3=new JMenu("����");//���������˵������
	JMenuItem jmi31=new JMenuItem("�˳�ϵͳ");
	
	
	public StudentManage()//�����޲ι��췽��
	{

		this.setTitle("ѧ��������Ϣ����");
		JL.setForeground(Color.darkGray);
		JL.setFont(new java.awt.Font("΢���ź�",Font.ITALIC,40));
		JL.setBounds(150,30,200,40);
		this.add(JL);
		this.setJMenuBar(jm);//���˵��������ӵ�����
		//���˵������ӵ��˵��������
		jm.add(jm1);
		jm.add(jm2);
		jm.add(jm3);
		
		//��Ӳ˵���˵����,����Ӽ�����
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jm1.add(jmi5);
		jm1.addActionListener(this);
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi5.addActionListener(this);
		
		jm2.add(jmi21);
		jm2.add(jmi22);
		jm2.add(jmi23);
		jm2.add(jmi24);
		jm2.addActionListener(this);
		jmi21.addActionListener(this);
		jmi22.addActionListener(this);
		jmi23.addActionListener(this);
		jmi24.addActionListener(this);
		
		jm3.add(jmi31);
		jm3.addActionListener(this);
		jmi31.addActionListener(this);
		
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		//���ô��ڼ�����
		addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent e)
			{
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jmi1)
		{
			new ImpleDao().AddStudent1();
		}
		else if(e.getSource()==jmi2)
		{
			 new ImpleDao().DeleteStudent1();
		}
		else if(e.getSource()==jmi3)
		{
			new ImpleDao().UpdateStudent1();
		}
		else if(e.getSource()==jmi4)
		{
			new ImpleDao().AddGrade1();
		}
		else if(e.getSource()==jmi5)
		{
			new ImpleDao().UpdateGrade1();
		}
		else if(e.getSource()==jmi21)
		{
			new ImpleDao().GetStudent1();//��ѯ���˻�����Ϣ
		}
		else if(e.getSource()==jmi22)
		{
			new ImpleDao().GetGrade1();//��ѯ���˳ɼ�
		}
		else if(e.getSource()==jmi23)
		{
			new ImpleDao().GetStudents1();//��ѯ��������Ϣ
		}
		else if(e.getSource()==jmi24)
		{
			new ImpleDao().GetGrades1();
		}
		else if(e.getSource()==jmi31)//�˳�
		{
			this.dispose();
		}
	}
		public static void main(String [] args)
		{
			new StudentManage();
		}
}
