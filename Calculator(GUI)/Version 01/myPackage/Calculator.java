package myPackage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;



public class Calculator{
	
	//class for the GUI frame
	public static class MyFrame extends JFrame implements ActionListener{
		
		//object of numbers to handle the two input numbers
		numbers num = new numbers();
		
		// two displays for the upper and lower parts
		JTextField displayTop, displayBot;
		
		//array of 20 buttons for the calculator
		JButton[] buttons = new JButton[20];
		
		//array of strings for the buttons' labels
		String[] labels = 
		{
			"¹/𝑥" , "𝑥\u00B2" , "√𝑥" , "÷" ,
			"7"   , "8"       , "9"  , "×" ,
			"4"   , "5"       , "6"  , "-" ,
			"1"   , "2"       , "3"  , "+" ,
			"AC"  , "0"       , "."  , "="
		};
		
		//string that tracks the previous clicked button
		String PrevButton = "";
		
		// constructor
		MyFrame(){
			
			this.setTitle("Calculator"); // title
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out application
			this.setSize(300,500); // x and y dimentions
			this.getContentPane().setBackground(new Color(240,240,240)); // background of the main frame
			this.setResizable(false);//prevent being resized
			this.setLayout(new BorderLayout());
			//change image icon
			ImageIcon image = new ImageIcon("Images/icon.png"); // create an image icon
			this.setIconImage(image.getImage()); //change icon of frame
			
			// Displays
			
			//a panel to hold the two displays
			JPanel DisplayPanel = new JPanel();
			DisplayPanel.setLayout(new GridLayout(2,1,0,0)); // a grid of two rows and 1 columns with 0 gaps for the displays
			//Top Display
			displayTop = new JTextField();
			displayTop.setEditable(false);
			displayTop.setPreferredSize(new Dimension(300,75));
			displayTop.setBackground(new Color(245,245,245));
			displayTop.setForeground(new Color(180,180,180));
			displayTop.setBorder(null);
			
			//Bottom Display
			displayBot = new JTextField();
			displayBot.setEditable(false);
			displayBot.setPreferredSize(new Dimension(300,75));
			displayBot.setBackground(new Color(245,245,245));
			displayBot.setBorder(null);
			
			//make the panel with rounded edges
			Border rounded = BorderFactory.createLineBorder(new Color(240,240,240),8,true);
			DisplayPanel.setBorder(BorderFactory.createCompoundBorder(rounded,BorderFactory.createEmptyBorder(0,0,0,0)));
			
			DisplayPanel.add(displayTop); // add top display to panel
			DisplayPanel.add(displayBot); // add bottom display to panel
			
			
			this.add(DisplayPanel,BorderLayout.NORTH); // add the panel to the main frame
			
			//Buttons
			
			//a panel to hold the buttons
			JPanel ButtonsPanel = new JPanel();
			ButtonsPanel.setLayout(new GridLayout(5,4,5,5)); // a grid of 5 rows and 4 columns with 5px gaps
			
			//fonts for displays and buttons
			Font ButtonsFont = new Font("DejaVu Sans" , Font.PLAIN , 20);
			Font DisplayFontTop = new Font("Times New Roman" , Font.PLAIN , 20);
			Font DisplayFontBot = new Font("Times New Roman" , Font.PLAIN , 35);
			displayTop.setFont(DisplayFontTop);
			displayBot.setFont(DisplayFontBot);
			
			for(int i=0 ; i<20 ; i++){
				buttons[i] = new JButton(labels[i]);
				buttons[i].setFocusable(false);
				buttons[i].setFont(ButtonsFont);
				buttons[i].setBackground(new Color(220,220,220));
				buttons[i].setBorder(null);
				buttons[i].addActionListener(this);
				ButtonsPanel.add(buttons[i]);
			}
			
			this.add(ButtonsPanel,BorderLayout.CENTER); // add the panel to the main frame
			this.setVisible(true); // make the frame visible
		}
		
