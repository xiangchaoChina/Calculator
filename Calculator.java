package com.demo;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class Calculator {
	 public static void main(String[] args){         
		 new ClaJFrame();     
	 }
}

// 计算机窗体
class ClaJFrame extends JFrame {
	public ClaJFrame() {
		super("java计算器");
		this.setBounds(500, 500, 200, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(new NumJPanel());
		this.setVisible(true);
	}
}

class NumJPanel extends JPanel {
	String op;// 保存操作符
	String op1, op2;// 读取操作符
	JButton[] JBut_Num = new JButton[10];
	JButton jbt_add;// +
	JButton jbt_sub;// -
	JButton jbt_cheng;// *
	JButton jbt_chu;// /
	JButton jbt_eq;// =
	JButton jbt_c;// 清除
	JTextArea JPText;// 显示区1
	JTextArea JPText1;
	JPanel JPCal = new JPanel();

	public NumJPanel() {
		JPText1 = new JTextArea(1, 15);
		JPText1.setEditable(false);
		this.add(JPText1);
		JPText = new JTextArea(1, 15);// 结果和输入区
		this.add(JPText);
		for (int i = 0; i < 10; i++) {// 添加
			JBut_Num[i] = new JButton(new Integer(i).toString());
			JPCal.add(JBut_Num[i]);
			JBut_Num[i].addActionListener(new BT_Action());
		}
		JPCal.setLayout(new GridLayout(4, 4, 0, 0));
		jbt_add = new JButton("+");
		jbt_add.addActionListener(new Op_Action());
		JPCal.add(jbt_add);
		jbt_sub = new JButton("-");
		jbt_sub.addActionListener(new Op_Action());
		JPCal.add(jbt_sub);
		jbt_cheng = new JButton("*");
		jbt_cheng.addActionListener(new Op_Action());
		JPCal.add(jbt_cheng);
		jbt_chu = new JButton("/");
		jbt_chu.addActionListener(new Op_Action());
		JPCal.add(jbt_chu);
		jbt_eq = new JButton("=");
		jbt_eq.addActionListener(new Op_Action());
		JPCal.add(jbt_eq);
		jbt_c = new JButton("C");
		jbt_c.addActionListener(new Op_Action());
		JPCal.add(jbt_c);
		this.add(JPCal);
	}

	class BT_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPText.append(e.getActionCommand());
			JPText1.append(e.getActionCommand());
		}
	}

	class Op_Action implements ActionListener, Result {// 实现数字按键监听器接口
		@Override
		public void actionPerformed(ActionEvent e) {
			JPText1.append(e.getActionCommand());
			if (e.getActionCommand().equals("-")
					|| e.getActionCommand().equals("+")
					|| e.getActionCommand().equals("*")
					|| e.getActionCommand().equals("/")) {
				op1 = JPText.getText().trim();
				op = e.getActionCommand();
				JPText.setText("");
			}
			if (e.getSource() == jbt_eq) {
				CaL_tor(op);
			}
			if (e.getSource() == jbt_c) {
				JPText.setText("");
				JPText1.setText("");
			}

		}

		public void CaL_tor(String str){          
			if(str=="-"){              
				op2=JPText.getText().trim();             
				JPText.setText(""+Sub(op1,op2));          
			}           
			if(str=="+"){                 
				op2=JPText.getText().trim();             
				JPText.setText(""+Add(op1,op2));          
			}           
			if(str=="*"){ 
		        op2=JPText.getText().trim();             
		        JPText.setText(""+Mul(op1,op2));          
		    }           
			if(str=="/"){              
			  try{              
					op2=JPText.getText().trim();             
					JPText.setText(""+Div(op1,op2));             
			  }catch(ArithmeticException e){                 
					JFrame JD;                 
					JD=new JFrame();                  
					JD.setBounds(550,500,100,80);                 
					JD.add(new JLabel("除数不能为0!"));                 
					JD.setVisible(true);             
			  }          
			}       
		}		
		public int Sub(String str1, String str2) {
			return (Integer.parseInt(str1)) - (Integer.parseInt(str2));
		}

		public int Add(String str1, String str2) {
			return (Integer.parseInt(str1)) + (Integer.parseInt(str2));
		}

		public int Div(String str1, String str2) {
			return (Integer.parseInt(str1)) / (Integer.parseInt(str2));
		}

		public int Mul(String str1,String str2){            
			return    (Integer.parseInt(str1))*(Integer.parseInt(str2)); 
	    }
	}
}
interface Result {
	public abstract int Add(String str1,String str2);     
	public abstract int Sub(String str1,String str2);     
	public abstract int Mul(String str1,String str2);     
	public abstract int Div(String str1,String str2); 
}
