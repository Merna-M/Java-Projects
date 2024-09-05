package myPackage;


public class Calc{
	public static double Sum(double n1,double n2){
		return n1+n2;
	}
	
	public static double Sub(double n1,double n2){
		return n1-n2;
	}
	
	public static double Mul(double n1,double n2){
		//check if there is an overflow
		if(Double.isInfinite(n1*n2)){
			return Double.MAX_VALUE;
		}
		return n1*n2;
	}
	
	public static double Div(double n1,double n2){
		return n1/n2;
	}
	
	public static double Neg(double n){
		return -n;
	}
	
	public static double Sqrt(double n){
		return Math.sqrt(n);
	}
	
	public static double Squ(double n){
		//check if there is an overflow
		if(Double.isInfinite(n*n)){
			return Double.MAX_VALUE;
		}
		return n*n;
	}
	
	public static double Reci(double n){
		return (1/n);
	}
}