		@Override
		//Actions when buttons are clicked
		public void actionPerformed(ActionEvent e){
			JButton clicked = (JButton)e.getSource();
			String buttonText = clicked.getText(); // get text of the clicked button
			switch(buttonText){
		
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					if(PrevButton == "=" || PrevButton == "¹/𝑥" || PrevButton == "𝑥\u00B2" || PrevButton == "√𝑥"){
						num.clearNumbers();
						displayBot.setText("");
					}
					if(((num.GetIsNum1() && num.GetNum1()==0) || (!num.GetIsNum1() && num.GetNum2()==0)) && !num.GetIsFraction()){
						displayBot.setText(buttonText);
					}else {
						AppendText(displayBot,buttonText);
						if(buttonText == "0")
							num.AddUnits((int)(buttonText.charAt(0)-'0'));
					}
					if(buttonText != "0")
						num.AddUnits((int)(buttonText.charAt(0)-'0'));
					PrevButton = buttonText;
					break;
				
				case "+":
				case "-":
				case "÷":
				case "×":
					num.SetIsNum1(false);
					num.SetIsFraction(false);
					if(num.GetSign() != '0'){
						Operate();
						num.SetNum2(0);
						num.SetSnum2(0);
						if(!displayBot.getText().equals("Overflow"))
							displayBot.setText(num.GetSnum1());
					}
					num.SetSign(buttonText.charAt(0));
					if(!displayBot.getText().equals("Overflow"))
						displayTop.setText(displayBot.getText() + " " + buttonText + " ");
					PrevButton = buttonText;
					break;
					
				case "¹/𝑥":
				case "𝑥\u00B2":
				case "√𝑥":
					num.SetIsFraction(false);
					if(buttonText == "¹/𝑥"){
						displayTop.setText("1/(" + num.GetSnum1() + ")");
						num.SetNum1(Calc.Reci(num.GetNum1()));
					}else if(buttonText == "𝑥\u00B2"){
						if(Calc.Squ(num.GetNum1()) == Double.MAX_VALUE){
							displayBot.setText("Overflow");
						}else {
							displayTop.setText("Sqr(" + num.GetSnum1() + ")");
							double n = Calc.Squ(num.GetNum1());
							num.SetNum1(n);
						}
						
					}else {
						displayTop.setText("Sqrt(" + num.GetSnum1() + ")");
						num.SetNum1(Calc.Sqrt(num.GetNum1()));
					}
					num.SetSnum1(num.GetNum1());
					if(!displayBot.getText().equals("Overflow"))
						displayBot.setText(num.GetSnum1());
					PrevButton = buttonText;
					break;
				
				case "AC":
					displayBot.setText("");
					displayTop.setText("");
					num.clearNumbers();
					PrevButton = "AC";
				break;
				
				case "=":
					if(num.GetSign() == '0'){
						displayTop.setText(num.GetSnum1() + " = ");
					}else {
						displayTop.setText(num.GetSnum1() + " " + num.GetSign() + " " + num.GetSnum2() + " = ");
						Operate();
					}
					num.SetSign('0');
					num.SetNum2(0);
					num.SetSnum2(0);
					if(!displayBot.getText().equals("Can't Divide by 0"))
						displayBot.setText(num.GetSnum1());
					num.SetIsFraction(false);
					PrevButton = "=";
				break;
				
				case ".":
					num.SetIsFraction(true);
					if(num.GetIsNum1() && num.GetNum1()==0){
						displayBot.setText("0");
					}else if(!num.GetIsNum1() && num.GetNum2()==0){
						displayBot.setText("0");
					}
					AppendText(displayBot,".");
					PrevButton = ".";
				break;
				
				default:
				break;
			}
		}
		
		//method to append a text to a display
		void AppendText(JTextField t,String text){
			String s = t.getText();
			if(s.length() <= 14)
				t.setText(s + text);
		}
		
		//operate a certain opration according to the sign
		void Operate(){
			switch(num.GetSign()){
				case '+':
					num.SetNum1(Calc.Sum(num.GetNum1(),num.GetNum2()));
					num.SetSnum1(num.GetNum1());
					break;
				
				case '-':
					num.SetNum1(Calc.Sub(num.GetNum1(),num.GetNum2()));
					num.SetSnum1(num.GetNum1());
					break;
				
				case '×':
					if(Calc.Mul(num.GetNum1(),num.GetNum2()) == Double.MAX_VALUE){
						//check for overflow
						displayBot.setText("Overflow");
						num.clearNumbers();
					}else {
						num.SetNum1(Calc.Mul(num.GetNum1(),num.GetNum2()));
						num.SetSnum1(num.GetNum1());
					}
					break;
				
				case '÷':
					if(num.GetNum2() == 0.0){
						//check if divided by zero
						displayBot.setText("Can't Divide by 0");
						displayTop.setText("");
						num.clearNumbers();
					}else {
						num.SetNum1(Calc.Div(num.GetNum1(),num.GetNum2()));
						num.SetSnum1(num.GetNum1());
					}
					break;
				
				default:
					break;
			}
		}
	}
	
	
	public static void main(String[] arg){
		
		MyFrame frame = new MyFrame();
		//request the Swing framework to refresh the component's appearance
		frame.repaint();
	}
}