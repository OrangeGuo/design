package lianliankan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.lang.model.element.Element;
import javax.swing.JPanel;
//��������
class StartPanel extends JPanel implements Runnable{
	public int time=0;
	public void paint(Graphics g)
	{
		super.paint(g);
		this.setBackground(Color.black);
		g.setColor(Color.yellow);
		Font font=new Font("����",Font.BOLD,100);
		this.setFont(font);
		if(time<2)g.drawString("������", 150, 300);
		else if(time==2)
		{
			g.setColor(Color.green);
			g.drawString("2", 250, 300);
		}
		else if(time==3)
		{
			g.setColor(Color.red);
			g.drawString("0", 250, 300);
		}
		else if(time==4)
		{
			g.setColor(Color.blue);
			g.drawString("1", 250, 300);
		}
		else if(time==5)
		{
			g.setColor(Color.white);
			g.drawString("6", 250, 300);
		}
		else if(time==6){
			g.drawString("������2016", 20, 300);
		}
		else {
			g.drawString("��", 220, 300);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(time<7)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			time++;
			this.repaint();
		}
	}
}
