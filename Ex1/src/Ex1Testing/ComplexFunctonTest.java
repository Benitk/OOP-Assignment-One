package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctonTest {
	ComplexFunction []cf = new ComplexFunction[5];
	
	@BeforeAll
	static void printinit() {
		System.out.println("initialize array of ComplexFunctions before each test");
		System.out.println("0,\n"
				         + "Plus(+3.0x^2 +1.0x^1,-1.0x^4 +2.4x^2 +3.1x^0),\n"
				         + "Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1),\n"
				         + "Min(Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1),+3.0x^2 +5.0x^1),\n"
				         + "Divid(Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1),Max(Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1),+3.0x^2 +5.0x^1))");
	}
	
	@BeforeEach
	void init()
	{	
		 Monom m = new Monom("0");
		 Polynom p1 = new Polynom("+1.0x^1 + 3x^2");
		 Polynom p2 = new Polynom("-1.0x^4 +2.4x^2 +3.1");
		 Polynom p3 = new Polynom("+2.0x^3 +4.0x^0");
		 Polynom p4 = new Polynom("+3.0x^2 +5.0x^1");
		 cf[0] = new ComplexFunction(m);
		 cf[1] = new ComplexFunction("Plus",p1, p2);
		 cf[2] = new ComplexFunction("Comp",p3, p4);
		 cf[3] = new ComplexFunction("Min", cf[2], p4);
		 cf[4] = new ComplexFunction("Divid",cf[2], cf[3]);
	}
	
	@Test
	void testToString() {
		String []ans = {"+0.0x^0","Plus(+3.0x^2 +1.0x^1,-1.0x^4 +2.4x^2 +3.1x^0)","Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1)",
				       "Min(Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1),+3.0x^2 +5.0x^1)",
				       "Divid(Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1),Min(Comp(+2.0x^3 +4.0x^0,+3.0x^2 +5.0x^1),+3.0x^2 +5.0x^1))"};
		for(int i = 0; i < 5; i++) {
			String expected = ans[i];
			String actual = cf[i].toString();
			assertEquals(expected, actual, "Test toString");
		}
	}
	
	@Test
	void testF() {
		int x = 2;
		double []ans = {0.0, 10.7, 21300.0, 22.0, 968.1818181818181};
		for(int i = 0; i < 5; i++) {
			double expected = ans[i];
			double actual = cf[i].f(x);
			assertEquals(expected, actual, "Test F(2)");
		}
	}
	@Test
	void testInitFromString_Copy() {
		int x = 2;
		function []ans = {cf[0].initFromString(cf[0].toString()), cf[1].initFromString(cf[1].toString()),
				cf[2].initFromString(cf[2].toString()), cf[3].initFromString(cf[3].toString()), cf[4].initFromString(cf[4].toString())};
		for(int i = 0; i < 5; i++) {
			String expected_string = ans[i].toString();
			String actual_string = cf[i].copy().toString();
			double expected_f = ans[i].f(x);
			double actual_f = cf[i].copy().f(x);
			assertEquals(expected_string, actual_string, "Test initFromString and copy");
			assertEquals(expected_f, actual_f, "Test initFromString and copy");
		}
	}	
	/*
	 * @Test void testF() { int x = 2; double []ans = {2, -2, -18.4, 0, -2.6};
	 * for(int i = 0; i < 5; i++) { double expected = ans[i]; double actual =
	 * m[i].f(x); assertEquals(expected, actual, "Test f(2)"); } }
	 * 
	 * @Test void testisZero() { Boolean []ans = {false, false, false, true, false};
	 * for(int i = 0; i < 5; i++) { Boolean expected = ans[i]; Boolean actual =
	 * m[i].isZero(); assertEquals(expected, actual, "Test isZero"); } }
	 * 
	 * @Test void testAdd() { Monom m1 = new Monom("3x"); Monom []ans = {new
	 * Monom("4x"),new Monom("2x"),new Monom("-4.6x^2"),new Monom("3x"),new
	 * Monom("-2.6")}; for(int i = 0; i < 5; i++) { String expected =
	 * ans[i].toString(); try { m[i].add(m1); }catch(Exception e) {
	 * System.out.println("Test Worked, when power is different can't add"); }
	 * String actual = m[i].toString(); assertEquals(expected, actual, "Test add");
	 * } }
	 * 
	 * @Test void testMultipy() { Monom m1 = new Monom("3x"); Monom []ans = {new
	 * Monom("3x^2"),new Monom("-3x^2"),new Monom("-13.8x^3"),new Monom("0"),new
	 * Monom("-7.8x")}; for(int i = 0; i < 5; i++) { Monom expected = ans[i];
	 * m[i].multipy(m1); Monom actual = m[i]; if(!(expected.equals(actual))) {
	 * fail("expected: "+expected.toString()+" but was: "+actual.toString()); } } }
	 * 
	 * @Test void testEquals() { Monom []ans = {new Monom(m[0].toString()),new
	 * Monom(m[1].toString()),new Monom(m[2].toString()),new
	 * Monom(m[3].toString()),new Monom(m[4].toString())}; for(int i = 0; i < 5;
	 * i++) { Monom expected = ans[i]; Monom actual = m[i];
	 * if(!(expected.equals(actual))) {
	 * fail("expected: "+expected.toString()+" but was: "+actual.toString()); } } }
	 * 
	 * @Test void testInitFromString_Copy() { Monom m1 = new Monom("3"); String
	 * []ans = {"+1.0x^1", "-1.0x^1", "-4.6x^2", "+0.0x^0", "-2.6x^0"}; for(int i =
	 * 0; i < 5; i++) { String expected = m1.initFromString(ans[i]).toString();
	 * String actual = m[i].copy().toString(); assertEquals(expected, actual,
	 * "Test initFromString and Copy"); } }
	 */

}
