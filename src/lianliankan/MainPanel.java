package lianliankan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MainPanel extends JPanel implements MouseListener{
	public GamePanel gPanel=null;
	JPanel jPanel=null;
	JLabel jLabel=null,jLabel2=null;;
	JButton jButton=null;
	String gString=null;
	clock timeClock=null;
	Thread thread=null;
	public void paint(Graphics g)
	{
		super.paint(g);
		this.setBackground(Color.lightGray);
	}
	public MainPanel() {
		gPanel=new GamePanel();
		jPanel=new JPanel();
		jLabel=new JLabel();
		jButton=new JButton("��ʼ");
		jButton.addMouseListener(this);
		jLabel2=new JLabel();
		jPanel.setLayout(new GridLayout(1,3));
		jPanel.add(jButton);
		jPanel.add(jLabel);
		jPanel.add(jLabel2);
		timeClock=new clock(50, jLabel2);
	    thread=new Thread(timeClock);
		this.setLayout(new BorderLayout());
		this.add(gPanel);
		this.add(jPanel,BorderLayout.NORTH);
		this.getGrade();
	}
	public void getGrade()
	{
		gString="�÷�:"+String.valueOf(this.gPanel.grade);
		this.jLabel.setText(gString);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jButton))
		{
			this.thread.start();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
