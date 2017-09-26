package Queen;

import java.awt.BufferCapabilities;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class queen extends JFrame implements MouseListener,ActionListener,Runnable{
	JPanel jPanel=null;//主面板
	JMenuBar jmb=null;
	JMenu jm=null;
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	JButton[][] button=null;
	boolean[][] flag=null;//标记数组
   
    int[][] index=null;
	boolean p;
	int row=0;
	int num=0;
	int add=0;
	int[] history=null;
	Vector<Integer> as=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        queen queen=new queen();
	}
	public queen()
	{
		as=new Vector<Integer>();
		//初始化swing组件
		jmb=new JMenuBar();
		jm=new JMenu("菜单");
		jmi1=new JMenuItem("上一步");
		jmi2=new JMenuItem("下一步");
		jm.add(jmi1);
		jm.add(jmi2);
		jmb.add(jm);
		jPanel=new JPanel();
		jPanel.setBackground(Color.LIGHT_GRAY);
		jPanel.setLayout(new GridLayout(8,8));
		button=new JButton[8][8];
		flag=new boolean[8][8];
		index=new int[64][2];
		history=new int[8];
		for(int i=0;i<8;i++)
		{
			history[i]=-1;
		}
		for(int i=0;i<64;i++)
		{
			index[i][0]=0;
			index[i][1]=0;
		}
		p=false;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				JButton but=new JButton();
				but.setBackground(Color.white);
				but.addMouseListener(this);//添加监听
				button[i][j]=but;
				jPanel.add(but);
				flag[i][j]=true;
			}
		}
		jmi1.addActionListener(this);
		jmi1.setActionCommand("back");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("next");
	    this.setJMenuBar(jmb);
		this.add(jPanel);
		//设置图标
		 Image a=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/tu/图标2.jpg"));
 		 this.setIconImage(a);
 		 //设置窗体属性
 		this.setLocation(200,200);
		this.setTitle("Queen");
		this.setSize(600,600);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Thread thread=new Thread(this);
		thread.start();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<8;i++){
			if(flag[row][i])
			{
	
				JButton jButton=button[row][i];
				if(e.getSource().equals(jButton))
				{
					p=true;
					if(add!=0)as.add(add);
					add=0;
					if(flag[row][i])
					{
						jButton.setBackground(Color.green);
					    /*
					    jButton=new JButton(icon);
					    jButton.setOpaque(false);
					    jButton.setContentAreaFilled(false);
					    button[row][i]=jButton;*/
						index[num][0]=row;
						index[num][1]=i;
						num++;
						add++;
						flag[row][i]=false;
					}
					for(int j=0;j<8;j++)
					{
						if(flag[row][j])
						{
							JButton jButton2=button[row][j];
							jButton2.setBackground(Color.gray);
						
						     index[num][0]=row;
								index[num][1]=j;
								num++;
								add++;
							flag[row][j]=false;
						}
					}
					//所在列全部锁定不可选状态
					for(int j=row+1;j<8;j++)
					{
						
						if(flag[j][i])
						{
							JButton jButton2=button[j][i];
							jButton2.setBackground(Color.gray);
						
							flag[j][i]=false;
							index[num][0]=j;
							index[num][1]=i;
							num++;
							add++;
						}
						
					}
					int x=i-1,y=1;
					while(x>=0&&row+y<8)
					{
						
						if(flag[row+y][x])
						{
							JButton jButton3=button[row+y][x];
							jButton3.setBackground(Color.gray);
							
							flag[row+y][x]=false;
							index[num][0]=row+y;
							index[num][1]=x;
							num++;
							add++;
						}
						x--;
						y++;
					}
					x=i+1;
					y=1;
					while(x<8&&row+y<8)
					{
						if(flag[row+y][x])
						{
							JButton jButton3=button[row+y][x];
							jButton3.setBackground(Color.gray);
						
							flag[row+y][x]=false;
							index[num][0]=row+y;
							index[num][1]=x;
							num++;
							add++;
						}
						x++;
						y++;
					}
					break;
				}
			}
				
		}
		if(p)
		{
			row++;
			p=false;
			if(row!=8)
			{
				
				for(int j=row;j<8;j++)
				{
					int state=0;
					for(int i=0;i<8;i++)
					{
						if(flag[j][i])
						{
							state=1;
							break;
						}
					}
					if(state==0)
					{
						this.ErrorMesseger("挑战失败!");
						break;
					}
				}
				
			}
			
		}
	}
	public void ErrorMesseger(String string)
    {
    	JOptionPane.showMessageDialog(null,string,"警告",JOptionPane.ERROR_MESSAGE);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("back"))
		{
			if(row-1>=0)
			{
				row--;
	
				if(add==0)
				{
					add=(Integer) as.get(as.size()-1);
					as.remove(as.size()-1);
				}
				for(int i=0;i<add;i++)
				{
					num--;
					JButton jButton=button[index[num][0]][index[num][1]];
					jButton.setBackground(Color.white);
					flag[index[num][0]][index[num][1]]=true;
				}
				add=0;
			}
			
		}
		else if(e.getActionCommand().equals("next"))
		{
			//System.out.print(row+" ");
			this.gaming();
			
			
		}
	}


	public void gaming()
	{
		boolean l=true;
		for(int i=history[row]+1;i<8;i++){
			if(flag[row][i])
			{
	
				JButton jButton=button[row][i];
				history[row]=i;
				l=false;
				//System.out.println(i+" ");
				if(add!=0)as.add(add);
				add=0;
				
					jButton.setBackground(Color.green);
					index[num][0]=row;
					index[num][1]=i;
					num++;
					add++;
					flag[row][i]=false;
				for(int j=0;j<8;j++)
				{
					if(flag[row][j])
					{
						JButton jButton2=button[row][j];
						jButton2.setBackground(Color.gray);
					
					     index[num][0]=row;
							index[num][1]=j;
							num++;
							add++;
						flag[row][j]=false;
					}
				}
				//所在列全部锁定不可选状态
				for(int j=row+1;j<8;j++)
				{
					
					if(flag[j][i])
					{
						JButton jButton2=button[j][i];
						jButton2.setBackground(Color.gray);
					
						flag[j][i]=false;
						index[num][0]=j;
						index[num][1]=i;
						num++;
						add++;
					}
					
				}
				int x=i-1,y=1;
				while(x>=0&&row+y<8)
				{
					
					if(flag[row+y][x])
					{
						JButton jButton3=button[row+y][x];
						jButton3.setBackground(Color.gray);
						
						flag[row+y][x]=false;
						index[num][0]=row+y;
						index[num][1]=x;
						num++;
						add++;
					}
					x--;
					y++;
				}
				x=i+1;
				y=1;
				while(x<8&&row+y<8)
				{
					if(flag[row+y][x])
					{
						JButton jButton3=button[row+y][x];
						jButton3.setBackground(Color.gray);
					
						flag[row+y][x]=false;
						index[num][0]=row+y;
						index[num][1]=x;
						num++;
						add++;
					}
					x++;
					y++;
				}	
			}
				
		}
		
		if(l)this.back();
		else {
			row++;
			if(row!=8)
			{
				
				for(int j=row;j<8;j++)
				{
					int state=0;
					for(int i=0;i<8;i++)
					{
						if(flag[j][i])
						{
							state=1;
							
							break;
						}
					}
					if(state==0)
					{
						this.back();
						break;
					}
				}
				
				
			}	
		}
			
	}
	public void back()
	{
		if(row-1>=0)
		{
			//System.out.println("回退");
			history[row]=-1;
			row--;
		    
			if(add==0)
			{
				add= as.get(as.size()-1);
				as.remove(as.size()-1);
			}
			
			for(int i=0;i<add;i++)
			{
				num--;
				JButton jButton=button[index[num][0]][index[num][1]];
				jButton.setBackground(Color.white);
				flag[index[num][0]][index[num][1]]=true;
				
			}
			add=0;
		}
	
	}
	public void flush()
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				JButton jButton=button[i][j];
				jButton.setBackground(Color.white);
				flag[i][j]=true;
			}
		}
		num=0;
		add=0;
		for(int i=1;i<8;i++)
		{
			history[i]=-1;
		}
		as.removeAllElements();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(history[0]<8)
		{
			
			while(row<8)
			{
				this.gaming();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			row=0;
			this.flush();
		}
	}
}
