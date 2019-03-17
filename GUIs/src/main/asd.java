package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class asd implements ActionListener{
       JFrame jf=new JFrame("学生界面");
    	   private JLabel l1=new JLabel("学校");
    	   private JLabel l2=new JLabel("学号");
    	   private JLabel l3=new JLabel("姓名");
    	   private JLabel l4=new JLabel("学院");
    	   private JLabel l5=new JLabel("班级");

		   private JButton bt1=new JButton("有效记录");
    	   private JButton bt2=new JButton("导出为个人记录的本地表格");
    	   private JLabel l6=new JLabel();
    	   private JLabel l7=new JLabel();
    	   private JLabel l8=new JLabel();
    	   private JLabel l9=new JLabel();
    	   private JLabel l10=new JLabel();
    	   private JLabel l11=new JLabel("晨跑计数次数");
    	   private JLabel l12=new JLabel("长跑计数次数");
    	   private JLabel l13=new JLabel( );
    	   private JLabel l14=new JLabel( );
    	   private JPanel jpl=new JPanel();
    	   
    	   private JButton bt3=new JButton("查询");
    	   private JMenu mFile=new JMenu("dian");
    	   public asd(){
    		   jpl.setLayout(null);
    		   l1.setSize(50,30);
    		   l1.setLocation(150,40);
    		   l2.setSize(50,30);
    		   l2.setLocation(150,80);
    		   l3.setSize(50,30);
    		   l3.setLocation(150,120);
    		   l4.setSize(50,30);
    		   l4.setLocation(150,160);
    		   l5.setSize(50,30);
    		   l5.setLocation(150,200);
    		   l6.setSize(50,30);
    		   l6.setLocation(180,40);
    		   l7.setSize(50,30);
    		   l7.setLocation(180,80);
    		   l8.setSize(50,30);
    		   l8.setLocation(180,120);
    		   l9.setSize(50,30);
    		   l9.setLocation(180,160);
    		   l10.setSize(50,30);
    		   l10.setLocation(180,200);
    		   bt1.setSize(200,50);
    		   bt1.setLocation(175,400);
    		   bt2.setSize(200,50);
    		   bt2.setLocation(475,400);
    		   
    		   bt3.setSize(200,50);
    		   bt3.setLocation(325,300);
    		   bt3.addActionListener(this);
    		   mFile.setLocation(30,30);

    		 
    		   
    		 
    		   jpl.add(l1);
    		   jpl.add(l2);
    		   jpl.add(l3);
    		   jpl.add(l4);
    		   jpl.add(l5);
    		   jpl.add(l6);
    		   jpl.add(l7);
    		   jpl.add(l8);
    		   jpl.add(l9);
    		   jpl.add(l10);
    		   jpl.add(bt1);
    		   jpl.add(bt2);
    		   
    		   jpl.add(bt3);
    		   jpl.add(mFile);
    		   jf.add(jpl);
    		   jf.setSize(850,500);
    		   jf.setVisible(true);
    		   
    	   }
    	   public static void main(String[] args){
    		   new asd();
    	   }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame jFrame = new JFrame();
		 JPanel jpl=new JPanel();
			  String [] columnNames= {"学校","学号","姓名","学院","班级","日期","有效距离（m）","时间","速度（m/s）"};
   		   Object [][] data ={{"南京工业大学","1405170120","鲁澳","CST","计软1701","20181106","2000.0m","12min","2.20m/s"},
   		   {"南京工业大学","1405170120","鲁澳","CST","计软1701","20181106","2000.0m","12min","2.20m/s"},
   		   {"南京工业大学","1405170120","鲁澳","CST","计软1701","20181106","2000.0m","12min","2.20m/s"},
   		   {"南京工业大学","1405170120","鲁澳","CST","计软1701","20181106","2000.0m","12min","2.20m/s"},
   		   {"南京工业大学","1405170120","鲁澳","CST","计软1701","20181106","2000.0m","12min","2.2m/s"}};
   		   JTable table= new JTable (data,columnNames);
  		//	table.setPreferredScrollableViewportSize(new Dimension(1000,1200));//高度和宽度设置
  		//	table.setLocation(10,120);
   		   table.setBounds(0, 0, 800,500);
   		   
  			table.setAutoResizeMode(0);//JTable的一个列或者JTable窗口的自身大小被重新定义，那么他的列会被相应的缩小或者放大，以适应新的窗口
  			JScrollPane pane=new JScrollPane(table);
  			jpl=new JPanel(new GridLayout(0, 1));

  			jpl.add(pane);
  			table.getColumnModel().getColumn(6).setPreferredWidth(200);
  			jFrame.add(jpl);
 		   jFrame.setSize(850,500);
		   jFrame.setVisible(true);
		   l11.setSize(50,30);
		   l11.setLocation(150,40);
		   l12.setSize(50,30);
		   l12.setLocation(150,80);
		   l13.setSize(50,30);
		   l13.setLocation(180,40);
		   l14.setSize(50,30);
		   l14.setLocation(180,160);
		   
		   jpl.add(l11);
		   jpl.add(l12);
		   jpl.add(l13);
		   jpl.add(l14);
		   
		}
    	  
}
