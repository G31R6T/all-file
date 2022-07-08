

import java.awt.*; //导入图形面板
import java.awt.event.*;//导入事件监听
import javax.swing.*;//导入组件
import java.util.*;//导入数组等



public class BlackHole extends JFrame  implements ActionListener {
    
	  JPanel Selectmb,Temp;    //选关面板
      JLabel Levelselect;  //选关提示文字标签
      JButton Okbutton;  //确定按钮
      JComboBox Levelitem; //复选框组件 选关
     int[][] DT1= new int[5][5];   //第一关 25格通行标记
     int[][] DT2= new int[7][7];   //第二关79格通行标记
     int[][] DT3= new int[9][9];   //第三关81格通行标记
     int Redscore; //红方得分
     int Bluescore;//蓝方得分
     int DtLevel;//关卡级别
     int TurnS=11;  //执行顺序  11-代表红方行动 22-代表蓝方行动
     int Seled=0;  //选中序号 ，哪一个飞船被选中进行移动操作
     int BottomMax,RightMax; //面板格子的底部边界和右边边界
     int Centerx=2;
     int Centery=2;//黑洞坐标
     int Currentx=0;//当前选中飞船的X坐标
     int Currenty=0;//当前选中飞船的Y坐标
     String[] Litem= {"5*5地图","7*7地图","9*9地图"};//关卡地图文字标题

     //DT1 第一关 红方和蓝方飞船的初始坐标
     int[] RedPosxDT1= {0,1,3,4};
     int[] RedPosyDT1={0,1,1,0};
     int[] BluePosxDT1={0,1,3,4}; 
     int[] BluePosyDT1={4,3,3,4}; 
   
     //DT2 第二关 红方和蓝方飞船的初始坐标
     int[] RedPosxDT2= {0,1,2,4,5,6};
     int[] RedPosyDT2={0,1,2,2,1,0};
     int[] BluePosxDT2={0,1,2,4,5,6}; 
     int[] BluePosyDT2={6,5,4,4,5,6};
   
     //DT3 第三关 红方和蓝方飞船的初始坐标
     int[] RedPosxDT3= {0,1,2,3,5,6,7,8};
     int[] RedPosyDT3={0,1,2,3,3,2,1,0};
     int[] BluePosxDT3={0,1,2,3,5,6,7,8}; 
     int[] BluePosyDT3={8,7,6,5,5,6,7,8};   

     int WinScore=2;//获胜分数标准
	   
	 Wdmb mb=null;//声明主窗体






//===============================================================================

      public  void init(){  //每局结束后的变量初始化

            Redscore=0; //红方得分清零
            Bluescore=0;//蓝方得分清零
            TurnS=11;  //红方先手
            Seled=0;  //默认选中第一艘飞船
           //DT1 第一关的初始化数据
     
          RedPosxDT1=new int[4]; //红方飞船起始X坐标数组清空
          RedPosyDT1=new int[4]; //红方飞船起始Y坐标数组清空
          BluePosxDT1=new int[4];//蓝方飞船起始X坐标数组清空
          BluePosyDT1=new int[4];//蓝方飞船起始Y坐标数组清空

          //红方飞船初始XY坐标
          RedPosxDT1[0]=0;
          RedPosxDT1[1]=1;
          RedPosxDT1[2]=3;
          RedPosxDT1[3]=4;
          RedPosyDT1[0]=0;
          RedPosyDT1[1]=1;
          RedPosyDT1[2]=1;
          RedPosyDT1[3]=0;


          //蓝方飞船初始XY坐标
          BluePosxDT1[0]=0;
          BluePosxDT1[1]=1;
          BluePosxDT1[2]=3;
          BluePosxDT1[3]=4;
          BluePosyDT1[0]=4;
          BluePosyDT1[1]=3;
          BluePosyDT1[2]=3;
          BluePosyDT1[3]=4;
   
     //DT2 第二关的初始化数据
          RedPosxDT2=new int[6]; //红方飞船起始X坐标数组清空
          RedPosyDT2=new int[6]; //红方飞船起始Y坐标数组清空
          BluePosxDT2=new int[6];//蓝方飞船起始X坐标数组清空
          BluePosyDT2=new int[6];//蓝方飞船起始Y坐标数组清空
          
          //红方飞船初始XY坐标
          RedPosxDT2[0]=0;
          RedPosxDT2[1]=1;
          RedPosxDT2[2]=2;
          RedPosxDT2[3]=4;
          RedPosxDT2[4]=5;
          RedPosxDT2[5]=6;
          RedPosyDT2[0]=0;
          RedPosyDT2[1]=1;
          RedPosyDT2[2]=2;
          RedPosyDT2[3]=2;
          RedPosyDT2[4]=1;
          RedPosyDT2[5]=0;
          
          //蓝方飞船初始XY坐标
          BluePosxDT2[0]=0;
          BluePosxDT2[1]=1;
          BluePosxDT2[2]=2;
          BluePosxDT2[3]=4;
          BluePosxDT2[4]=5;
          BluePosxDT2[5]=6;
          BluePosyDT2[0]=6;
          BluePosyDT2[1]=5;
          BluePosyDT2[2]=4;
          BluePosyDT2[3]=4;
          BluePosyDT2[4]=5;
          BluePosyDT2[5]=6;
          
     //RedPosxDT2= {0,1,2,4,5,6};
     //RedPosyDT2={0,1,2,2,1,0};
     //BluePosxDT2={0,1,2,4,5,6}; 
     //BluePosyDT2={6,5,4,4,5,6};
   
     //DT3 第三关的初始化数据
          RedPosxDT3=new int[8]; //红方飞船起始X坐标数组清空
          RedPosyDT3=new int[8]; //红方飞船起始Y坐标数组清空
          BluePosxDT3=new int[8];//蓝方飞船起始X坐标数组清空
          BluePosyDT3=new int[8];//蓝方飞船起始Y坐标数组清空
          
          //红方飞船初始XY坐标
          RedPosxDT3[0]=0;
          RedPosxDT3[1]=1;
          RedPosxDT3[2]=2;
          RedPosxDT3[3]=3;
          RedPosxDT3[4]=5;
          RedPosxDT3[5]=6;
          RedPosxDT3[6]=7;
          RedPosxDT3[7]=8;         
          RedPosyDT3[0]=0;
          RedPosyDT3[1]=1;
          RedPosyDT3[2]=2;
          RedPosyDT3[3]=3;
          RedPosyDT3[4]=3;
          RedPosyDT3[5]=2;
          RedPosyDT3[6]=1;
          RedPosyDT3[7]=0;
          
          
          //蓝方飞船初始XY坐标
          BluePosxDT3[0]=0;
          BluePosxDT3[1]=1;
          BluePosxDT3[2]=2;
          BluePosxDT3[3]=3;
          BluePosxDT3[4]=5;
          BluePosxDT3[5]=6;
          BluePosxDT3[6]=7;
          BluePosxDT3[7]=8;
          BluePosyDT3[0]=8;
          BluePosyDT3[1]=7;
          BluePosyDT3[2]=6;
          BluePosyDT3[3]=5;
          BluePosyDT3[4]=5;
          BluePosyDT3[5]=6;
          BluePosyDT3[6]=7;
          BluePosyDT3[7]=8;
    //  RedPosxDT3= {0,1,2,3,5,6,7,8};
    //  RedPosyDaT3={0,1,2,3,3,2,1,0};
    //  BluePosxDT3={0,1,2,3,5,6,7,8}; 
    //  BluePosyDT3={8,7,6,5,5,6,7,8};   


     
      }
     
//=============================================================================











       @SuppressWarnings("unchecked")  //关闭检查报警
	public static void main(String[] args) {  //程序入口
	
        BlackHole A = new BlackHole();    //实现构造方法 创建窗口

        
        

	}
	
