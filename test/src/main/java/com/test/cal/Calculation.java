package com.test.cal;

public class Calculation {
	
	CalPrint calPrint;


	int a;
	int b;

	
	public CalPrint getCalPrint() {
		return calPrint;
	}

	public void setCalPrint(CalPrint calPrint) {
		this.calPrint = calPrint;
	}
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}	
	public void print_() {
		calPrint.myPrint(this.a , this.b);
	}

}
