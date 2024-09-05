package myPackage;

public class numbers{
	private double num1;
	private double num2;
	private String snum1;
	private String snum2;
	private boolean isNum1;
	private boolean isFraction;
	private char sign;
	static double fractionM;
	
	/********************************************************************************************************/
	/********************************************* Contructor ***********************************************/
	/********************************************************************************************************/
	numbers(){
		num1 = 0;
		num2 = 0;
		sign = '0';
		snum1 = "";
		snum2 = "";
		isNum1 = true;
		isFraction = false;
		fractionM = 0.1;
	}
	
	/********************************************************************************************************/
	/*********************************************** Setters ************************************************/
	/********************************************************************************************************/
	
	void SetNum1(double n1){
		num1 = n1;
	}
	/********************************************************************************************************/
	void SetNum2(double n2){
		num2 = n2;
	}
	/********************************************************************************************************/
	void SetIsNum1(boolean b){
		isNum1 = b;
	}
	/********************************************************************************************************/
	void SetIsFraction(boolean b){
		isFraction = b;
		if(b == false){
			//return fractionM to its initial value
			fractionM = 0.1;
		}
	}
	/********************************************************************************************************/
	void SetSign(char sign){
		this.sign = sign;
	}
	/********************************************************************************************************/
	void SetSnum1(double n){
		if(n == 0){
			snum1 = "0";
		}else if(n == Math.floor(n)){
			//Integer Value
			snum1 = String.format("%.0f",n);
			int sLen = snum1.length();
			if(sLen >= 14){
				snum1 = String.format("%e", n);
			}
		}else {
			int BeforeDot,AfterDot;
			BeforeDot = String.valueOf((long)n).length();
			AfterDot = 14 - BeforeDot;
			snum1 = String.format("%." + AfterDot + "f",n).replaceAll("0*$", "").replaceAll("\\.$", "");
		}
	}
	/********************************************************************************************************/
	void SetSnum2(double n){
		if(n == 0){
			snum2 = "0";
		}else if(n == Math.floor(n)){
			//Integer Value
			snum2 = String.format("%.0f",n);
			int sLen = snum2.length();
			if(sLen >= 14){
				snum2 = String.format("%e", n);
			}
		}else {
			int BeforeDot,AfterDot;
			BeforeDot = String.valueOf((long)n).length();
			AfterDot = 14 - BeforeDot;
			snum2 = String.format("%." + AfterDot + "f",n).replaceAll("0*$", "").replaceAll("\\.$", "");
		}
	}
	
	
	/********************************************************************************************************/
	/*********************************************** Getters ************************************************/
	/********************************************************************************************************/
	
	double GetNum1(){
		return num1;
	}
	/********************************************************************************************************/
	double GetNum2(){
		return num2;
	}
	/********************************************************************************************************/
	boolean GetIsNum1(){
		return isNum1;
	}
	/********************************************************************************************************/
	boolean GetIsFraction(){
		return isFraction;
	}
	/********************************************************************************************************/
	char GetSign(){
		return sign;
	}
	/********************************************************************************************************/
	String GetSnum1(){
		return snum1;
	}
	/********************************************************************************************************/
	String GetSnum2(){
		return snum2;
	}
	/********************************************************************************************************/
	
	/********************************************************************************************************/
	/*********************************************** Methods ************************************************/
	/********************************************************************************************************/
	void clearNumbers(){
		num1 = num2 = 0;
		snum1 = "";
		snum2 = "";
		isNum1 = true;
		sign = '0';
		isFraction = false;
		fractionM = 0.1;
	}
	/********************************************************************************************************/
	void AddUnits(int n){
		//refuse all values with more than 14 digits
		if(isNum1 && snum1.length()<=14){
			if(!isFraction){
				//handling integers
				num1 = num1*10 + (double)n;
				if(snum1 == "0")
					snum1 = "";
				snum1 = snum1 + (char)(n+ '0');
			}else {
				//handling the fraction part
				num1 = num1 + n*fractionM;
				if((long)num1 == 0)
					snum1 = "0";
				if(fractionM == 0.1)
					snum1 = snum1 + '.';
				snum1 = snum1 + (char)(n+ '0');
				fractionM /= 10;
			}
			
		}else if(!isNum1 && snum2.length()<=14){
			//refuse all values with more than 14 digits
			if(!isFraction){
				//handling integers
				num2 = num2*10 + (double)n;
				if(snum2 == "0")
					snum2 = "";
				snum2 = snum2 + (char)(n+ '0');
			}else {
				//handling the fraction part
				num2 = num2 + n*fractionM;
				if((long)num2 == 0)
					snum2 = "0";
				if(fractionM == 0.1)
					snum2 = snum2 + '.';
				snum2 = snum2 + (char)(n+ '0');
				fractionM /= 10;
			}
		}
	}
	/********************************************************************************************************/
}