    public void actionPerformed(ActionEvent e)   //事件监听
	{
		

                 

		 if(e.getActionCommand().equals("111"))  //关卡地图选定后，单击确定按钮后执行
		 { 
			 
			 this.dispose();  //销毁原窗体
			 String LevelDT = Levelitem.getSelectedItem().toString(); //提取选择的地图名字
			 if (LevelDT=="5*5地图") {
				                   WinScore=2;  //获胜标准
                                   BottomMax=4; //底边界
                                   RightMax=4;  //右边界
                                   Centerx=2;   //黑洞x坐标
                                   Centery=2;   //黑洞y坐标
                                   Seled=0;     //默认选中第一艘飞船
				 BlackHole B = new BlackHole(1); //打开窗体
                                
				
			 }
			 if (LevelDT=="7*7地图") {
				                  WinScore=3;
                                  BottomMax=6;
                                   RightMax=6; 
                                   Centerx=3;
                                   Centery=3;
                                   Seled=0;
				 BlackHole B = new BlackHole(2);
                                 
				
			 }
			 if (LevelDT=="9*9地图") {
				                  WinScore=4;
                                  BottomMax=8;
                                   RightMax=8; 
                                   Centerx=4;
                                   Centery=4;
                                   Seled=0;
				 BlackHole B = new BlackHole(3);
				 
			 }
			
		 }
		 
	}
    
	
	
	
	
	 BlackHole()  {  //构造方法
	
		Selectmb = new JPanel(); //选关面板
		
		Selectmb.setLayout(new FlowLayout(FlowLayout.LEFT,350,100));//流式布局

	Levelselect = new JLabel("请选择地图 ");  //标签
	Levelselect.setFont(new Font("幼圆", Font.BOLD, 50)); //标签字体设置
	Levelselect.setForeground(Color.red);  //前景颜色设置
		Levelitem= new JComboBox(Litem);   //下拉框
		Levelitem.setFont(new Font("幼圆", Font.BOLD, 60)); //下拉框字体设置
		Okbutton = new JButton("确定");  //确认按钮
		Okbutton.setFont(new Font("幼圆", Font.BOLD, 60));//按钮字体设置
		Selectmb.add(Levelselect);  //添加标签到面板
		Selectmb.add(Levelitem);    //添加下拉框到面板
		Selectmb.add(Okbutton);     //添加按钮到面板

		 
		Okbutton.addActionListener(this);  //设置按钮监听
		
		Okbutton.setActionCommand("111");  //设置按钮监听标记
		
		
		
		this.add(Selectmb);  //将面板放入窗体
		
		this.setTitle("BlackHole"); //设置窗口标题
	
		this.setSize(1024,768); //设置窗口大小
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //关闭窗口时注销进程
		this.setResizable(false);   //不可调整窗口大小
		this.setLocationRelativeTo(null);  //设置窗口在屏幕中央
        this.setVisible(true);		 //显示窗口
	}
	
	
 





	
	public BlackHole(int Sel) {

        switch (Sel) {
        
        case 1:        	    		
    		 DtLevel=1;    		     //第一关
    		this.setTitle("5*5地图");  //标题
    		//地图定位初始化
    		for ( int m=0;m<5;m++) {
    				for( int n=0 ;n<5;n++) {
    				DT1[m][n]=0;
    				}
    		}

    		DT1[2][2]=9;//9代表黑洞
    		
    		DT1[0][0]=1;
    		DT1[1][1]=1;
    		DT1[4][0]=1;
    		DT1[3][1]=1;//非零代表有船（红船）
    		
    		
    		DT1[0][4]=1;
    		DT1[1][3]=1;    		   		
    		DT1[4][4]=1;
    		DT1[3][3]=1;//非零代表有船（蓝船）
    		
           Seled=0;
                  
        	break;
        	
        case 2:        	
    		 DtLevel=2;    		
    		this.setTitle("7*7地图"); 
    		//地图定位初始化
    		for ( int m=0;m<7;m++) {
    				for( int n=0 ;n<7;n++) {
    				DT2[m][n]=0;
    				}
    		}

    		DT2[3][3]=9;//9代表黑洞
    		
    		DT2[0][0]=1;
    		DT2[1][1]=1;
    		DT2[2][2]=1;
    		DT2[4][2]=1;
    		DT2[5][1]=1;
    		DT2[6][0]=1;
    		
    		
    		
    		DT2[0][6]=1;
    		DT2[1][5]=1;    		   		
    		DT2[2][4]=1;
    		DT2[4][4]=1;
    		DT2[5][5]=1;    		   		
    		DT2[6][6]=1;
    		
    		
           Seled=0;
    		
    		
    		
        	break;
        	
        case 3:        	    		
    		 DtLevel=3;
    		this.setTitle("9*9地图");  
    		
    		//地图定位初始化
    		for ( int m=0;m<9;m++) {
    				for( int n=0 ;n<9;n++) {
    				DT3[m][n]=0;
    				}
    		}

    		DT3[4][4]=9;//9代表黑洞
    		
    		DT3[0][0]=1;
    		DT3[1][1]=1;
    		DT3[2][2]=1;
    		DT3[3][3]=1;
    		DT3[5][3]=1;
    		DT3[6][2]=1;
    		DT3[7][1]=1;
    		DT3[8][0]=1;
    		
    		
    		
    		DT3[0][8]=1;
    		DT3[1][7]=1;    		   		
    		DT3[2][6]=1;
    		DT3[3][5]=1;
    		DT3[5][5]=1;
    		DT3[6][6]=1;    		   		
    		DT3[7][7]=1;
    		DT3[8][8]=1;
           Seled=0;
    		
    		
        	break;          
        }
        Redscore=0;
		Bluescore=0;    		
		mb=new Wdmb();
		 this.add(mb);
		 this.addKeyListener(mb);    		
		this.setSize(730,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);  //设置窗口在屏幕中央
        this.setVisible(true);		
		
	}

