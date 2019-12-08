package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

public class PolynomTest {
	public static void main(String[] args) {
		test2();
	}
	
	/**
	 * testing all the methods in Polynom class
	 * loop on an array of polynoms strings
	 * That the first part is considered good polynoms and the second part is considered bad polynoms
	 * then printing the number of exceptions
	 */
	
	
	
	private static void test2() {
		 Monom m = new Monom("-0");
		 Polynom p1 = new Polynom("1");
		 Polynom p3 = new Polynom("+1.0x^1  -1.0x^4 +2.4x^2 +3");
		 Polynom p2 = new Polynom("-1.0x^4 +2.4x^2 +3");
		 ComplexFunction[] cf = new ComplexFunction[5];
		 cf[0] = new ComplexFunction(m);
		 cf[1] = new ComplexFunction("div",p1, m);
		 cf[2] = new ComplexFunction("plus",p3, new Monom("0"));
		 function s = cf[0].initFromString("div(x,0)");
		 System.out.println(p1);
		/*
		 * cf[3] = new ComplexFunction("min", cf[2], p4); cf[4] = new
		 * ComplexFunction("div",cf[2], cf[3]);
		 */
		 System.out.println(Double.isInfinite(3/Double.parseDouble("-0")));
		 function f = cf[0].initFromString("div(plus(3x,x),0)");
		 System.out.println(f.f(0));
		
		/*
		 * System.out.println(cf[3].f(2)); System.out.println(cf[4].toString());
		 * function f =cf[0].initFromString(cf[4].toString()); System.out.println(f);
		 */
	}
	
	private static void test1() {
	
		
		System.out.println("*****  Test1:  Polynom functions  *****");
		String polynom_string = "";
		int exception_counter = 0;
		// good and bad example of polynoms
		String[] polynom = {"x + x^2", "0x^0 + 0", "-3.2X^2 + x + 3", "-2x^2 + x^2 + 8x + 5", "-2.6","10x^1 + 3xx", "(x + 2x^3)","-5.5x3*(x + 3x^3)", "(2x^2-4)*(-1.2x-7.1)", "3 + x^3 - bx^3", "3 + 3x^-1"};
		Polynom p_2x = new Polynom("2x");
		Polynom p_3x_xpow2 = new Polynom("3x + x^2");
		Polynom p_x_3 = new Polynom("x + 3");
	
		for (int i = 0; i < polynom.length; i++) {
			try {
				Polynom p = new Polynom(polynom[i]);
				System.out.println("test function f(x) for " + p + " f("+i+") = " + p.f(i) + "\n");
				
				System.out.println("test function derivative for " + p + " f(x)' = " + p.derivative() + "\n");

				System.out.println("test function equal for " + p + " to himself");
				Polynom temp = new Polynom(p.toString());
				System.out.println("equal ? " + p.equals(temp) + "\n");
				System.out.println("test function equal for " + p + " to 3x + x^2");
				System.out.println("equal ? " + p.equals(p_3x_xpow2) + "\n");
				
				System.out.println("test function isZero for " + p + " is Zero ? " + p.isZero() + "\n");
				
				System.out.println("test function add for " + p);
				polynom_string = p.toString();
				p.add(p);
				System.out.println(polynom_string + " " + polynom_string + " = " + p + "\n");
				
				System.out.println("test function add for " + p);
				polynom_string = p.toString();
				p.add(p_2x);
				System.out.println(polynom_string + " + 2x" + " = " + p + "\n");
				
				System.out.println("test function substract for " + p);
				polynom_string = p.toString();
				p.substract(p_3x_xpow2);
				System.out.println(polynom_string + " -(3x + x^2)" + " = " + p + "\n");
				
				System.out.println("test function multiply for " + p);
				polynom_string = p.toString();
				p.multiply(p_2x);
				System.out.println("(" + polynom_string + ")" + " * 2x = " + p + "\n");
				
				System.out.println("test function multiply for " + p);
				polynom_string = p.toString();
				p.multiply(p_3x_xpow2);
				System.out.println("(" + polynom_string + ")" + " * (3x + x^2) = " + p + "\n");
				
				System.out.println("test function root for " + p_x_3 + " root' = " + p_x_3.root(-6 , 0, 0.00001) + "\n");

				System.out.println("test function area for " + p_x_3 + " area' = " + p_x_3.area(-3 , 0, 0.00001) + "\n");
				
				System.out.println("test function copy for " + p);
				Polynom_able p1 = (Polynom_able) p.copy();
				System.out.println( p1 + " = " + p + "\n");
				
				System.out.println("**** next Polynom *** \n");
				
				
			}catch(Exception e) {
				exception_counter++;
				e.printStackTrace();
				continue;
			}
		}
		System.out.println("The number of 'bad polynoms' is 6");
		System.out.println("number of exception is - " + exception_counter);
	}	
}
