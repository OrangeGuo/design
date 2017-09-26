package lianliankan;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;




//�����
class GamePanel extends JPanel implements MouseListener{
	list newList=null;
	String[] strings={"/tu/01.jpg","/tu/02.jpg","/tu/03.jpg","/tu/04.jpg","/tu/05.jpg","/tu/06.jpg","/tu/07.jpg","/tu/08.jpg","/tu/09.jpg",
			"/tu/10.jpg","/tu/11.jpg","/tu/12.jpg","/tu/13.jpg","/tu/14.jpg","/tu/15.jpg","/tu/16.jpg","/tu/17.jpg","/tu/18.jpg","/tu/19.jpg",
			"/tu/20.jpg","/tu/21.jpg","/tu/22.jpg","/tu/23.jpg","/tu/24.jpg","/tu/25.jpg","/tu/26.jpg","/tu/27.jpg","/tu/28.jpg","/tu/29.jpg",
			"/tu/30.jpg","/tu/31.jpg","/tu/32.jpg"};
	Vector<JButton> buttons=null,buttons_re=null;
	int []flag=null;
	int temp=-1,tempi=-1;
	public int grade=0;
	public void paint(Graphics g)
	{
		super.paint(g);
	}
	public GamePanel()
	{
		newList=new list();
		newList.random(64);
		buttons=new Vector<JButton>();
		buttons_re=new Vector<JButton>();
		flag=new int[64];
		list list=newList;
		
		//System.out.print(this.getSize().getHeight()+" "+size_y);
		while(list!=null)//����Ƿ������
		{
			if(list.n>31)list.n-=32;
			list=list.next;
		}
		this.setLayout(new GridLayout(8,8));
		for(int i=0;i<64;i++)
		{
			Image anImage=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(strings[newList.get(i)]));
			anImage=anImage.getScaledInstance(70, 70, Image.SCALE_FAST);
			ImageIcon icons=new ImageIcon(anImage);
			JButton jButton=new JButton(icons);
			jButton.addMouseListener(this);
			this.add(jButton);
			buttons.add(jButton);
			flag[i]=newList.get(i);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<64;i++)
		{
			JButton jButton=buttons.get(i);
			if(e.getSource().equals(jButton))
			{
				if(temp==-1)
				{
					temp=flag[i];
					tempi=i;
					jButton.setIcon(null);
					jButton.setBackground(Color.lightGray);
					buttons_re.add(jButton);
				}
				else 
				{
					if(temp==flag[i]&&tempi!=i)
					{
						temp=-1;
						tempi=-1;
						jButton.setIcon(null);
						jButton.setBackground(Color.lightGray);
						buttons_re.add(jButton);
					}
					else {
						JButton jButton2=buttons_re.get(buttons_re.size()-1);
						Image anImage=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(strings[temp]));
						anImage=anImage.getScaledInstance(70, 70, Image.SCALE_FAST);
						ImageIcon icons=new ImageIcon(anImage);
						jButton2.setIcon(icons);
                        buttons_re.remove(jButton2);
						temp=flag[i];
						tempi=i;
						jButton.setIcon(null);
						jButton.setBackground(Color.lightGray);
						buttons_re.add(jButton);
					}
						
				}
			}
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
class list{
	public int n=-1;
	public list next=null;
	
	public list(){
		
	}
	public void random(int size){

		    int k=0;
            list li_copy=this;
            this.n=(int)(Math.random()*size);
            k++;
			while(k<size)
			{
				li_copy=this;
				int n=(int)(Math.random()*size);//���0��size-1�������
				boolean p=true;//��ֹ����ظ��ĵ����
				for(int j=0;j<k;j++)
				{
					if(li_copy.n==n)
					{
						p=false;
						break;
					}
					if(li_copy.next==null)
						li_copy.next=new list();
					li_copy=li_copy.next;
				}
				if(p)
				{
					li_copy.n=n;
					k++;
				}
			}
			
		
	
	}
	public void add(int citynum)
	{
		list list=this;
		while(list.next!=null)
		{
			list=list.next;
		}
		list list2=new list();
		list2.n=citynum;
		list.next=list2;
	}
	public int get(int i)
	{
		if(i==0)
		{
			return this.n;
		}
		else 
		{
			list list_now=this;
			for(int j=0;j<i;j++)
			{
				if(list_now.next!=null)list_now=list_now.next;
			}
			return list_now.n;
		}
	}
	public void show(list li)
	{
		while(li!=null)
		{
			System.out.print(li.n+" ");
			li=li.next;
		}
		System.out.println();
	}


}
