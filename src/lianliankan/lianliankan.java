package lianliankan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class lianliankan  {
	/**
	 * ����:������
	 * ����:���Ϊ
	 * ����:16.11.16
	 */
	//�Զ������
	StartPanel sPanel=null;
	MainPanel gamePanel=null;
	//swing���
	JFrame jFrame=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        lianliankan lianliankan=new lianliankan();
	}
	public lianliankan(){
		//��ʼ�������
		sPanel=new StartPanel();
		
		jFrame=new JFrame();
		//�����߳�
		Thread thread=new Thread(sPanel);
		thread.start();
		//���������ӵ�����

		jFrame.add(sPanel);
	    //����ͼ��
		Image a=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/tu/ͼ��2.jpg"));
		jFrame.setIconImage(a);
	
	
		 //���ô�������
		jFrame.setSize(600,600);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("������2016");
		jFrame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getX()>=240&&e.getX()<=320&&e.getY()>=250&&e.getY()<=345&&sPanel.time==7)//��Ӧ������¼�
				{
					jFrame.remove(sPanel);
					gamePanel=new MainPanel();
					jFrame.add(gamePanel);
				}
			}
		});
	}
	
	
}