	class Wdmb extends JPanel implements KeyListener
	{
		int x=1,y=50;
		

         
               public void paint(Graphics g) //这个方法是覆盖父类的方法，画笔的意思
		{

                      //取当前控制飞船的坐标    
            	   if(TurnS==11) {
            		   switch(DtLevel) {
            		   case 1:
            			   Currentx=RedPosxDT1[Seled];
                           Currenty=RedPosyDT1[Seled];
            			   break;
            		   case 2:
            			   Currentx=RedPosxDT2[Seled];
                           Currenty=RedPosyDT2[Seled];
            			   break;
            		   case 3:
            			   Currentx=RedPosxDT3[Seled];
                           Currenty=RedPosyDT3[Seled];
            			   break;
            		   }
   		              
   			      }else{
   			    	  
	   			    	switch(DtLevel) {
		         		   case 1:
		         			    Currentx=BluePosxDT1[Seled];
		   	                    Currenty=BluePosyDT1[Seled];
		         			   break;
		         		   case 2:
		         			    Currentx=BluePosxDT2[Seled];
		   	                    Currenty=BluePosyDT2[Seled];
		         			   break;
		         		   case 3:
		         			    Currentx=BluePosxDT3[Seled];
		   	                    Currenty=BluePosyDT3[Seled];
		         			   break;
	         		   }
	   			    	  
   			    	  
   			    	  
   			    	   
   			      }
			super.paint(g);
			g.setColor(Color.BLACK);
			g.setFont(new Font("微软雅黑",Font.BOLD, 15));
			g.drawString("红方SCORE ",10,20);	
			g.drawString(Integer.toString(Redscore),10,40);
			g.drawString("蓝方SCORE ",180, 20);
			g.drawString(Integer.toString(Bluescore),180, 40);
                        g.setColor(Color.RED);
                          // g.drawString(Integer.toString(TurnS),280, 40);
                          
                           //g.drawString(Integer.toString(DT1[RedPosxDT1[Seled]][(RedPosyDT1[Seled])]),300, 40);
                         //  g.drawString(Integer.toString(DT1[RedPosxDT1[Seled]-1][(RedPosyDT1[Seled])]),320, 40);
                          // g.drawString(Integer.toString(DT1[RedPosxDT1[Seled]+1][(RedPosyDT1[Seled])]),350, 40);
//                           g.drawString("选中:"+Integer.toString(Seled),380, 40);                          
//                           g.drawString("X:"+Integer.toString(Currentx),500, 40);
//                           g.drawString("Y:"+Integer.toString(Currenty),550, 40);
//                           g.drawString("红船数量"+RedPosxDT1.length,600, 40);
//                           g.drawString("蓝船数量"+BluePosxDT1.length,600, 80);
//                           g.drawString("当前0/1值::"+Integer.toString(DT1[Currentx][Currenty]),500, 80);
//                           g.drawString("DT1长度::"+Integer.toString(DT1.length),500, 120);
//                        
                        
		
			g.setColor(Color.darkGray);

			
			
			
			if(DtLevel==1) {

                        //画地图
			for(int i=0;i<480;i+=80) {
				g.drawLine(1,50+i,401,50+i);
				g.drawLine(i+1,51,i+1, 451);
				
			}
			g.setColor(Color.BLACK);
			g.fillRect(1+Centerx*80,50+Centery*80,80,80);
			//g.fillRect(161,210,80,80);
			//==========================================================================
			//画红船                                     
                              
			 for (int n=0 ;n<RedPosyDT1.length;n++){
				 g.setColor(Color.BLACK);
				 g.fillRect(1+Centerx*80,50+Centery*80,80,80);
	             //g.fillRect(161,210,80,80);
			 
                    if(n==Seled && TurnS==11) {
                    	 g.setColor(Color.YELLOW);
     			        g.fillRect(1+RedPosxDT1[Seled]*80,50+RedPosyDT1[Seled]*80,80,80);
                    }else {
				  g.setColor(Color.RED);
                  g.fillRect(1+RedPosxDT1[n]*80,50+RedPosyDT1[n]*80,80,80);              
                          
                    } 
                           }

                          //画蓝船
                                      for (int n=0 ;n<BluePosxDT1.length;n++){
                                            g.setColor(Color.BLACK);
                                            g.fillRect(1+Centerx*80,50+Centery*80,80,80);
                        		          //g.fillRect(161,210,80,80);
                           
                                    if (TurnS ==22 && n==Seled ){

                                     g.setColor(Color.darkGray);
			                         g.fillRect(1+BluePosxDT1[n]*80,50+BluePosyDT1[n]*80,80,80);
                             
                                  
                               }else {
                            	   
                            	   g.setColor(Color.BLUE);
                                   g.fillRect(1+BluePosxDT1[n]*80,50+BluePosyDT1[n]*80,80,80);
                            	   
                            	   
                               }
                        

                       
                                   
                           }
                              //=================================================================================
                                      g.drawString("X:"+Integer.toString(Currentx),500, 40);
                                      g.drawString("Y:"+Integer.toString(Currenty),550, 40);
                                      
			
			}
			
			
			if(DtLevel==2) {
                     Centerx=3;
                     Centery=3;
				              //画地图
					for(int i=0;i<640;i+=80) {
						g.drawLine(1,50+i,561,50+i);
						g.drawLine(i+1,51,i+1, 611);
					}
					g.setColor(Color.BLACK);
					g.fillRect(1+Centerx*80,50+Centery*80,80,80);
			
			//==========================================================================
			//画红船                                     
                              
			 for (int n=0 ;n<RedPosyDT2.length;n++){
				 g.setColor(Color.BLACK);
				 g.fillRect(1+Centerx*80,50+Centery*80,80,80);
	            
			 
                    if(n==Seled && TurnS==11) {
                    	 g.setColor(Color.YELLOW);
     			        g.fillRect(1+RedPosxDT2[Seled]*80,50+RedPosyDT2[Seled]*80,80,80);
                    }else {
				  g.setColor(Color.RED);
                  g.fillRect(1+RedPosxDT2[n]*80,50+RedPosyDT2[n]*80,80,80);              
                          
                    } 
                           }

                          //画蓝船
                                      for (int n=0 ;n<BluePosxDT2.length;n++){
                                            g.setColor(Color.BLACK);
                                            g.fillRect(1+Centerx*80,50+Centery*80,80,80);
                        		         
                           
                                    if (TurnS ==22 && n==Seled ){

                                     g.setColor(Color.darkGray);
			                         g.fillRect(1+BluePosxDT2[n]*80,50+BluePosyDT2[n]*80,80,80);
                             
                                  
                               }else {
                            	   
                            	   g.setColor(Color.BLUE);
                                   g.fillRect(1+BluePosxDT2[n]*80,50+BluePosyDT2[n]*80,80,80);
                            	   
                            	   
                               }
                        

                       
                                   
                           }
                              //=================================================================================
                                      g.drawString("X:"+Integer.toString(Currentx),500, 40);
                                      g.drawString("Y:"+Integer.toString(Currenty),550, 40);
                                      
			
			}
			
		
			
				
				if(DtLevel==3) {
					
			         Centerx=4;
                     Centery=4;
				              //画地图
                     for(int i=0;i<800;i+=80) {
     					g.drawLine(1,50+i,721,50+i);
     					g.drawLine(i+1,51,i+1, 771);
     				}
					g.setColor(Color.BLACK);
					g.fillRect(1+Centerx*80,50+Centery*80,80,80);
			
			//==========================================================================
			//画红船                                     
                              
			 for (int n=0 ;n<RedPosyDT3.length;n++){
				 g.setColor(Color.BLACK);
				 g.fillRect(1+Centerx*80,50+Centery*80,80,80);
	            
			 
                    if(n==Seled && TurnS==11) {
                    	 g.setColor(Color.YELLOW);
     			        g.fillRect(1+RedPosxDT3[Seled]*80,50+RedPosyDT3[Seled]*80,80,80);
                    }else {
				  g.setColor(Color.RED);
                  g.fillRect(1+RedPosxDT3[n]*80,50+RedPosyDT3[n]*80,80,80);              
                          
                    } 
                           }

                          //画蓝船
                                      for (int n=0 ;n<BluePosxDT3.length;n++){
                                            g.setColor(Color.BLACK);
                                            g.fillRect(1+Centerx*80,50+Centery*80,80,80);
                        		         
                           
                                    if (TurnS ==22 && n==Seled ){

                                     g.setColor(Color.darkGray);
			                         g.fillRect(1+BluePosxDT3[n]*80,50+BluePosyDT3[n]*80,80,80);
                             
                                  
                               }else {
                            	   
                            	   g.setColor(Color.BLUE);
                                   g.fillRect(1+BluePosxDT3[n]*80,50+BluePosyDT3[n]*80,80,80);
                            	   
                            	   
                               }
                        

                       
                                   
                           }
                              //=================================================================================
                                      g.drawString("X:"+Integer.toString(Currentx),500, 40);
                                      g.drawString("Y:"+Integer.toString(Currenty),550, 40);
                                      
			
					

			
                         }
			
			
		}
		
