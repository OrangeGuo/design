package caculator;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculate implements ActionListener,MouseListener{

	/**
	 * ������:������
	 * ��������:���Ϊ
	 * ��ʼ����:16.12.19
	 */
	/*swing���*/
	JButton buttons[],button=null,nextButton,backButton;
	JFrame jFrame;
	JPanel northPanel,jPanel,southPanel,jPanel2;
    JLabel jLabel,jLabel2,jLabel3;
	Font font;
	String content,name[];
	Vector<Float> numbers=null;
	Vector<Character> chars=null;
	int tag=0;
    boolean dot_flag=false,cont=false,error=false,zero=false;
    int n=0,bracke=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Calculate calculate=new Calculate();
	}
	public Calculate()
	{
		/*�������ʼ������������,ע�����*/
		numbers=new Vector<Float>();
		chars=new Vector<Character>();
		content="";
		font=new Font("����",Font.BOLD,30);
		jFrame=new JFrame();
		northPanel=new JPanel(new GridLayout(1,1));
		southPanel=new JPanel(new GridLayout(1,3));
		jPanel=new JPanel(new GridLayout(4,4));
		jPanel2=new JPanel(new GridLayout(6,1));
	    jLabel=new JLabel("0",JLabel.RIGHT);
	    jLabel2=new JLabel("���ջ",JLabel.LEFT);
	    jLabel3=new JLabel("��ֵջ",JLabel.LEFT);
	    jLabel2.setFont(font);
	    jLabel3.setFont(font);
		buttons=new JButton[19];
		String name[]={"0","1","2","3","4","5","6","7","8","9",".","+","-","*","/","=","(",")","���"};
		this.name=name;
		nextButton=new JButton("��һ��");
		backButton=new JButton("����");
		nextButton.addActionListener(this);
		backButton.addActionListener(this);
		font=new Font("����",Font.BOLD,25);
		nextButton.setFont(font);
		backButton.setFont(font);
		font=new Font("����",Font.BOLD,30);
		for(int i=0;i<19;i++)
		{
			buttons[i]=new JButton(name[i]);
			buttons[i].setFont(font);
			buttons[i].setForeground(Color.black);
			buttons[i].addActionListener(this);
			buttons[i].setBackground(Color.lightGray);
			buttons[i].addMouseListener(this);
		}
		buttons[18].setForeground(Color.red);
		font=new Font("����",Font.BOLD,40);
	    jLabel.setFont(font);
	    jLabel.setForeground(Color.black);
		northPanel.setBackground(Color.white);
		northPanel.add(jLabel);
		jPanel.setBackground(Color.white);
		jPanel.add(buttons[1]);
		jPanel.add(buttons[2]);
		jPanel.add(buttons[3]);
		jPanel.add(buttons[11]);
		jPanel.add(buttons[4]);
		jPanel.add(buttons[5]);
		jPanel.add(buttons[6]);
		jPanel.add(buttons[12]);
		jPanel.add(buttons[7]);
		jPanel.add(buttons[8]);
		jPanel.add(buttons[9]);
		jPanel.add(buttons[13]);
		jPanel.add(buttons[0]);
		jPanel.add(buttons[10]);
		jPanel.add(buttons[14]);
		jPanel.add(buttons[15]);
		for(int i=10;i<=15;i++)buttons[i].setEnabled(false);
		buttons[17].setEnabled(false);
		JPanel tempJPanel=new JPanel();
		tempJPanel.add(nextButton);
		tempJPanel.add(backButton);
		jPanel2.add(jLabel2);
		jPanel2.add(jLabel3);
		jPanel2.add(tempJPanel);
		southPanel.setBackground(Color.white);
		southPanel.add(buttons[16]);
		southPanel.add(buttons[17]);
		southPanel.add(buttons[18]);
		jFrame.setSize(350,400);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.setTitle("��������ʾ");
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(northPanel,BorderLayout.NORTH);
		jFrame.add(jPanel);
		jFrame.add(southPanel,BorderLayout.SOUTH);
	}
	public boolean verify()
	{
		int n=0;
		for(int i=0;i<content.length();i++)
		{
			if(content.charAt(i)=='(')n++;
			else if(content.charAt(i)==')')n--;
		}
		if(n==0)
			return true;
		else 
			return false;
	}
	public int  check(int i)
	{
		String tString="";
		if(!cont)
		{
			char temp=content.charAt(i);
			if(temp=='(')
			{
				chars.add(temp);
				i++;
				if(i<content.length()&&content.charAt(i)==')')
				{
					error=true;
					jLabel.setText("�����ڲ���Ϊ��!");
					jLabel.setForeground(Color.red);
					nextButton.setEnabled(false);
				}
			}
			else if(temp==')')
			{
				if(chars.get(chars.size()-1)=='(')
				{
					chars.remove(chars.size()-1);
					i++;
				}
				else cont=true;
				
			}
			else if(temp>='0'&&temp<='9') 
	       {
				while(temp>='0'&&temp<='9')
				{
					tString+=temp;
					i++;
					if(i<content.length())
					{
						temp=content.charAt(i);
					}
					else 
						break;
					if(temp=='.')
					{
						tString+=temp;
						i++;
						if(i<content.length())temp=content.charAt(i);
						else break;
					}
				}
				if(tString!="")
				{
					float num=Float.parseFloat(tString);
					if(numbers.size()!=0)
					{
						float num2=numbers.get(numbers.size()-1);
						if(chars.size()!=0)
						{
							char c=chars.get(chars.size()-1);
							if(c=='*')
							{
								num=num2*num;
								numbers.remove(numbers.size()-1);
								chars.remove(chars.size()-1);
								if(i==content.length()||content.charAt(i)==')'||content.charAt(i)=='+'||content.charAt(i)=='-')
								cont=true;
							}
							else if(c=='/')
							{
								if(num!=0)
								{
									num=num2/num;
									numbers.remove(numbers.size()-1);
									chars.remove(chars.size()-1);
									if(i==content.length()||content.charAt(i)==')'||content.charAt(i)=='+'||content.charAt(i)=='-')
									cont=true;
								}
								else {
									nextButton.setEnabled(false);
									error=true;
									jLabel.setText("������Ϊ0!");
									jLabel.setForeground(Color.red);
			
								}
							}
							else if(c=='+')	
							{
								if(i==content.length())
								{
									num=num+num2;
									numbers.remove(numbers.size()-1);
									chars.remove(chars.size()-1);
									cont=true;
								}
								else if(content.charAt(i)==')'||content.charAt(i)=='+'||content.charAt(i)=='-')
								{
									num=num+num2;
									numbers.remove(numbers.size()-1);
									chars.remove(chars.size()-1);
									cont=true;
								}
							}
							else if(c=='-')	
							{
								if(i==content.length())
								{
									num=num2-num;
									numbers.remove(numbers.size()-1);
									chars.remove(chars.size()-1);
									cont=true;
								}
								else if(content.charAt(i)==')'||content.charAt(i)=='+'||content.charAt(i)=='-')
								{
									num=num2-num;
									numbers.remove(numbers.size()-1);
									chars.remove(chars.size()-1);
									cont=true;
								}
							}
						}
					}
				 numbers.add(num);
				}
			}

			else if(temp=='*'||temp=='/'||temp=='+'||temp=='-')
			{
				chars.add(temp);
				i++;
			}
		}
		else 
		{
			
			float num=numbers.get(numbers.size()-1);
			numbers.remove(numbers.size()-1);
			if(numbers.size()!=0)
			{
				float num2=numbers.get(numbers.size()-1);
				if(chars.size()!=0)
				{
					char c=chars.get(chars.size()-1);
					if(c=='*')
					{
						num=num2*num;
						numbers.remove(numbers.size()-1);
						chars.remove(chars.size()-1);
					}
					else if(c=='/')
					{
						if(num!=0)
						{
							num=num2/num;
							numbers.remove(numbers.size()-1);
							chars.remove(chars.size()-1);
						}
						else {
							jLabel.setText("������Ϊ0!");
							error=true;
							jLabel.setForeground(Color.red);
							nextButton.setEnabled(false);
						}
					}
					else if(c=='+')	
					{
						if(i==content.length())
						{
							num=num+num2;
							numbers.remove(numbers.size()-1);
							chars.remove(chars.size()-1);
						}
						else if(content.charAt(i)==')'||content.charAt(i)=='+'||content.charAt(i)=='-')
						{
							num=num+num2;
							numbers.remove(numbers.size()-1);
							chars.remove(chars.size()-1);
						}
					}
					else if(c=='-')	
					{
						if(i==content.length())
						{
							num=num2-num;
							numbers.remove(numbers.size()-1);
							chars.remove(chars.size()-1);
						}
						else if(content.charAt(i)==')'||content.charAt(i)=='+'||content.charAt(i)=='-')
						{
							num=num2-num; 
							numbers.remove(numbers.size()-1);
							chars.remove(chars.size()-1);
						}
					}
					else if(c=='(')cont=false;
				}
			}
			else 
			{
				cont=false;
			}
		 numbers.add(num);
		}
		
		tString="";
		for(int j=0;j<chars.size();j++)
		{
			tString+=chars.get(j);
			tString+=' ';
		}
		jLabel2.setText(tString);
		tString="";
		for(int j=0;j<numbers.size();j++)
		{
			tString+=String.valueOf(numbers.get(j));
			tString+=' ';
		}
		jLabel3.setText(tString);
        return i;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(backButton))
		{
			zero=false;
			for(int i=0;i<19;i++)
			    buttons[i].setBackground(Color.lightGray);
			content="";
			n=0;
			bracke=0;
			tag=0;
			dot_flag=false;
			jLabel.setText("0");
			font=new Font("����",Font.BOLD,40);
			jLabel.setFont(font);
			jFrame.remove(jPanel2);
			jLabel.setForeground(Color.black);
			jLabel2.setText("���ջ");
			jLabel3.setText("��ֵջ");
			jFrame.add(jPanel);
			jFrame.add(southPanel,BorderLayout.SOUTH);
			jPanel.updateUI();
			southPanel.updateUI();
			chars.removeAllElements();
			numbers.removeAllElements();
			nextButton.setEnabled(true);
			for(int i=0;i<10;i++)
				buttons[i].setEnabled(true);
			for(int i=10;i<=15;i++)
				buttons[i].setEnabled(false);
			buttons[16].setEnabled(true);
			buttons[17].setEnabled(false);
			buttons[18].setEnabled(true);
		}
		else if(e.getSource().equals(nextButton))
		{
		
            if(n<content.length()||cont) 
            {
            	n=this.check(n);
               if(!error)
               {
            	   String tempString="";
               	for(int i=n;i<content.length();i++)
               	{
               		tempString+=content.charAt(i);
               	}
               	jLabel.setText(tempString);
               	if(tempString.length()<15)
               		font=new Font("����",Font.BOLD,40);
   				else 
   					font=new Font("����",Font.BOLD,20);
               }
               else font=new Font("����",Font.BOLD,40);
				jLabel.setFont(font);
            	
            }
            else if(chars.size()!=0)
            {
            	cont=true;
            	n=this.check(n);
            }
            else
            {
            	jLabel.setText(String.valueOf(numbers.get(0)));
            	if(jLabel.getText().length()<15)
					font=new Font("����",Font.BOLD,40);
				else
					font=new Font("����",Font.BOLD,20);
				jLabel.setFont(font);
				jLabel3.setText("");
            	nextButton.setEnabled(false);
            }
		}
	     else  if(e.getSource().equals(buttons[15]))
         {
        	 jFrame.remove(jPanel);
        	 jFrame.remove(southPanel);
        	 jFrame.add(jPanel2);
        	 jPanel2.updateUI();
        	 cont=false;
        	 if(!this.verify())
        	 {
        		 nextButton.setEnabled(false);
        		 jLabel.setText("����ȱʧ!");
        		jLabel.setForeground(Color.red);
        		font=new Font("����",Font.BOLD,40);
				jLabel.setFont(font);
        	 }
         }
         else {
     		for(int i=0;i<19;i++)
    		{
    			if(i!=15&&e.getSource().equals(buttons[i]))
    			{
    				if(i!=18)
    				{
    				
    					if(content.length()<31)
    					{
    						if(content.equals("0"))
    						{
    							if(i==0)zero=true;
    							else if(zero)
    							{
    								content+=name[i];
    							}
    							else {
									content=name[i];
								}
    						}
    						else
    						   content+=name[i];
    					}
    					
    					if(i>=0&&i<=9)
    					{
    						for(int j=11;j<=15;j++)buttons[j].setEnabled(true);
    						if(bracke==0)buttons[15].setEnabled(true);
    						else buttons[15].setEnabled(false);
    						buttons[16].setEnabled(false);
    						if(bracke>0&&tag==3)buttons[17].setEnabled(true);
    						else {
    							buttons[17].setEnabled(false);
							}
    						if(tag==1)tag++;
    							
    					}
    					else if(i==10)
    					{
    						for(int j=10;j<=17;j++)buttons[j].setEnabled(false);
    					}
    					else if(i>10&&i<=14)
    					{
    						for(int j=10;j<=15;j++)buttons[j].setEnabled(false);
    						buttons[16].setEnabled(true);
    						buttons[17].setEnabled(false);
    						for(int j=0;j<10;j++)buttons[j].setEnabled(true);
    						if(tag==2)tag++;
    					}
    					else if(i==16)
    					{
    						bracke++;
    						for(int j=0;j<10;j++)buttons[j].setEnabled(true);
    						if(tag==0)
    							tag++;
    					}
    					else if(i==17)
    					{
    						bracke--;
    						if(bracke==0)
    						{
    							buttons[17].setEnabled(false);
    							tag=0;
    						}
    						for(int j=0;j<11;j++)buttons[j].setEnabled(false);
    						for(int j=11;j<16;j++)buttons[j].setEnabled(true);
    						if(bracke==0)buttons[15].setEnabled(true);
    						else buttons[15].setEnabled(false);
    					}
    					if(i>=0&&i<10)
    					{
    						if(!dot_flag)buttons[10].setEnabled(true);
    						dot_flag=true;
    					
    					}
    					else if(i!=10)
    					{
    						dot_flag=false;
    						buttons[10].setEnabled(false);
    					}
    				
    				}
    				else
    				{
    					for(int j=0;j<10;j++)buttons[j].setEnabled(true);
    					for(int j=10;j<16;j++)buttons[j].setEnabled(false);
    					buttons[16].setEnabled(true);
    					buttons[17].setEnabled(false);
    					content="0";
    					zero=false;
    					bracke=0;
    					tag=0;
    				}
    				jLabel.setText(content);
    				if(content.length()<15)
    					font=new Font("����",Font.BOLD,40);
    				else
    					font=new Font("����",Font.BOLD,20);
    				jLabel.setFont(font);
    				break;
    			}
    		}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
          for(int i=0;i<19;i++)
          {
        	  if(e.getSource().equals(buttons[i]))
        	  {
        		  button=buttons[i];
        		  buttons[i].setBackground(Color.white);
        		  break;
        	  }
          }
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
         if(button!=null)
         {
        	 button.setBackground(Color.lightGray);
        	 button=null;
         }
	}

}
