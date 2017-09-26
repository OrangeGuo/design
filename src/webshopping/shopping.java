package webshopping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.AllPermission;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.xml.crypto.Data;

public class shopping implements MouseListener,ActionListener{

	/**
	 * 程序功能:模拟在线网站购物
	 * 程序作者:郭大为
	 * 开始日期:16.11.21
	 */
	/*swing组件声明*/
	JLabel errorlabel;
	JFrame jFrame=null;
	JPanel jPanel=null,northJPanel=null,southJPanel=null,errorpanel;
	JButton jButton=null,jButton2=null,preButton=null,nextButton=null,jumpButton=null,backButton=null,button[],errorbutton;
	JTextField jTextField=null;
	Font font=null;
	ManagePane mp=null;
	StorePanel storePanel=null,stores[]=null;
	/*其他变量*/
	StoreList list,list_sale;
	boolean flag=true,update_flag=true,flush_flag=true;
	int dot=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       shopping shopping=new shopping();
	}
	public shopping(){
		/*各组件初始化*/
	this.Initial();
	    button=new JButton[5];
		jTextField=new JTextField(10);
		jPanel=new JPanel();
		backButton=new JButton("返回");
		stores=new StorePanel[5];
		errorlabel=new JLabel("未查询到相关结果!",JLabel.CENTER);
		jButton=new JButton("搜索");
		jButton2=new JButton("退出");
		preButton=new JButton("上一页");
		jumpButton=new JButton("管理");
		nextButton=new JButton("下一页");
		errorbutton=new JButton("返回列表");
		font=new Font("宋体",Font.BOLD, 25);
		errorlabel.setFont(font);
		errorlabel.setForeground(Color.red);
		errorbutton.setFont(font);
		northJPanel=new JPanel();
		southJPanel=new JPanel();
		errorpanel=new JPanel();

		jFrame=new JFrame();
		font=new Font("新宋体",Font.CENTER_BASELINE, 30);
	
		jTextField.setText("搜索商品");
		jTextField.setForeground(Color.LIGHT_GRAY);
        /*注册监听*/
		font=new Font("宋体", 1, 20);
		for(int i=0;i<5;i++)
		{
			button[i]=new JButton("更多");
			button[i].setForeground(Color.red);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}
		backButton.addActionListener(this);
		jTextField.addMouseListener(this);
		jButton.addActionListener(this);
		jButton2.addActionListener(this);
		jumpButton.addActionListener(this);
		preButton.addActionListener(this);
		nextButton.addActionListener(this);
		errorbutton.addActionListener(this);
		/*组合组件及设置各组件属性*/
		preButton.setEnabled(false);

		this.flush(list);
	    jPanel.setBackground(Color.white);
	    jPanel.setLayout(new GridLayout(5,1));
	    northJPanel.setBackground(Color.LIGHT_GRAY);
		southJPanel.setBackground(Color.LIGHT_GRAY);
	    northJPanel.add(jButton2);
	    northJPanel.add(jTextField);
	    northJPanel.add(jButton);
	    southJPanel.add(preButton);
	    southJPanel.add(nextButton);
	    southJPanel.add(jumpButton);
	     errorpanel.add(errorlabel);
	     errorpanel.add(errorbutton);
	     errorpanel.setBackground(Color.white);
	    jFrame.add(jPanel);
	    jFrame.add(northJPanel,BorderLayout.NORTH);
	    jFrame.add(southJPanel,BorderLayout.SOUTH);
		jFrame.setTitle("地虎");
		jFrame.setSize(300,600);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		Image a=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/shop.jpg"));
		jFrame.setIconImage(a);
		mp=new ManagePane(backButton,list);
	
	}
	public void Initial()
	{
		
		String filename="C://Users//Administrator//Desktop//data.txt";
		FileReader fr=null;
		
		BufferedReader br=null;
         try {
			fr=new FileReader(filename);
			br=new BufferedReader(fr);
			String s="";
			int num=1;
		
			try {
				while((s=br.readLine())!=null)
				{
					
                   int i=0,j,cre;
                   String temp="",name="",tempString="";
                   while(s.charAt(i)!='@')
                   {
                	   temp+=s.charAt(i);
                	   i++;
                   }
                   i++;
                   for( j=1;temp.charAt(j)!='#';j++)
                        name+=temp.charAt(j);
                   j++;
                   for(;temp.charAt(j)!='#';j++)
                       tempString+=temp.charAt(j);
                   cre=Integer.valueOf(tempString);
                   store store=new store(num, cre, name);
                   if(num==1)
                        list=new StoreList(store);
                   else {
					StoreList storeList=new StoreList(store);
					list.add(storeList);
				}
                   
                  num++;
				
					while(i<s.length())
					{
						temp="";
						name="";
						int price,sale,reserve;
						while(s.charAt(i)!='@')
		                {
		                	 temp+=s.charAt(i);
		                	 i++;
		                }
		                i++;
		                for( j=1;temp.charAt(j)!='#';j++)
	                        name+=temp.charAt(j);
	                    j++;
	                    tempString="";
	                    for( ;temp.charAt(j)!='#';j++)
	                        tempString+=temp.charAt(j);
	                    j++;
	                    price=Integer.parseInt(tempString);
	                    tempString="";
	                    for( ;temp.charAt(j)!='#';j++)
	                        tempString+=temp.charAt(j);
	                    j++;
	                    sale=Integer.parseInt(tempString);
	                    tempString="";
	                    for( ;j<temp.length();j++)
	                        tempString+=temp.charAt(j);
	                    reserve=Integer.parseInt(tempString);
	                    goods goods=new goods(name, price, sale, reserve);
	                    store.products.add(goods);
	                    }
                   
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			try {
				br.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void save(){
		StoreList tempList;
		if(list!=null)
			tempList=list.sort_num();
		else 
			tempList=null;
		
		String filename="C:Users//Administrator//Desktop//data.txt";
		FileWriter fw=null;
		BufferedWriter bw=null;
         try {
			try {
				fw=new FileWriter(filename);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			bw=new BufferedWriter(fw);
			for(int i=0;tempList!=null&&i<tempList.size();i++)
			{
				store store=tempList.get(i+1);
				String content="#";
				content+=store.name;
				content+="#";
				content+=String.valueOf(store.cred);
				content+="#@";
				for(int j=0;j<store.products.size();j++)
				{
					content+="#";
					goods goods=store.products.get(j);
					content+=goods.name;
					content+="#";
					content+=String.valueOf(goods.price);
					content+="#";
					content+=String.valueOf(goods.sale);
					content+="#";
					content+=String.valueOf(goods.reserve);
					content+="@";
				}
				content+="\r\n";
				bw.write(content);
			}
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	}
	//刷新数据
	public void flush(StoreList list)
	{
		if(list!=null&&flush_flag)
			list=list.renew();
		jPanel.removeAll();
		if(list!=null&&dot==list.size())
			dot-=5;
		for(int i=0;list!=null&&i<list.size()-dot&&i<5;i++)
		{
		    stores[i]=new StorePanel(list.get(i+dot+1),button[i]);
		    jPanel.add(stores[i]);
		}
		if(list==null||dot+5>=list.size())
			nextButton.setEnabled(false);
		else 
			nextButton.setEnabled(true);
		jPanel.updateUI();
	}
	public void Update()
	{
		if(update_flag)
		{
			jFrame.remove(jPanel);
			jFrame.remove(northJPanel);
			jFrame.remove(southJPanel);
	        jFrame.add(mp);
			jFrame.repaint();
			mp.updateUI();
			update_flag=false;
		}
		else {
			list=mp.getList();
			this.flush(list);
	        jFrame.remove(mp);
			jFrame.add(jPanel);
			jFrame.add(northJPanel,BorderLayout.NORTH);
			jFrame.add(southJPanel,BorderLayout.SOUTH);
			jPanel.updateUI();
			northJPanel.updateUI();
			southJPanel.updateUI();
			update_flag=true;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jButton2))
		{
			this.save();
			jFrame.dispose();
		}
		else if(e.getSource().equals(jumpButton))
		{
			this.Update();
		}
		else if(e.getSource().equals(backButton))
		{
			if(list!=null)list=list.renew();
			if(backButton.getText().equals("上一层"))
			{
				backButton.setText("返回");
				mp.jTextField.setText("");
				mp.renew();
				
			}
			else 
			{
				flush_flag=true;
				dot=0;
				this.Update();
			}
		}
		else if(e.getSource().equals(preButton))
		{
			dot-=5;
			if(flush_flag)
    		{
    			this.flush(list);
    		}
    		else 
    		{
    			this.flush(list_sale);
    		}
			if(dot==0)preButton.setEnabled(false);
		}
		else if(e.getSource().equals(errorbutton))
		{
			flush_flag=true;
			this.flush(list);
		}
		else if(e.getSource().equals(nextButton))
		{
			preButton.setEnabled(true);
			dot+=5;
			if(flush_flag)
    		{
    			this.flush(list);
    		}
    		else 
    		{
    			this.flush(list_sale);
    		}
		}
		else if(e.getSource().equals(jButton))
		{
			String product=jTextField.getText();
			list_sale=list.sort_sale(product);
			dot=0;
			preButton.setEnabled(false);
			if(list_sale!=null)
			{
				this.flush_flag=false;
				this.flush(list_sale);
			
			}
			else {
				jPanel.removeAll();
				jPanel.add(errorpanel);
				jPanel.updateUI();
				nextButton.setEnabled(false);
			}
		}
	    for(int i=0;i<5;i++)
	    {
	    	if(e.getSource().equals(button[i]))
	    	{
	    		store store=null;
	    		if(flush_flag)
	    		{
	    			this.flush(list);
	    		 store=list.get(i+dot+1);
	    		}
	    		else 
	    		{
	    			this.flush(list_sale);
	    			 store=list_sale.get(i+dot+1);
	    		}
	    		
	    		PurchasePanel pp=new PurchasePanel(store);	
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
		if(e.getSource().equals(jTextField)&&flag)
		{
			jTextField.setForeground(Color.black);
			jTextField.setText("");
			flag=false;
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
class ManagePane extends JPanel implements ActionListener{
	JFrame jFrame=null;
	JPanel topJPanel=null,mainPanel=null,jPanels[]=null,jPanel,notionPanel;
	JLabel jLabel=null,jLabel2=null,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabels[]=null,title,notion;
	Font font=null;
	JTextField jTextField=null,table[][],cred,jtf;
	JButton applyButton=null,addButton=null,detButton=null,ediButton=null,backButton=null,button,button2,button3
			,buttons[]=null;
	StoreList list=null;
    String ediString=null;
    int deleteflag;
	public void paint(Graphics g)
	{
			super.paint(g);
	}
	public ManagePane(JButton jButton,StoreList list){
		this.list=list;
		ediString="";
		topJPanel=new JPanel();
		mainPanel=new JPanel();
		notionPanel=new JPanel();
		if(list!=null)
			title=new JLabel("当前店铺数:"+String.valueOf(list.size()),JLabel.CENTER);
		else 
			title=new JLabel("当前店铺数:"+String.valueOf(0),JLabel.CENTER);
		jPanels=new JPanel[6];
		cred=new JTextField(7);
		
	 jtf=new JTextField(5);
		table=new JTextField[6][3];
		font=new Font("宋体",Font.BOLD, 15);
		notion=new JLabel("名称不超过5个字符，数值部分小于1000",JLabel.CENTER);
		notion.setFont(font);
		notion.setForeground(Color.red);
        notionPanel.add(notion);
        font=new Font("宋体",Font.BOLD, 25);
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<3;j++)
			{
				JTextField jField=new JTextField(5);
				jField.setText("0");
				jField.setFont(font);
				table[i][j]=jField;
			}
		}
		this.setLayout(new BorderLayout());
		jLabels=new JLabel[6];
		String name[]={"衣服","裤子","鞋子","手套","袜子","帽子"};
		font=new Font("宋体",Font.BOLD, 20);
		title.setFont(font);
		for(int i=0;i<6;i++)
		{
			jLabels[i]=new JLabel(name[i],JLabel.CENTER);
			jLabels[i].setFont(font);
			jLabels[i].setForeground(Color.LIGHT_GRAY);
			jPanels[i]=new JPanel();
			jPanels[i].setLayout(new GridLayout(1,5));
			
			jPanels[i].setBackground(Color.white);
		}
	    backButton=jButton;
	    buttons=new JButton[6];
		font=new Font("宋体",Font.BOLD, 35);
	    for(int i=0;i<6;i++)
	    {
	    	buttons[i]=new JButton("+");
	    	buttons[i].setFont(font);
	    	buttons[i].addActionListener(this);
	    }
		applyButton=new JButton("确定");
		addButton=new JButton("增加店铺");
		detButton=new JButton("删除店铺");
		ediButton=new JButton("修改店铺");
		button=new JButton("添加");
		button2=new JButton("查找");
		button3=new JButton("查找");
		jLabel=new JLabel("店铺管理");
		jLabel3=new JLabel("商品",JLabel.CENTER);
		jLabel4=new JLabel("价格",JLabel.CENTER);
		jLabel5=new JLabel("销量",JLabel.CENTER);
		jLabel6=new JLabel("库存",JLabel.CENTER);
		jLabel7=new JLabel("操作",JLabel.CENTER);
		jPanel=new JPanel();
		jLabel2=new JLabel("店铺名称(不超过五个字符)",JLabel.CENTER);
		jTextField=new JTextField(5);
		font=new Font("宋体",Font.BOLD, 25);
		addButton.setBackground(Color.white);
		detButton.setBackground(Color.white);
		ediButton.setBackground(Color.white);
		addButton.setFont(font);
		detButton.setFont(font);
		ediButton.setFont(font);
		addButton.setForeground(Color.BLUE);
		detButton.setForeground(Color.blue);
		ediButton.setForeground(Color.blue);
	    topJPanel.add(backButton);
	    topJPanel.add(jLabel);
	    topJPanel.add(applyButton);
	    mainPanel.setBackground(Color.white);
	    mainPanel.setLayout(new GridLayout(14,1));
	    mainPanel.add(title);
	    mainPanel.add(addButton);
	    mainPanel.add(detButton);
	    mainPanel.add(ediButton);
	    jPanel.setLayout(new GridLayout(1,5));
		jPanel.add(jLabel3);
		jPanel.add(jLabel4);
		jPanel.add(jLabel5);
		jPanel.add(jLabel6);
		jPanel.add(jLabel7);
		applyButton.addActionListener(this);
		addButton.addActionListener(this);
		detButton.addActionListener(this);
		ediButton.addActionListener(this);
		button.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		if(list==null)
		{
			detButton.setEnabled(false);
			ediButton.setEnabled(false);
		}
		font=new Font("新宋体",Font.CENTER_BASELINE, 30);
		jLabel.setFont(font);
		this.add(topJPanel,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(addButton))
		{
			backButton.setText("上一层");
			applyButton.setText("添加");
			font=new Font("宋体",Font.BOLD, 20);
			jLabel2.setFont(font);
			font=new Font("宋体",Font.BOLD, 30);
			jTextField.setFont(font);
			font=new Font("宋体",Font.BOLD, 20);
			jLabel3.setFont(font);
			jLabel4.setFont(font);
			jLabel5.setFont(font);
			jLabel6.setFont(font);
			jLabel7.setFont(font);
			jLabel2.setText("请输入商铺名称");
			String name[]={"衣服","裤子","鞋子","手套","袜子","帽子"};
			this.mainPanel.removeAll();
			this.mainPanel.add(jLabel2);
			this.mainPanel.add(jTextField);
			jTextField.setText("");
			this.mainPanel.add(jPanel);
			for(int i=0;i<6;i++)
			{
				jLabels[i].setForeground(Color.lightGray);
				jPanels[i].add(jLabels[i]);
				for(int j=0;j<3;j++)
				{
					table[i][j].setForeground(Color.lightGray);
					table[i][j].setEditable(false);
					table[i][j].setText("");
					jPanels[i].add(table[i][j]);
				}
				buttons[i].setText("+");
				jPanels[i].add(buttons[i]);
				this.mainPanel.add(jPanels[i]);
			}
			JPanel tjPanel=new JPanel();
			jLabel2=new JLabel("信誉度",JLabel.CENTER);
			jLabel2.setFont(font);
			tjPanel.add(jLabel2);
			cred.setText("1到99之间");
			tjPanel.add(cred);
			this.mainPanel.add(tjPanel);
			this.mainPanel.add(notionPanel);
			this.mainPanel.updateUI();
			
		}
		else if(e.getSource().equals(detButton))
		{
			backButton.setText("上一层");
			mainPanel.removeAll();
			jtf.setText("");
			jLabel2=new JLabel("请输入商铺编号");
			font=new Font("宋体",Font.BOLD, 20);
			jLabel2.setFont(font);
			JPanel tjPanel=new JPanel();

			tjPanel.add(jLabel2);
			tjPanel.add(jtf);
			tjPanel.add(button2);
			tjPanel.setBackground(Color.white);
			mainPanel.add(tjPanel);
			mainPanel.updateUI();
		}
		else if(e.getSource().equals(button2))
		{
			String temp=jtf.getText();
			if(temp.length()>2||temp.length()==0||!this.judge(temp, 2))
			{
				this.ErrorMesseger("输入有误!");
			}
			else if(Integer.parseInt(temp)>list.size()){
				this.ErrorMesseger("该店铺不存在!");
			}
			else{
				mainPanel.removeAll();
				store store=list.get(Integer.parseInt(temp));
				jLabel2=new JLabel("店铺名称:"+store.name,JLabel.CENTER);
				font=new Font("宋体",Font.BOLD, 20);
				jLabel2.setFont(font);
			    mainPanel.add(jLabel2);
			    jLabel2=new JLabel("信誉度:"+String.valueOf(store.cred),JLabel.CENTER);
				jLabel2.setFont(font);
				mainPanel.add(jLabel2);
				String tabString="商品:";
				for(int i=0;i<store.products.size();i++)
				{
					goods goods=store.products.get(i);
					tabString+=goods.name;
					tabString+=" ";
				}
				jLabel2=new JLabel(tabString,JLabel.CENTER);
				font=new Font("宋体",Font.BOLD, 15);
				jLabel2.setFont(font);
			
				mainPanel.add(jLabel2);
			    applyButton.setText("删除");
			    deleteflag=Integer.parseInt(temp);
			}
		}
		else if(e.getSource().equals(ediButton))
		{
			backButton.setText("上一层");
			mainPanel.removeAll();
			jtf.setText("");
			jLabel2=new JLabel("请输入商铺编号");
			font=new Font("宋体",Font.BOLD, 20);
			jLabel2.setFont(font);
			JPanel tjPanel=new JPanel();
			tjPanel.add(jLabel2);
			tjPanel.add(jtf);
			tjPanel.add(button3);
			tjPanel.setBackground(Color.white);
			mainPanel.add(tjPanel);
			mainPanel.updateUI();
		}
		else if(e.getSource().equals(button3))
		{
			if(button3.getText().equals("查找"))
			{
				String temp=jtf.getText();
				if(temp.length()>2||temp.length()==0||!this.judge(temp, 2))
				{
					this.ErrorMesseger("输入有误!");
				}
				else if(Integer.parseInt(temp)>list.size()){
					this.ErrorMesseger("该店铺不存在!");
				}
				else {
					ediString=new String(temp);
					store store=list.get(Integer.parseInt(temp));
					jLabel2=new JLabel("店铺名称(不超过五个字符)",JLabel.CENTER);
					font=new Font("宋体",Font.BOLD, 20);
					jLabel2.setFont(font);
					font=new Font("宋体",Font.BOLD, 30);
					jTextField.setFont(font);
					jTextField.setText(store.name);
					font=new Font("宋体",Font.BOLD, 20);
					jLabel3.setFont(font);
					jLabel4.setFont(font);
					jLabel5.setFont(font);
					jLabel6.setFont(font);
					jLabel7.setFont(font);
					String name[]={"衣服","裤子","鞋子","手套","袜子","帽子"};
					this.mainPanel.removeAll();
					this.mainPanel.add(jLabel2);
					this.mainPanel.add(jTextField);
					this.mainPanel.add(jPanel);
					int flag=0;
					for(int i=0;i<6;i++)
					{
						goods goods=store.products.get(flag);
						jPanels[i].removeAll();
						if(jLabels[i].getText().equals(goods.name))
						{
							if(flag<store.products.size()-1)flag++;
							jLabels[i].setForeground(Color.black);
							table[i][0].setText(String.valueOf(goods.price));
							table[i][1].setText(String.valueOf(goods.sale));
							table[i][2].setText(String.valueOf(goods.reserve));
							for(int j=0;j<3;j++)
							{
								table[i][j].setEditable(true);
								table[i][j].setForeground(Color.black);
							}
							buttons[i].setText("-");
						}
						else {
							jLabels[i].setForeground(Color.lightGray);
							table[i][0].setText("0");
							table[i][1].setText("0");
							table[i][2].setText("0");
							for(int j=0;j<3;j++)
							{
								table[i][j].setEditable(false);
								table[i][j].setForeground(Color.lightGray);
							}
							buttons[i].setText("+");
						}
						jPanels[i].add(jLabels[i]);
						for(int j=0;j<3;j++)jPanels[i].add(table[i][j]);
						jPanels[i].add(buttons[i]);
						jPanels[i].updateUI();
						this.mainPanel.add(jPanels[i]);
					}
					JPanel tjPanel=new JPanel();
					jLabel2=new JLabel("信誉度",JLabel.CENTER);
					jLabel2.setFont(font);
					tjPanel.add(jLabel2);
					cred.setText(String.valueOf(store.cred));
					tjPanel.add(cred);
					this.mainPanel.add(tjPanel);
					this.mainPanel.add(notionPanel);
					this.mainPanel.updateUI();
					applyButton.setText("修改");
				}

			}
		}
      
		else if(e.getSource().equals(applyButton))
		{
			if(applyButton.getText().equals("修改"))
			{
				if(this.check())
				{
					store store=list.get(Integer.parseInt(ediString));
					store.cred=Integer.parseInt(cred.getText());
					store.name=new String(jTextField.getText());
					store.products.removeAllElements();
					String name[]={"衣服","裤子","鞋子","手套","袜子","帽子"};
					for(int i=0;i<6;i++)
					{
						if(buttons[i].getText().equals("-"))
						{
							int x=0,y=0,z=0;
							x=Integer.parseInt(table[i][0].getText());
							y=Integer.parseInt(table[i][1].getText());
							z=Integer.parseInt(table[i][2].getText());		
							goods goods=new goods(name[i], x, y, z);
							store.products.add(goods);
						}
					}
					mainPanel.removeAll();
					font=new Font("宋体",Font.BOLD, 20);
					jLabel2=new JLabel("修改成功!",JLabel.CENTER);
					jLabel2.setFont(font);
					mainPanel.add(jLabel2);
					mainPanel.updateUI();
					applyButton.setText("确定");
				}
			}
			else if(applyButton.getText().equals("删除"))
			{

				store store=list.get(deleteflag);
				store.num=0;
				list=list.renew();
				mainPanel.removeAll();
				jLabel2=new JLabel("删除成功!",JLabel.CENTER);
				font=new Font("宋体",Font.BOLD, 20);
				jLabel2.setFont(font);
				JPanel tjPanel=new JPanel();
				tjPanel.add(jLabel2);
			    tjPanel.setBackground(Color.white);
			    mainPanel.add(tjPanel);
			    mainPanel.updateUI();
			    applyButton.setText("确定");
			}
			else if(applyButton.getText().equals("添加"))
			{
				if(this.check())
				{
					int num;
					if(list!=null)
						 num=list.size()+1;
					else 
						num=1;
					int credi=Integer.parseInt(cred.getText());
					String nameString=jTextField.getText();
					store store=new store(num, credi, nameString);
					String name[]={"衣服","裤子","鞋子","手套","袜子","帽子"};
					for(int i=0;i<6;i++)
					{
						if(buttons[i].getText().equals("-"))
						{
							int x=0,y=0,z=0;
							x=Integer.parseInt(table[i][0].getText());
							y=Integer.parseInt(table[i][1].getText());
							z=Integer.parseInt(table[i][2].getText());		
							goods goods=new goods(name[i], x, y, z);
							store.products.add(goods);
						}
					}
					StoreList temp=new StoreList(store);
					if(list!=null)
						list.add(temp);
					else
						list=temp;
					mainPanel.removeAll();
					font=new Font("宋体",Font.BOLD, 20);
					jLabel2=new JLabel("添加成功!",JLabel.CENTER);
					jLabel2.setFont(font);
					mainPanel.add(jLabel2);
					
					mainPanel.updateUI();
					applyButton.setText("确定");
				}
			}
			else if(applyButton.getText().equals("确定")||applyButton.getText().equals("应用"))
			{
				this.renew();
				backButton.setText("返回");
				applyButton.setText("应用");
			}
		}
		
		for(int i=0;i<6;i++)
	    {
	        if(e.getSource().equals(buttons[i]))
	        {
	        	if(buttons[i].getText().equals("+"))
	        	{
	        		buttons[i].setText("-");
	        		jLabels[i].setForeground(Color.black);
	        		for(int j=0;j<3;j++)
	        		{
	        			table[i][j].setForeground(Color.black);
	        			table[i][j].setEditable(true);
	        		}
	        	}
	        	else if(buttons[i].getText().equals("-"))
	        	{
	        		buttons[i].setText("+");
	        		jLabels[i].setForeground(Color.lightGray);
	        		for(int j=0;j<3;j++)
	        		{
	        			table[i][j].setForeground(Color.lightGray);
	        			table[i][j].setEditable(false);
	        		}
	        	}
	        	break;
	        }
	    }
	}
	public void setList(StoreList list)
	{
		this.list=list;
	}
	public StoreList getList()
	{
		return list;
	}
	public void renew()
	{
		this.list=list.renew();
		title.setText("当前店铺数:"+String.valueOf(list.size()));
		mainPanel.removeAll();
		mainPanel.add(title);
		mainPanel.add(addButton);
		mainPanel.add(ediButton);
		mainPanel.add(detButton);
		if(list.size()==0)
		{
			ediButton.setEnabled(false);
			detButton.setEnabled(false);
		}
		else 
		{
			ediButton.setEnabled(true);
			detButton.setEnabled(true);
		}
		mainPanel.updateUI();
	}
	public boolean judge(String string ,int n)
	{
		for(int i=0;i<n&&i<string.length();i++)
		{
			if(string.charAt(i)>'9'||string.charAt(i)<'0')
			{
				return false;
			}
		}
		return true;
	}
    public void ErrorMesseger(String string)
    {
    	JOptionPane.showMessageDialog(null,"     "+string,"警告",JOptionPane.ERROR_MESSAGE);
    }
	public boolean check()
	{
		String string=jTextField.getText();
		if(string.length()>5)
		{
			this.ErrorMesseger("店铺名称字数过多!");
			return false;
		}
		else if(string.length()==0)
		{
			this.ErrorMesseger("店铺名称不能为空!");
			return false;
		}
		string=cred.getText();
		if(string.length()>2||string.length()==0)
		{
			this.ErrorMesseger("信誉度错误!");
			return false;
		}
		else if(!this.judge(string, 2))
		{
			this.ErrorMesseger("信誉度错误!");
			return false;
		}
		int sum=0;
		for(int i=0;i<6;i++)
		{
			if(buttons[i].getText().equals("-"))
			{
				for(int j=0;j<3;j++)
				{
					JTextField jField=table[i][j];
					string=jField.getText();
					if(string.length()>3||string.length()==0)
					{
						this.ErrorMesseger("商品信息填写错误!");
						return false;
					}
					if(!this.judge(string, 3))
					{
						this.ErrorMesseger("商品信息填写错误!");
						return false;
					}
				}
				if(Integer.parseInt(table[i][0].getText())==0)
				{
					this.ErrorMesseger(jLabels[i].getText()+"价格不能为0!");
					return false;
				}
				sum++;
			}
		}
		if(sum==0)
		{
			this.ErrorMesseger("店内商品不能为空!");
			return false;
		}
		return true;
	}

}
class StorePanel extends JPanel{
	 JPanel jPanel2=null;
	 JLabel jLabel=null,jLabel2=null,jLabel3=null;
	 JButton jButton=null,imageButton=null;
	 //重写函数
	 public void paint(Graphics g)
	{
		super.paint(g);
	}
	 public StorePanel(store stores,JButton jbutton)
	 {
	 jPanel2=new JPanel();
		 jLabel=new JLabel();
		 Font font=new Font("宋体", 1, 20);
		//jLabel.setFont(font);
		 
		this.jButton=jbutton;
	     imageButton=new JButton();
	      font=new Font("宋体",Font.ITALIC, 70);
	      imageButton.setFont(font);
	     jButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	     jButton.setBackground(Color.white);
		 imageButton.setBackground(Color.white);
		 imageButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		 jPanel2.setBackground(Color.white);
		 jPanel2.setLayout(new GridLayout(4,1));

		
		 this.setLayout(new GridLayout(1,2));
		 this.add(imageButton);
		 this.add(jPanel2);
		 this.refresh(stores);
	 }
	 public void refresh(store stores)
	 {
		 imageButton.setText(String.valueOf(stores.num));
		 String labelString="标签:";
		 for(int i=0;i<stores.products.size();i++)
		 {
			 goods goods=stores.products.get(i);
			 labelString+=" ";
			 labelString+=goods.name;
		 }
		 Font font=new Font("宋体", 1, 20);
		 jLabel2=new JLabel("名称:"+stores.name,JLabel.CENTER);
		 jLabel2.setFont(font);
		 jLabel2.setBackground(Color.blue);
		 jLabel.setText(labelString);
		 jLabel3=new JLabel("信誉度:"+String.valueOf(stores.cred),JLabel.CENTER);
		 jLabel3.setFont(font);
		 jPanel2.add(jLabel2);
		 jPanel2.add(jLabel3);
		 jPanel2.add(jLabel);
		 jPanel2.add(jButton);
		 if(stores.cred>=90)jLabel3.setForeground(Color.green);
		 else if(stores.cred<60)jLabel3.setForeground(Color.red);
	 }
}
class goods {
		public String name=null;/*商品名称*/
		public int price;/*商品价格*/
		public int sale;/*商品销量*/
		public int reserve;/*商品库存*/
		public goods(String name,int price,int sale,int reserve)
		{
			this.name=new String(name);
			this.price=price;
			this.sale=sale;
			this.reserve=reserve;
		}
	}
class StoreList {
	    public store store=null;
	    public StoreList nextList=null;
	    public StoreList preList=null;
	    public StoreList(store store)
	    {
	    	this.store=store;
	    	this.nextList=null;
	    	this.preList=null;
	    }
	    public void add(StoreList store)
	    {
	    	StoreList tempList=this;
	    	while(tempList.nextList!=null)
	    		tempList=tempList.nextList;
	    	tempList.nextList=store;
	    }
	    public int size()
	    {
	    	if(this.store.num==0)return 0;
	    	else 
	    	{
	    		int size=1;
	    		StoreList tempList=this;
	    	  	while(tempList.nextList!=null)
	    	  	{
	                size++;
	        		tempList=tempList.nextList;
	    	  	}
	    	  	return size;
	    	}
	    }
	    public store get(int n)
	    {
	    	if(n==1)return this.store;
	    	else {
	    		StoreList tempList=this;
	    		int temp=1;
	    		while(tempList.nextList!=null)
	    		{
	    			temp++;
	    			tempList=tempList.nextList;
	    			if(temp==n)return tempList.store;
	    		}
	    		return null;
			}
	    }
	    public StoreList renew()
	    {
	    	if(this.store.num==0)
	    	{
	    		if(this.nextList!=null)
	    		{
	    			StoreList temp=this.nextList;
	    			int num=1;
	    			temp.store.num=num;
	    			while(temp.nextList!=null)
	    			{
	    				num++;
	    				temp=temp.nextList;
	    				temp.store.num=num;
	    			}
	    			return this.nextList;
	    		}
	    		else return null;
	    	}
			else {
				StoreList temp=this,next=this.nextList;
				while(next!=null)
				{
					if(next.store.num==0)
					{
						temp.nextList=next.nextList;
					}
					temp=next;
					next=next.nextList;
				}
				temp=this;
				int num=1;
				temp.store.num=num;
				while(temp.nextList!=null)
				{
					num++;
					temp=temp.nextList;
					temp.store.num=num;
				}
				return this;
			}
	    }
	    public StoreList sort_cred()
	    {
	    	StoreList result=null;
	    	Vector<store> tempLists=new Vector<store>();
	    	store store=null;
	    	for(int i=0;i<this.size();i++)
	    	{
	    		tempLists.add(this.get(i+1));
	    		if(store==null)store=this.get(i+1);
	    		else 
	    		{
	    			store temp=this.get(i+1);
	    			if(store.cred<temp.cred)store=temp;
	    		}
	    	}
	    	result=new StoreList(store);
	    	tempLists.remove(store);
	    	while(tempLists.size()>0)
	    	{
	    		store=null;
	        	for(int i=0;i<tempLists.size();i++)
	        	{
	        		if(store==null)store=tempLists.get(i);
	        		else 
	        		{
	        			store temp=tempLists.get(i);
	        			if(store.cred<temp.cred)store=temp;
	        		}
	        	}
	        	StoreList temp=new StoreList(store);
	        	result.add(temp);
	        	tempLists.remove(store);
	    	}
	    	return result;
	    }
	    public StoreList sort_sale(String goodname)
	    {
	    	StoreList result=null;
	    	Vector<store> tempLists=new Vector<store>();
	    	store store=null;
	    	for(int i=0;i<this.size();i++)
	    	{
	    		store tempStore=this.get(i+1);
	    		if(tempStore.findgood(goodname))tempLists.add(tempStore);
	    	}
	    	while(tempLists.size()>0)
	    	{
	    		store=null;
	        	for(int i=0;i<tempLists.size();i++)
	        	{
	        		if(store==null)store=tempLists.get(i);
	        		else 
	        		{
	        			store temp=tempLists.get(i);
	        			if(store.getSale(goodname)<temp.getSale(goodname))store=temp;
	        		}
	        	}
	        	if(result==null)   result=new StoreList(store);
	        	else {
	        		StoreList temp=new StoreList(store);
	            	result.add(temp);
				}
	        	tempLists.remove(store);
	    	}
	    	StoreList someList=result;
	    	while(someList!=null&&someList.nextList!=null)
	    	{
	    		someList.nextList.preList=someList;
	    		someList=someList.nextList;
	    	}
	    	return result;
	    }
	    public StoreList sort_num()
	    {
	    	StoreList result=null;
	    	Vector<store> tempLists=new Vector<store>();
	    	store store=null;
	    	for(int i=0;i<this.size();i++)
	    	{
	    		store tempStore=this.get(i+1);
	    		tempLists.add(tempStore);
	    	}
	    	while(tempLists.size()>0)
	    	{
	    		store=null;
	        	for(int i=0;i<tempLists.size();i++)
	        	{
	        		if(store==null)store=tempLists.get(i);
	        		else 
	        		{
	        			store temp=tempLists.get(i);
	        			if(store.num>temp.num)store=temp;
	        		}
	        	}
	        	if(result==null)   result=new StoreList(store);
	        	else {
	        		StoreList temp=new StoreList(store);
	            	result.add(temp);
				}
	        	tempLists.remove(store);
	    	}
	    	return result;
	    }
	}
class store {
		String name=null;/*店铺名称*/
		public int num;/*店铺编号*/
		int cred;/*店铺信誉度*/
		public Vector<goods> products=null;/*商品*/
	    public store(int num,int cred,String name){
	    	this.num=num;
	    	this.cred=cred;
	    	this.name=new String(name);
	        products=new Vector<goods>();
	    }
	    public int getSale(String name)
	    {
	    	for(int i=0;i<products.size();i++)
	    	{
	    		goods goods=products.get(i);
	    		if(goods.name.equals(name))return goods.sale;
	        }
	    	return 0;
	    }
	    /*店铺内是否存在某个商品*/
	    public boolean findgood(String name)
	    {
	    	for(int i=0;i<products.size();i++)
	    	{
	    		goods goods=products.get(i);
	    		if(goods.name.equals(name))return true;
	    	}
	    	return false;
	    }
	}
class PurchasePanel extends JDialog implements ActionListener{
	JLabel title,note;
	Font font;
	JPanel jPanel,jPanels[],buy;
	JButton button[],noButton,outButton;
	store store;

	boolean flag[];
	public PurchasePanel(store store)
	{
		
		outButton=new JButton("去其他店铺逛逛");
		outButton.addActionListener(this);
		outButton.setForeground(Color.red);
		 font=new Font("宋体",Font.BOLD, 20);
		 outButton.setFont(font);
		flag=new boolean[6];
		for(int i=0;i<6;i++)flag[i]=true;
		buy=new JPanel();
		buy.setBackground(Color.white);
	    font=new Font("宋体",Font.BOLD, 15);
		noButton=new JButton("取消购买");
		noButton.setFont(font);
		noButton.addActionListener(this);
		this.store=store;
		font=new Font("宋体",Font.BOLD, 20);
		note=new JLabel();
		note.setFont(font);
		note.setForeground(Color.CYAN);
		buy.add(note);
		buy.add(noButton);
		title=new JLabel("信誉度:"+String.valueOf(store.cred),JLabel.CENTER);
		title.setFont(font);
		JPanel tempPanel=new JPanel();
		tempPanel.add(title);
		if(store.cred>=90)tempPanel.setBackground(Color.green);
		else if(store.cred<60)tempPanel.setBackground(Color.red);
		else tempPanel.setBackground(Color.yellow);
		this.add(tempPanel,BorderLayout.NORTH);
		button=new JButton[6];
		jPanel=new JPanel();
		jPanels=new JPanel[6];
		jPanel.setLayout(new GridLayout(10,1));
		String temp[]={"商品","价格","销量","库存","操作"};
		JPanel tempJPanel=new JPanel();
		tempJPanel.setLayout(new GridLayout(1,5));
		for(int i=0;i<5;i++)
		{
			JLabel tempJLabel=new JLabel(temp[i]);
			tempJLabel.setFont(font);
			tempJPanel.add(tempJLabel);
		}
		jPanel.add(tempJPanel);
		
		for(int i=0;i<store.products.size();i++)
		{
			jPanels[i]=new JPanel();
			jPanels[i].setBackground(Color.white);
			jPanels[i].setLayout(new GridLayout(1,5));
			font=new Font("宋体",Font.BOLD, 20);
			goods goods=store.products.get(i);
			JLabel tempJLabel=new JLabel(goods.name);
			tempJLabel.setFont(font);
			jPanels[i].add(tempJLabel);
			tempJLabel.setForeground(Color.blue);
			tempJLabel=new JLabel(String.valueOf(goods.price));
			tempJLabel.setFont(font);
			jPanels[i].add(tempJLabel);
			tempJLabel=new JLabel(String.valueOf(goods.sale));
			tempJLabel.setFont(font);
			jPanels[i].add(tempJLabel);
			tempJLabel=new JLabel(String.valueOf(goods.reserve));
			tempJLabel.setFont(font);
			jPanels[i].add(tempJLabel);
			button[i]=new JButton("购买");
			button[i].setFont(font);
			button[i].setForeground(Color.red);
			button[i].addActionListener(this);
			jPanels[i].add(button[i]);
			if(goods.reserve==0)
			{
				button[i].setEnabled(false);
				button[i].setText("售空");
				flag[i]=false;
			}
			jPanel.add(jPanels[i]);
		}
		this.add(jPanel);
		this.add(outButton,BorderLayout.SOUTH);
		this.setBackground(Color.white);
		this.setTitle(store.name);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.dispose();
		this.setModal(true);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(noButton))
		{
			jPanel.remove(buy);
			for(int i=0;i<store.products.size();i++)
			{
				if(flag[i])
				{
					button[i].setText("购买");
					button[i].setEnabled(true);
				}
			}
			jPanel.updateUI();
		}
		else if(e.getSource().equals(outButton))
		{
			this.dispose();
		}
		else {
			for(int i=0;i<store.products.size();i++)
			{
				if(e.getSource().equals(button[i]))
				{
					if(button[i].getText().equals("购买"))
					{
						  this.Pop(i);
						  button[i].setText("确认");
						  for(int j=0;j<store.products.size();j++)
						  {
							  if(j!=i&&flag[j])button[j].setEnabled(false);
						  }
	
					      
					}
					else if(button[i].getText().equals("确认"))
					{
						button[i].setText("已购买");
						font=new Font("宋体",Font.BOLD, 13);
						button[i].setFont(font);
						button[i].setEnabled(false);
						jPanels[i].add(button[i]);
						this.Push();
						flag[i]=false;
						for(int j=0;j<store.products.size();j++)
						{
							if(flag[j])button[j].setEnabled(true);
						}
						goods goods=store.products.get(i);
						goods.reserve--;
						goods.sale++;
						jPanels[i].removeAll();
						font=new Font("宋体",Font.BOLD, 20);
						JLabel tempJLabel=new JLabel(goods.name);
						tempJLabel.setFont(font);
						jPanels[i].add(tempJLabel);
						tempJLabel.setForeground(Color.blue);
						tempJLabel=new JLabel(String.valueOf(goods.price));
						tempJLabel.setFont(font);
						jPanels[i].add(tempJLabel);
						tempJLabel=new JLabel(String.valueOf(goods.sale));
						tempJLabel.setFont(font);
						jPanels[i].add(tempJLabel);
						tempJLabel=new JLabel(String.valueOf(goods.reserve));
						tempJLabel.setFont(font);
						jPanels[i].add(tempJLabel);
						jPanels[i].add(button[i]);
						jPanels[i].updateUI();
					}
					
			
					
					break;
				}
			}
		}
		
	}
	public void Push()
	{
		jPanel.remove(buy);
		jPanel.updateUI();
	}
	public void Pop(int flag)
	{
		goods goods=store.products.get(flag);
		note.setText("共计1件商品，应付款"+String.valueOf(goods.price)+"元");
		jPanel.add(buy,flag+2);
		jPanel.updateUI();
	}
}