		public void keyTyped(KeyEvent e)
		{  // 有字符输出的函数
		}
		public void keyPressed(KeyEvent e) 
		{  
                        if(DtLevel==1){
                        	       WinScore=2;
                                   BottomMax=4;
                                   RightMax=4; 
                                   Centerx=2;
                                   Centery=2;
                         }
                        
                        if(DtLevel==2){
                 	       WinScore=3;
                            BottomMax=6;
                            RightMax=6; 
                            Centerx=3;
                            Centery=3;
                        }
                        
                        
                        if(DtLevel==3){
                 	       WinScore=4;
                            BottomMax=8;
                            RightMax=8; 
                            Centerx=4;
                            Centery=4;
                        }
                                
			if(e.getKeyCode()==KeyEvent.VK_DOWN  )  //下
			{    
				
				//**********************************************************************
                //取当前控制飞船的坐标    
       	   if(TurnS==11) {
       		   switch(DtLevel) {
       		   case 1:
       			   Currentx=RedPosxDT1[Seled];
                      Currenty=RedPosyDT1[Seled];
       			   break;
       		   case 2:
       			   Currentx=RedPosxDT2[Seled];
                      Currenty=RedPosyDT2[Seled];
       			   break;
       		   case 3:
       			   Currentx=RedPosxDT3[Seled];
                      Currenty=RedPosyDT3[Seled];
       			   break;
       		   }
		              
			      }else{
			    	  
  			    	switch(DtLevel) {
	         		   case 1:
	         			    Currentx=BluePosxDT1[Seled];
	   	                    Currenty=BluePosyDT1[Seled];
	         			   break;
	         		   case 2:
	         			    Currentx=BluePosxDT2[Seled];
	   	                    Currenty=BluePosyDT2[Seled];
	         			   break;
	         		   case 3:
	         			    Currentx=BluePosxDT3[Seled];
	   	                    Currenty=BluePosyDT3[Seled];
	         			   break;
        		   }
  			    	  
			    	  
			    	  
			    	   
			      }
				
				//*********************************************************************
//				if(TurnS==11) {
//		            Currentx=RedPosxDT1[Seled];
//                    Currenty=RedPosyDT1[Seled];
//			      }else{
//			    	    Currentx=BluePosxDT1[Seled];
//	                    Currenty=BluePosyDT1[Seled];
//			      }     
				 //JOptionPane.showMessageDialog(null,"X:"+Integer.toString(Currentx)+"  y:"+Integer.toString(Currenty));
                                    if(TurnS==11)
                                  {
//                                    	
                                            if(Currenty<BottomMax ){
                                            	
                                            	switch(DtLevel) {
                                            	case 1:
                                            		 while( DT1[Currentx][Currenty+1]==0){
                                                         
                                                         DT1[Currentx][Currenty+1]=1;
                                                         DT1[Currentx][Currenty]=0;
                                                         Currenty++;
                                                         RedPosyDT1[Seled]=Currenty;
                                                            if(Currenty<BottomMax) {

                                                            }else {break;}
                                                   } 
                                                     
                                                     if(Currenty<BottomMax) {
                                                         if( DT1[Currentx][Currenty+1]==9){                                                         
                                                             DT1[Currentx][Currenty]=0;
                                                             Currenty++;
                                                             RedPosyDT1[Seled]=Currenty;
                                                             
                                                         }
                                                }
                                                     
                                                     TurnS=22;
                                            		
                                            		break;
                                            	case 2:
                                                       while( DT2[Currentx][Currenty+1]==0){
                                                         
                                                         DT2[Currentx][Currenty+1]=1;
                                                         DT2[Currentx][Currenty]=0;
                                                         Currenty++;
                                                         RedPosyDT2[Seled]=Currenty;
                                                            if(Currenty<BottomMax) {

                                                            }else {break;}
                                                   } 
                                                     
                                                     if(Currenty<BottomMax) {
                                                         if( DT2[Currentx][Currenty+1]==9){                                                         
                                                             DT2[Currentx][Currenty]=0;
                                                             Currenty++;
                                                             RedPosyDT2[Seled]=Currenty;
                                                             
                                                         }
                                                }
                                                     
                                                     TurnS=22;
                                            		
                                            		break;
                                            	case 3:
                                                       while( DT3[Currentx][Currenty+1]==0){
                                                         
                                                         DT3[Currentx][Currenty+1]=1;
                                                         DT3[Currentx][Currenty]=0;
                                                         Currenty++;
                                                         RedPosyDT3[Seled]=Currenty;
                                                            if(Currenty<BottomMax) {

                                                            }else {break;}
                                                   } 
                                                     
                                                     if(Currenty<BottomMax) {
                                                         if( DT3[Currentx][Currenty+1]==9){                                                         
                                                             DT3[Currentx][Currenty]=0;
                                                             Currenty++;
                                                             RedPosyDT3[Seled]=Currenty;
                                                             
                                                         }
                                                }
                                                     
                                                     TurnS=22;
                                            		
                                            		break;
                                            	
                                            	}
                                            	
                                          
                                                 
                                                 
                                                 
                                            }

                                 }else{
                                	 

		                                       
		                                	 
		                                	 
		                                	 if(Currenty<BottomMax ){
			                                     	
		                                		 switch(DtLevel){
		                                		      
		                                		 case 1:
		                                			 
			                                         while( DT1[Currentx][Currenty+1]==0){
			                                                 
			                                                 DT1[Currentx][Currenty+1]=1;
			                                                 DT1[Currentx][Currenty]=0;
			                                                 Currenty++;
			                                                 BluePosyDT1[Seled]=Currenty;
			                                                 
			                                                  if(Currenty<BottomMax) {

			                                                  }else {break;}
			                                           } 
			                                         
			                                         
			                                         
			                                         if(Currenty<BottomMax) {
		                                                 if( DT1[Currentx][Currenty+1]==9){
				                                             
				                                             
				                                             DT1[Currentx][Currenty]=0;
				                                             Currenty++;
				                                             BluePosyDT1[Seled]=Currenty;
				                                            
				                                         }
	                                              }
			                                         
			                                         
			                                         
			                                         
			                                         
			                                         TurnS=11;
		                                			  
		                                			 break;
		                                		 case 2:
		                                			 
		                                			 
			                                         while( DT2[Currentx][Currenty+1]==0){
			                                                 
			                                                 DT2[Currentx][Currenty+1]=1;
			                                                 DT2[Currentx][Currenty]=0;
			                                                 Currenty++;
			                                                 BluePosyDT2[Seled]=Currenty;
			                                                 
			                                                  if(Currenty<BottomMax) {

			                                                  }else {break;}
			                                           } 
			                                         
			                                         
			                                         
			                                         if(Currenty<BottomMax) {
		                                                 if( DT2[Currentx][Currenty+1]==9){
				                                             
				                                             
				                                             DT2[Currentx][Currenty]=0;
				                                             Currenty++;
				                                             BluePosyDT2[Seled]=Currenty;
				                                            
				                                         }
	                                              }
			                                         
			                                         
			                                         
			                                         
			                                         
			                                         TurnS=11;
		                                			 break;
		                                		 case 3:
		                                			 
		                                			 
			                                         while( DT3[Currentx][Currenty+1]==0){
			                                                 
			                                                 DT3[Currentx][Currenty+1]=1;
			                                                 DT3[Currentx][Currenty]=0;
			                                                 Currenty++;
			                                                 BluePosyDT3[Seled]=Currenty;
			                                                 
			                                                  if(Currenty<BottomMax) {

			                                                  }else {break;}
			                                           } 
			                                         
			                                         
			                                         
			                                         if(Currenty<BottomMax) {
		                                                 if( DT3[Currentx][Currenty+1]==9){
				                                             
				                                             
				                                             DT3[Currentx][Currenty]=0;
				                                             Currenty++;
				                                             BluePosyDT3[Seled]=Currenty;
				                                            
				                                         }
	                                              }
			                                         
			                                         
			                                         
			                                         
			                                         
			                                         TurnS=11;
		                                			 break;
		                                		 
		                                		 
		                                		 }
		                                	
                                                 }

                                       }











			}
			else if(e.getKeyCode()==KeyEvent.VK_UP)   //上
			{
				//***********************************************
                //取当前控制飞船的坐标    
       	   if(TurnS==11) {
       		   switch(DtLevel) {
       		   case 1:
       			   Currentx=RedPosxDT1[Seled];
                      Currenty=RedPosyDT1[Seled];
       			   break;
       		   case 2:
       			   Currentx=RedPosxDT2[Seled];
                      Currenty=RedPosyDT2[Seled];
       			   break;
       		   case 3:
       			   Currentx=RedPosxDT3[Seled];
                      Currenty=RedPosyDT3[Seled];
       			   break;
       		   }
		              
			      }else{
			    	  
  			    	switch(DtLevel) {
	         		   case 1:
	         			    Currentx=BluePosxDT1[Seled];
	   	                    Currenty=BluePosyDT1[Seled];
	         			   break;
	         		   case 2:
	         			    Currentx=BluePosxDT2[Seled];
	   	                    Currenty=BluePosyDT2[Seled];
	         			   break;
	         		   case 3:
	         			    Currentx=BluePosxDT3[Seled];
	   	                    Currenty=BluePosyDT3[Seled];
	         			   break;
        		   }
  			    	  
			    	  
			    	  
			    	   
			      }
				//***********************************************
				
				
//				if(TurnS==11) {
//		            Currentx=RedPosxDT1[Seled];
//                    Currenty=RedPosyDT1[Seled];
//			      }else{
//			    	  Currentx=BluePosxDT1[Seled];
//	                    Currenty=BluePosyDT1[Seled];
//			      }
			
							     if(TurnS==11)
				                 {
//							    	
				                           if(Currenty>0 ){
				                           	
                                                 switch(DtLevel) {
                                                       case 1:
                                                    	   
                                                           while( DT1[Currentx][Currenty-1]==0){
           			                                        
           			                                        DT1[Currentx][Currenty-1]=1;
           			                                        DT1[Currentx][Currenty]=0;
           			                                        Currenty--;
           			                                        RedPosyDT1[Seled]=Currenty;
           			                                           if(Currenty>0) {

           			                                           }else {break;}
           			                                        
           			                                  }  
           				                                
           				                                
           				                                
           				                                
           				                                if(Currenty>0) {
           			                                        if( DT1[Currentx][Currenty-1]==9){
           					                                    
           					                                    
           					                                    DT1[Currentx][Currenty]=0;
           					                                    Currenty--;
           					                                    RedPosyDT1[Seled]=Currenty;
           					                                    
           					                                }
           	                                           }
           				                                
           				                                
           				                                TurnS=22;  
                                                	        break;
                                                       case 2:
                                                    	   
                                                           while( DT2[Currentx][Currenty-1]==0){
           			                                        
           			                                        DT2[Currentx][Currenty-1]=1;
           			                                        DT2[Currentx][Currenty]=0;
           			                                        Currenty--;
           			                                        RedPosyDT2[Seled]=Currenty;
           			                                           if(Currenty>0) {

           			                                           }else {break;}
           			                                        
           			                                  }  
           				                                
           				                                
           				                                
           				                                
           				                                if(Currenty>0) {
           			                                        if( DT2[Currentx][Currenty-1]==9){
           					                                    
           					                                    
           					                                    DT2[Currentx][Currenty]=0;
           					                                    Currenty--;
           					                                    RedPosyDT2[Seled]=Currenty;
           					                                    
           					                                }
           	                                           }
           				                                
           				                                
           				                                TurnS=22;  
                                                    	    break;
                                                       case 3:
                                                    	   
                                                           while( DT3[Currentx][Currenty-1]==0){
           			                                        
           			                                        DT3[Currentx][Currenty-1]=1;
           			                                        DT3[Currentx][Currenty]=0;
           			                                        Currenty--;
           			                                        RedPosyDT3[Seled]=Currenty;
           			                                           if(Currenty>0) {

           			                                           }else {break;}
           			                                        
           			                                  }  
           				                                
           				                                
           				                                
           				                                
           				                                if(Currenty>0) {
           			                                        if( DT3[Currentx][Currenty-1]==9){
           					                                    
           					                                    
           					                                    DT3[Currentx][Currenty]=0;
           					                                    Currenty--;
           					                                    RedPosyDT3[Seled]=Currenty;
           					                                    
           					                                }
           	                                           }
           				                                
           				                                
           				                                TurnS=22;  
                                                    	    break;
                                                 
                                                 
                                                 
                                                 }
//				                                
				                                
				                         
				                                
				                                
				                           }
				
				                }else{
				                	 
				                       	 if(Currenty>0 ){
				                            	

				                       		      switch(DtLevel) {
					                       		      case 1:

								                       		 
								                       		while( DT1[Currentx][Currenty-1]==0){
				                                                
				                                                DT1[Currentx][Currenty-1]=1;
				                                                DT1[Currentx][Currenty]=0;
				                                                Currenty--;
				                                                BluePosyDT1[Seled]=Currenty;
				                                                if(Currenty>0) {

				                                                }else {break;}
				                                                 
				                                          }
								                       	 if(Currenty>0) {
				                                                if( DT1[Currentx][Currenty-1]==9){
						                                            
						                                            
						                                            DT1[Currentx][Currenty]=0;
						                                            Currenty--;
						                                            BluePosyDT1[Seled]=Currenty;
						                                           
		                                                          }
		                                                }
								                       	 TurnS=11;
					                       		    	   break;
					                       		      case 2:
					                       		    	  

								                       		 
								                       		while( DT2[Currentx][Currenty-1]==0){
				                                                
				                                                DT2[Currentx][Currenty-1]=1;
				                                                DT2[Currentx][Currenty]=0;
				                                                Currenty--;
				                                                BluePosyDT2[Seled]=Currenty;
				                                                if(Currenty>0) {

				                                                }else {break;}
				                                                 
				                                          }
								                       	 if(Currenty>0) {
				                                                if( DT2[Currentx][Currenty-1]==9){
						                                            
						                                            
						                                            DT2[Currentx][Currenty]=0;
						                                            Currenty--;
						                                            BluePosyDT2[Seled]=Currenty;
						                                           
		                                                          }
		                                                }
								                       	 TurnS=11;
					                       		    	    break;
					                       		      case 3:
					                       		      

							                       		 
							                       		while( DT3[Currentx][Currenty-1]==0){
			                                                
			                                                DT3[Currentx][Currenty-1]=1;
			                                                DT3[Currentx][Currenty]=0;
			                                                Currenty--;
			                                                BluePosyDT3[Seled]=Currenty;
			                                                if(Currenty>0) {

			                                                }else {break;}
			                                                 
			                                          }
							                       	 if(Currenty>0) {
			                                                if( DT3[Currentx][Currenty-1]==9){
					                                            
					                                            
					                                            DT3[Currentx][Currenty]=0;
					                                            Currenty--;
					                                            BluePosyDT3[Seled]=Currenty;
					                                           
	                                                          }
	                                                }
							                       	 TurnS=11;
					                       		            break;			                       		      
					                       		      
				                       		       }
				                       		 
				                       		 
				                              }
				                              
				
				                     }
				
				
				




                        
			}                                                                                                                             
			else if(e.getKeyCode()==KeyEvent.VK_LEFT ) //左
			{
				
				//**************************************************
                //取当前控制飞船的坐标    
       	   if(TurnS==11) {
       		   switch(DtLevel) {
       		   case 1:
       			   Currentx=RedPosxDT1[Seled];
                      Currenty=RedPosyDT1[Seled];
       			   break;
       		   case 2:
       			   Currentx=RedPosxDT2[Seled];
                      Currenty=RedPosyDT2[Seled];
       			   break;
       		   case 3:
       			   Currentx=RedPosxDT3[Seled];
                      Currenty=RedPosyDT3[Seled];
       			   break;
       		   }
		              
			      }else{
			    	  
  			    	switch(DtLevel) {
	         		   case 1:
	         			    Currentx=BluePosxDT1[Seled];
	   	                    Currenty=BluePosyDT1[Seled];
	         			   break;
	         		   case 2:
	         			    Currentx=BluePosxDT2[Seled];
	   	                    Currenty=BluePosyDT2[Seled];
	         			   break;
	         		   case 3:
	         			    Currentx=BluePosxDT3[Seled];
	   	                    Currenty=BluePosyDT3[Seled];
	         			   break;
        		   }
  			    	  
			    	  
			    	  
			    	   
			      }
				//******************************************************
				
//				if(TurnS==11) {
//		            Currentx=RedPosxDT1[Seled];
//                    Currenty=RedPosyDT1[Seled];
//			      }else{
//			    	  Currentx=BluePosxDT1[Seled];
//	                    Currenty=BluePosyDT1[Seled];
//			      }
				// JOptionPane.showMessageDialog(null,"X:"+Integer.toString(Currentx)+"  y:"+Integer.toString(Currenty));
                                if(TurnS==11){
                                	 
		                                  if(Currentx>0){

		                                	     switch(DtLevel){
								                        
								                        case 1:
								                       	  
						                                	  while(DT1[Currentx-1][Currenty]==0){
											                         
							                                         DT1[Currentx-1][Currenty]=1;
							                                         DT1[Currentx][Currenty]=0;
							                                         Currentx--;
							                                         RedPosxDT1[Seled]=Currentx;
							                                         
							                                               if(Currentx>0) {
									                        
							                                               }else {break;}
							                                         
							                                   } 
						                                	  if(Currentx>0) {
							                                         if (  DT1[Currentx-1][Currenty]==9){
												                         
											                        	 
									                                        
								                                         DT1[Currentx][Currenty]=0;
								                                         Currentx--;
								                                         RedPosxDT1[Seled]=Currentx;
								                                         
										                              }
							                                         
				                                               }
						                                	  TurnS=22;
								                        	break;
								                        case 2:
								                       	  
						                                	  while(DT2[Currentx-1][Currenty]==0){
											                         
							                                         DT2[Currentx-1][Currenty]=1;
							                                         DT2[Currentx][Currenty]=0;
							                                         Currentx--;
							                                         RedPosxDT2[Seled]=Currentx;
							                                         
							                                               if(Currentx>0) {
									                        
							                                               }else {break;}
							                                         
							                                   } 
						                                	  if(Currentx>0) {
							                                         if (  DT2[Currentx-1][Currenty]==9){
												                         
											                        	 
									                                        
								                                         DT2[Currentx][Currenty]=0;
								                                         Currentx--;
								                                         RedPosxDT2[Seled]=Currentx;
								                                         
										                              }
							                                         
				                                               }
						                                	  TurnS=22;
								                        	break;
								                        case 3:
								                       	  
						                                	  while(DT3[Currentx-1][Currenty]==0){
											                         
							                                         DT3[Currentx-1][Currenty]=1;
							                                         DT3[Currentx][Currenty]=0;
							                                         Currentx--;
							                                         RedPosxDT3[Seled]=Currentx;
							                                         
							                                               if(Currentx>0) {
									                        
							                                               }else {break;}
							                                         
							                                   } 
						                                	  if(Currentx>0) {
							                                         if (  DT3[Currentx-1][Currenty]==9){
												                         
											                        	 
									                                        
								                                         DT3[Currentx][Currenty]=0;
								                                         Currentx--;
								                                         RedPosxDT3[Seled]=Currentx;
								                                         
										                              }
							                                         
				                                               }
						                                	  TurnS=22;
								                        	break;
								                        
			                        
								                        } 
		                                	  
							                               
		                                	  
		                                	  
		                                	  
		                                	  
				                                	     
				                                   }

                                }else{
                                	 
                                
				                                	 if(Currentx>0   ){

										                     switch(DtLevel) {
										                     
										                     case 1:
										                		 
						                                		 while( DT1[Currentx-1][Currenty]==0){
											                         
							                                         DT1[Currentx-1][Currenty]=1;
							                                         DT1[Currentx][Currenty]=0;
							                                         Currentx--;
							                                         BluePosxDT1[Seled]=Currentx;
							                                         
							                                            if(Currentx>0) {
							                                         
//										                                       
										                                         
							                                            }else {break;}
							                                         
							                                      } 
						                                		 
						                                		 
						                                		 if(Currentx>0) {
							                                         
							                                         if (  DT1[Currentx-1][Currenty]==9){
												                         
											                        	 
									                                        
								                                         DT1[Currentx][Currenty]=0;
								                                         Currentx--;
								                                         BluePosxDT1[Seled]=Currentx;
								                                         
										                               }
							                                         
				                                            }
						                                		 
						                                		 TurnS=11;
										                    	 break;
										                     case 2:
										                		 
						                                		 while( DT2[Currentx-1][Currenty]==0){
											                         
							                                         DT2[Currentx-1][Currenty]=1;
							                                         DT2[Currentx][Currenty]=0;
							                                         Currentx--;
							                                         BluePosxDT2[Seled]=Currentx;
							                                         
							                                            if(Currentx>0) {
							                                         
//										                                       
										                                         
							                                            }else {break;}
							                                         
							                                      } 
						                                		 
						                                		 
						                                		 if(Currentx>0) {
							                                         
							                                         if (  DT2[Currentx-1][Currenty]==9){
												                         
											                        	 
									                                        
								                                         DT2[Currentx][Currenty]=0;
								                                         Currentx--;
								                                         BluePosxDT2[Seled]=Currentx;
								                                         
										                               }
							                                         
				                                            }
						                                		 
						                                		 TurnS=11;
										                    	 break;
										                     case 3:
										                		 
						                                		 while( DT3[Currentx-1][Currenty]==0){
											                         
							                                         DT3[Currentx-1][Currenty]=1;
							                                         DT3[Currentx][Currenty]=0;
							                                         Currentx--;
							                                         BluePosxDT3[Seled]=Currentx;
							                                         
							                                            if(Currentx>0) {
							                                         
//										                                       
										                                         
							                                            }else {break;}
							                                         
							                                      } 
						                                		 
						                                		 
						                                		 if(Currentx>0) {
							                                         
							                                         if (  DT3[Currentx-1][Currenty]==9){
												                         
											                        	 
									                                        
								                                         DT3[Currentx][Currenty]=0;
								                                         Currentx--;
								                                         BluePosxDT3[Seled]=Currentx;
								                                         
										                               }
							                                         
				                                            }
						                                		 
						                                		 TurnS=11;
										                    	 break;
										               
										                     }    
						                                	     
				                                
				                		 
				                                		 
						                                   }    


                                }
			}
			else if(e.getKeyCode()==KeyEvent.VK_RIGHT) //右
			{    
				  
				
				//*******************************************************
                //取当前控制飞船的坐标    
       	   if(TurnS==11) {
       		   switch(DtLevel) {
       		   case 1:
       			   Currentx=RedPosxDT1[Seled];
                      Currenty=RedPosyDT1[Seled];
       			   break;
       		   case 2:
       			   Currentx=RedPosxDT2[Seled];
                      Currenty=RedPosyDT2[Seled];
       			   break;
       		   case 3:
       			   Currentx=RedPosxDT3[Seled];
                      Currenty=RedPosyDT3[Seled];
       			   break;
       		   }
		              
			      }else{
			    	  
  			    	switch(DtLevel) {
	         		   case 1:
	         			    Currentx=BluePosxDT1[Seled];
	   	                    Currenty=BluePosyDT1[Seled];
	         			   break;
	         		   case 2:
	         			    Currentx=BluePosxDT2[Seled];
	   	                    Currenty=BluePosyDT2[Seled];
	         			   break;
	         		   case 3:
	         			    Currentx=BluePosxDT3[Seled];
	   	                    Currenty=BluePosyDT3[Seled];
	         			   break;
        		   }
  			    	  
			    	  
			    	  
			    	   
			      }
				//**********************************************************
				
				
				
//				if(TurnS==11) {
//		            Currentx=RedPosxDT1[Seled];
//                    Currenty=RedPosyDT1[Seled];
//			      }else{
//			    	  Currentx=BluePosxDT1[Seled];
//	                    Currenty=BluePosyDT1[Seled];
//			      }
				// JOptionPane.showMessageDialog(null,"X:"+Integer.toString(Currentx)+"  y:"+Integer.toString(Currenty));
		        //JOptionPane.showMessageDialog(null,"y:"+Integer.toString(Currenty));
				 //JOptionPane.showMessageDialog(null,"t:"+Integer.toString(TurnS));
                                if(TurnS==11){
                                	
	                                        if(Currentx<RightMax ){
					                              switch(DtLevel) {
							                              case 1:
							                                 	while(DT1[Currentx+1][Currenty]==0){
									                                 
									                                 DT1[Currentx+1][Currenty]=1;
									                                 DT1[Currentx][Currenty]=0;
									                                 Currentx++;
									                                 RedPosxDT1[Seled]=Currentx;
									                                 
									                                 
									                                 
									                                 if(Currentx<RightMax ) {
//                                                                     
									                                 }else {break;}
                                                                     
									                            } 
                                       	
					                                        	
					                                        	
					                                        	 if(Currentx<RightMax ) {
                                                                    if(DT1[Currentx+1][Currenty]==9){
						                                    	    
						                                    	    	 
										                                 
										                                 DT1[Currentx][Currenty]=0;
										                                 Currentx++;
										                                 RedPosxDT1[Seled]=Currentx;
										                                 
						                                    	    
									                                 
									                                      }
								                                 }
					                                        	TurnS=22;
							                            	  break;
							                              case 2:
							                                 	while(DT2[Currentx+1][Currenty]==0){
									                                 
									                                 DT2[Currentx+1][Currenty]=1;
									                                 DT2[Currentx][Currenty]=0;
									                                 Currentx++;
									                                 RedPosxDT2[Seled]=Currentx;
									                                 
									                                 
									                                 
									                                 if(Currentx<RightMax ) {
//                                                                     
									                                 }else {break;}
                                                                     
									                            } 
                                       	
					                                        	
					                                        	
					                                        	 if(Currentx<RightMax ) {
                                                                    if(DT2[Currentx+1][Currenty]==9){
						                                    	    
						                                    	    	 
										                                 
										                                 DT2[Currentx][Currenty]=0;
										                                 Currentx++;
										                                 RedPosxDT2[Seled]=Currentx;
										                                 
						                                    	    
									                                 
									                                      }
								                                 }
					                                        	TurnS=22;
							                            	  break;
							                              case 3:
							                                 	while(DT3[Currentx+1][Currenty]==0){
									                                 
									                                 DT3[Currentx+1][Currenty]=1;
									                                 DT3[Currentx][Currenty]=0;
									                                 Currentx++;
									                                 RedPosxDT3[Seled]=Currentx;
									                                 
									                                 
									                                 
									                                 if(Currentx<RightMax ) {
//                                                                     
									                                 }else {break;}
                                                                     
									                            } 
                                       	
					                                        	
					                                        	
					                                        	 if(Currentx<RightMax ) {
                                                                    if(DT3[Currentx+1][Currenty]==9){
						                                    	    
						                                    	    	 
										                                 
										                                 DT3[Currentx][Currenty]=0;
										                                 Currentx++;
										                                 RedPosxDT3[Seled]=Currentx;
										                                 
						                                    	    
									                                 
									                                      }
								                                 }
					                                        	TurnS=22;
							                            	  break;
					                              
					                              }

	                                        	
						                         
	                                        	
	                                        	
	                                        	
	                                        	
	                                        	
	                                         }

                                 }else{
                                	
                                	 
                                	      if(Currentx<RightMax ){
                     					
                                	    	  		switch(DtLevel) {
	                                	    	  		case 1:
	                                          	    	  while(DT1[Currentx+1][Currenty]==0){
								                                 
								                                 DT1[Currentx+1][Currenty]=1;
								                                 DT1[Currentx][Currenty]=0;
								                                 Currentx++;
								                                 BluePosxDT1[Seled]=Currentx;
								                                 if(Currentx<RightMax ){
//										                                
										                                 
								                                 }else {break;}
                 	    	  
								                                 
								                                 
								                                 
								                            }
			                                	    	  if(Currentx<RightMax ){
								                                 if(DT1[Currentx+1][Currenty]==9){											                                 
									                                 DT1[Currentx][Currenty]=0;
									                                 Currentx++;
									                                 BluePosxDT1[Seled]=Currentx;
									                                 							                                    	    
								                                 
								                                 }
								                                 
						                                 }
			                                	    	  TurnS=11;
	                                	    	  			break;
	                                	    	  		case 2:
	                                          	    	  while(DT2[Currentx+1][Currenty]==0){
								                                 
								                                 DT2[Currentx+1][Currenty]=1;
								                                 DT2[Currentx][Currenty]=0;
								                                 Currentx++;
								                                 BluePosxDT2[Seled]=Currentx;
								                                 if(Currentx<RightMax ){
//										                                
										                                 
								                                 }else {break;}
                 	    	  
								                                 
								                                 
								                                 
								                            }
			                                	    	  if(Currentx<RightMax ){
								                                 if(DT2[Currentx+1][Currenty]==9){											                                 
									                                 DT2[Currentx][Currenty]=0;
									                                 Currentx++;
									                                 BluePosxDT2[Seled]=Currentx;
									                                 							                                    	    
								                                 
								                                 }
								                                 
						                                 }
			                                	    	  TurnS=11;
	                                	    	  			break;
	                                	    	  		case 3:
	                                          	    	  while(DT3[Currentx+1][Currenty]==0){
								                                 
								                                 DT3[Currentx+1][Currenty]=1;
								                                 DT3[Currentx][Currenty]=0;
								                                 Currentx++;
								                                 BluePosxDT3[Seled]=Currentx;
								                                 if(Currentx<RightMax ){
//										                                
										                                 
								                                 }else {break;}
                 	    	  
								                                 
								                                 
								                                 
								                            }
			                                	    	  if(Currentx<RightMax ){
								                                 if(DT3[Currentx+1][Currenty]==9){											                                 
									                                 DT3[Currentx][Currenty]=0;
									                                 Currentx++;
									                                 BluePosxDT3[Seled]=Currentx;
									                                 							                                    	    
								                                 
								                                 }
								                                 
						                                 }
			                                	    	  TurnS=11;
	                                	    	  			break;
                                	    	  		
                                	    	  		}

                                	    	  
						                
                                	    	  
                                	    	  
                                	    	  
                                           }


                                 }
			}else if(e.getKeyCode()==KeyEvent.VK_SPACE) //空格选择
			{    
				 switch(DtLevel) {
                 case 1:
                	  if(TurnS==11){  //该红走
                          
	                       
                          //选中变化
                           if (Seled<(RedPosxDT1.length-1)){
                                
                               
                                 Seled=Seled+1;
                               
                               
                           }else{
                                 Seled=0;
                           }

                    Currentx=RedPosxDT1[Seled];
                    Currenty=RedPosyDT1[Seled];
                 
                    //this.repaint();
                 }else{
                           //蓝
                   

	                         //选中变化
	                           if (Seled<BluePosxDT1.length-1){
	                                 Seled=Seled+1;
	                           }else {
	                                 Seled=0;
	                           }
                 
	                           Currentx=BluePosxDT1[Seled];
	                            Currenty=BluePosyDT1[Seled];  
	                            this.repaint();
	                           
                 } 
                 	break;
                 case 2:
                	  if(TurnS==11){  //该红走
                          
	                       
                          //选中变化
                           if (Seled<(RedPosxDT2.length-1)){
                                
                               
                                 Seled=Seled+1;
                               
                               
                           }else{
                                 Seled=0;
                           }

                    Currentx=RedPosxDT2[Seled];
                    Currenty=RedPosyDT2[Seled];
                 
                    //this.repaint();
                 }else{
                           //蓝
                   

	                         //选中变化
	                           if (Seled<BluePosxDT2.length-1){
	                                 Seled=Seled+1;
	                           }else {
	                                 Seled=0;
	                           }
                 
	                           Currentx=BluePosxDT2[Seled];
	                            Currenty=BluePosyDT2[Seled];  
	                            this.repaint();
	                           
                 } 
                 	break;
                 case 3:
                	  if(TurnS==11){  //该红走
                          
	                       
                          //选中变化
                           if (Seled<(RedPosxDT3.length-1)){
                                
                               
                                 Seled=Seled+1;
                               
                               
                           }else{
                                 Seled=0;
                           }

                    Currentx=RedPosxDT3[Seled];
                    Currenty=RedPosyDT3[Seled];
                 
                    //this.repaint();
                 }else{
                           //蓝
                   

	                         //选中变化
	                           if (Seled<BluePosxDT3.length-1){
	                                 Seled=Seled+1;
	                           }else {
	                                 Seled=0;
	                           }
                 
	                           Currentx=BluePosxDT3[Seled];
	                            Currenty=BluePosyDT3[Seled];  
	                            this.repaint();
	                           
                 } 
                 	break;
             
                  }
                           
                           
                        
			}
                      
              
                   switch(DtLevel) {
			                   case 1:
			                	   if(Seled<0||Seled>RedPosxDT1.length-1) {Seled=0;}
			                	   if(Seled<0||Seled>RedPosyDT1.length-1) {Seled=0;}
			                	   if(RedPosxDT1[Seled]==Centerx && RedPosyDT1[Seled]==Centery){ 
			                           
		                                 Redscore++;

		                               //把最后一个元素替代指定的元素
		 
		                               RedPosxDT1[Seled] = RedPosxDT1[RedPosxDT1.length-1];
		 
		                              //数组缩容
		 
		                                 RedPosxDT1 = Arrays.copyOf(RedPosxDT1, RedPosxDT1.length-1);

		                              //把最后一个元素替代指定的元素
		 
		                                  RedPosyDT1[Seled] = RedPosyDT1[RedPosyDT1.length-1];
		 
		                               //数组缩容
		 
		                                   RedPosyDT1 = Arrays.copyOf(RedPosyDT1, RedPosyDT1.length-1);
		                               }
		                             
		                             
		                             
		                             
		                             
			                	   if(Seled<0||Seled>BluePosxDT1.length-1) {Seled=0;}
			                	   if(Seled<0||Seled>BluePosyDT1.length-1) {Seled=0;}
		                             if(BluePosxDT1[Seled]==Centerx && BluePosyDT1[Seled]==Centery){ 
		                                 
		                                 Bluescore++;

		                               //把最后一个元素替代指定的元素
		 
		                               BluePosxDT1[Seled] = BluePosxDT1[BluePosxDT1.length-1];
		 
		                              //数组缩容
		 
		                                 BluePosxDT1 = Arrays.copyOf(BluePosxDT1, BluePosxDT1.length-1);

		                              //把最后一个元素替代指定的元素
		 
		                                  BluePosyDT1[Seled] = BluePosyDT1[BluePosyDT1.length-1];
		 
		                               //数组缩容
		 
		                                   BluePosyDT1 = Arrays.copyOf(BluePosyDT1, BluePosyDT1.length-1);
		                               }
		                             
			                	   break;
			                   case 2:
			                	   if(Seled<0||Seled>RedPosxDT2.length-1) {Seled=0;}
			                	   if(Seled<0||Seled>RedPosyDT2.length-1) {Seled=0;}
			                	   if(RedPosxDT2[Seled]==Centerx && RedPosyDT2[Seled]==Centery){ 
			                           
		                                 Redscore++;

		                               //把最后一个元素替代指定的元素
		 
		                               RedPosxDT2[Seled] = RedPosxDT2[RedPosxDT2.length-1];
		 
		                              //数组缩容
		 
		                                 RedPosxDT2 = Arrays.copyOf(RedPosxDT2, RedPosxDT2.length-1);

		                              //把最后一个元素替代指定的元素
		 
		                                  RedPosyDT2[Seled] = RedPosyDT2[RedPosyDT2.length-1];
		 
		                               //数组缩容
		 
		                                   RedPosyDT2 = Arrays.copyOf(RedPosyDT2, RedPosyDT2.length-1);
		                               }
		                             
		                             
		                             
		                             
		                             
			                	   if(Seled<0||Seled>BluePosxDT2.length-1) {Seled=0;}
			                	   if(Seled<0||Seled>BluePosyDT2.length-1) {Seled=0;}
		                             if(BluePosxDT2[Seled]==Centerx && BluePosyDT2[Seled]==Centery){ 
		                                 
		                                 Bluescore++;

		                               //把最后一个元素替代指定的元素
		 
		                               BluePosxDT2[Seled] = BluePosxDT2[BluePosxDT2.length-1];
		 
		                              //数组缩容
		 
		                                 BluePosxDT2 = Arrays.copyOf(BluePosxDT2, BluePosxDT2.length-1);

		                              //把最后一个元素替代指定的元素
		 
		                                  BluePosyDT2[Seled] = BluePosyDT2[BluePosyDT2.length-1];
		 
		                               //数组缩容
		 
		                                   BluePosyDT2 = Arrays.copyOf(BluePosyDT2, BluePosyDT2.length-1);
		                               }
		                             
			                	    break;
			                   case 3:
			                	   if(Seled<0||Seled>RedPosxDT3.length-1) {Seled=0;}
			                	   if(Seled<0||Seled>RedPosyDT3.length-1) {Seled=0;}
			                	   if(RedPosxDT3[Seled]==Centerx && RedPosyDT3[Seled]==Centery){ 
			                           
		                                 Redscore++;

		                               //把最后一个元素替代指定的元素
		 
		                               RedPosxDT3[Seled] = RedPosxDT3[RedPosxDT3.length-1];
		 
		                              //数组缩容
		 
		                                 RedPosxDT3 = Arrays.copyOf(RedPosxDT3, RedPosxDT3.length-1);

		                              //把最后一个元素替代指定的元素
		 
		                                  RedPosyDT3[Seled] = RedPosyDT3[RedPosyDT3.length-1];
		 
		                               //数组缩容
		 
		                                   RedPosyDT3 = Arrays.copyOf(RedPosyDT3, RedPosyDT3.length-1);
		                               }
		                             
		                             
		                             
		                             
			                	   if(Seled<0||Seled>BluePosxDT3.length-1) {Seled=0;}
			                	   if(Seled<0||Seled>BluePosyDT3.length-1) {Seled=0;}
		                             
		                             if(BluePosxDT3[Seled]==Centerx && BluePosyDT3[Seled]==Centery){ 
		                                 
		                                 Bluescore++;

		                               //把最后一个元素替代指定的元素
		 
		                               BluePosxDT3[Seled] = BluePosxDT3[BluePosxDT3.length-1];
		 
		                              //数组缩容
		 
		                                 BluePosxDT3 = Arrays.copyOf(BluePosxDT3, BluePosxDT3.length-1);

		                              //把最后一个元素替代指定的元素
		 
		                                  BluePosyDT3[Seled] = BluePosyDT3[BluePosyDT3.length-1];
		 
		                               //数组缩容
		 
		                                   BluePosyDT3 = Arrays.copyOf(BluePosyDT3, BluePosyDT3.length-1);
		                               }
		                             
			                	    break;
                 
                   }
                      
                   
                            
                             
                             

                        
			            //  this.repaint();
                      if (Redscore==WinScore){  
                          JOptionPane.showMessageDialog(null,"红方胜利");
                         Redscore=0;Bluescore=0;
                          init();
                          }
                       if (Bluescore==WinScore){  
                          JOptionPane.showMessageDialog(null,"蓝方胜利");
                         Bluescore=0;Redscore=0;
                          init();
                          }
                       this.repaint();
		}
	    public void keyReleased(KeyEvent e) 
	    {
		   //按键抬起
		}  
		
		
		
		
		
		
	}

}


