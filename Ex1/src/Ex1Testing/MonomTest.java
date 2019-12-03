package Ex1Testing;

import Ex1.Monom;

/**
 * This class represents a simple (naive) tester for the Monom class, Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be
 * changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and
 * functionality of the Monom class. <br>
 * (iii) Expected output: <br>
 * ***** Test1: ***** <br>
 * 0) 2.0 isZero: false f(0) = 2.0 <br>
 * 1) -1.0x isZero: false f(1) = -1.0 <br>
 * 2) -3.2x^2 isZero: false f(2) = -12.8 <br>
 * 3) 0 isZero: true f(3) = 0.0 <br>
 ***** Test2: ***** <br>
 * 0) 0 isZero: true eq: true <br>
 * 1) -1.0 isZero: false eq: true <br>
 * 2) -1.3x isZero: false eq: true <br>
 * 3) -2.2x^2 isZero: false eq: true <br>
 */
public class MonomTest {
	public static void main(String[] args) {	
		  test1(); 
	}
	/**
	 * testing all the methods in Monom class
	 * loop on an array of Monoms strings
	 * That the first part is considered good monoms and the second part is considered bad monoms
	 * then printing the number of exceptions
	 */
	private static void test1() {
		System.out.println("*****  Test1:  Monom functions  *****");
		String monom_string = "";
		int exception_counter = 0;
		// good and bad example of monoms
		String[] monoms = {"x", "-x", "-4.6X^2", "0", "-2.6","10x^1", "(x)","-5.5x3", "2x^2.5", "bx^3", "2x^^3","x^-1", "x^b", "3xx" };
		Monom m_2x = new Monom("2x");
	
		for (int i = 0; i < monoms.length; i++) {
			try {
				Monom m = new Monom(monoms[i]);
				
				System.out.println("test function f(x) for " + m + " f("+i+") = " + m.f(i) + "\n");

				System.out.println("test function derivative for " + m + " f(x)' = " + m.derivative() + "\n");
				
				System.out.println("test function equal for " + m + " to himself");
				Monom temp = new Monom(m);
				System.out.println("equal ? " + m.equals(temp) + "\n");
				System.out.println("test function equal for " + m + " to 2x");
				System.out.println("equal ? " + m.equals(m_2x) + "\n");
				
				System.out.println("test function isZero for " + m + " is Zero ? " + m.isZero() + "\n");
				
				System.out.println("test function add for " + m + " with himself");
				monom_string = m.toString();
				m.add(m);
				System.out.println(monom_string + " " + monom_string + " = " + m + "\n");
				
				System.out.println("test function multiply for " + m);
				monom_string = m.toString();
				m.multipy(m_2x);
				System.out.println(monom_string + " * 2x = " + m + "\n");
				System.out.println("**** next Monom *** \n");
				
			}catch(Exception e) {
				exception_counter++;
				e.printStackTrace();
				continue;
			}
		}
		System.out.println("The number of 'bad monoms' is 8");
		System.out.println("number of exception is - " + exception_counter);
	}
	
}
