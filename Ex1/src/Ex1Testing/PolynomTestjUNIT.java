package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import myMath.Monom;
import myMath.Polynom;

class PolynomTestjUNIT {
	Polynom[] p1 = new Polynom [5];
	@BeforeEach
	void init() {

		p1[0]= new Polynom ("x^2+3x+9");
		p1[1]= new Polynom ("3x^3+x^2+2");
		p1[2]= new Polynom ("2x^2+5x+3");
		p1[3]= new Polynom ("3x^3+x^2");
		p1[4]= new Polynom ("x^2+x");
	}
	@Test
	// test the derivative on my polynom array
	void testderivative() {
		Polynom []ans = {new Polynom("2X+3+0"),new Polynom("2x+9x^2+0"), new Polynom ("4x+5+0"),new Polynom("2x+9x^2"),new Polynom("1+2x")};
		for(int i=0;i<5;i++) {
			String expected = ans[i].toString();
			String actual = p1[i].derivative().toString();
			assertEquals(expected, actual, "Test derivative");
		}}
	@Test
	// compute the value of the polynom array on a given x=2
	void testF() {
		int x = 2;
		double []ans = {19, 30,21,28,6};
		for(int i = 0; i < 5; i++) {
			double expected = ans[i];
			double actual = p1[i].f(x);
			assertEquals(expected, actual, "Test f(2)");
		}}
	@Test
	// test if every monom in my polynom array has coefficent of zero or size fo the pilynom is zero.
	void testisZero() {
		Boolean []ans = {false, false, false, false, false};
		for(int i = 0; i < 5; i++) {
			Boolean expected = ans[i];
			Boolean actual = p1[i].isZero();
			assertEquals(expected, actual, "Test isZero");
		}}
	@Test
	// test when i add polynom to each of me polynom when the power is the same.
	void testAdd() {
		Polynom p2 = new Polynom("3x^2+5x");
		Polynom []ans = {new Polynom("4x^2+8x+9"),new Polynom("3x^3+4x^2+5x+2"),new Polynom("5x^2+10x+3")
				,new Polynom("4x^2+3x^3+5x"),new Polynom("4x^2+6x")};
		for(int i = 0; i < 5; i++) {
			String expected = ans[i].toString();
			p1[i].add(p2);
			System.out.println("Test Worked, when power is different can't add");
			String actual = p1[i].toString();
			assertEquals(expected, actual, "Test add polynom");
		}}
	@Test
	// test when i add monom each of me polynom when the power is the same.
	void testAddM() {
		Polynom p2 = new Polynom("3x");
		Polynom []ans = {new Polynom("x^2+6x+9"),new Polynom("3x^3+x^2+3x+2"),new Polynom("2x^2+8x+3")
				,new Polynom("3x^3+x^2+3x"),new Polynom("x^2+4x")};
		for(int i = 0; i < 5; i++) {
			String expected = ans[i].toString();
			p1[i].add(p2);
			System.out.println("Test Worked, when power is different can't add");
			String actual = p1[i].toString();
			assertEquals(expected, actual, "Test add polynom");
		}}
	@Test
	// 
	void testEquals() {
		Polynom []ans = {new Polynom(p1[0].toString()),new Polynom(p1[1].toString()),new Polynom(p1[2].toString())
				,new Polynom(p1[3].toString()),new Polynom(p1[4].toString())};
		for(int i = 0; i < 5; i++) {
			Polynom expected = ans[i];
			Polynom actual = p1[i];
			if(!(expected.equals(actual))) {
				fail("expected: "+expected.toString()+" but was: "+actual.toString());
			}}}
	@Test
	//test multiply monom (3x) with my polynom array
	void testMultipyM() {
		Monom m1 = new Monom("3x");
		Polynom []ans = {new Polynom("3x^3+9x^2+27x"),new Polynom("3x^3+9x^4+6x"),new Polynom("6x^3+15x^2+9X")
				,new Polynom("3x^3+9x^4"),new Polynom("3x^2+3x^3")};
		for(int i = 0; i < 5; i++) {
			Polynom expected = ans[i];
			p1[i].multiply(m1);
			Polynom actual = p1[i];
			if(!(expected.equals(actual))) {
				fail("expected: "+expected.toString()+" but was: "+actual.toString());
				
			}}}
	@Test
	// test multyply polynom (x^2+1) with my polynom array
	void testMultipy() {
		Polynom m1 = new Polynom("x^2+1");
		Polynom []ans = {new Polynom("x^4+3X^3+10X^2+3X+9"),new Polynom("3X^5+X^4+3X^3+3X^2+2")
				,new Polynom("2X^4+5X^3+5X^2+5x+3"),new Polynom("3X^5+X^4+3X^3+X^2"),new Polynom("X^3+X^4+X+X^2")};
		for(int i = 0; i < 5; i++) {
			Polynom expected = ans[i];
			p1[i].multiply(m1);
			Polynom actual = p1[i];
			if(!(expected.equals(actual))) {
				fail("expected: "+expected.toString()+" but was: "+actual.toString());
				
			}}}	
	@Test
	// test substarct (x^2+x) with my polynom array
	void testsubstract() {
		Polynom p2 = new Polynom("x^2+x");
		Polynom []ans = {new Polynom("2x+9"),new Polynom("3x^3-x+2"),new Polynom("x^2+4x+3")
				,new Polynom("3x^3-x"),new Polynom("")};
		for(int i = 0; i < 5; i++) {
			String expected = ans[i].toString();
			p1[i].substract(p2);
			System.out.println("Test Worked, when power is different can't add");
			String actual = p1[i].toString();
			assertEquals(expected, actual, "Test substract polynom");
		}
	}
	@Test
	//test if my polynom return a string of the polynom.
	void Test_toString_and_Copy() throws Exception {
		String[] monoms = {p1[0].copy().toString(),p1[1].copy().toString(), p1[2].copy().toString(), p1[3].copy().toString(),p1[4].copy().toString()};
		for(int i=0;i<monoms.length;i++) {
			String expected = monoms[i];
			String actual = p1[i].toString();
			assertEquals(expected, actual, "Test toString and copy");
		}
		
	}
	@Test
	//calculate the point of intersection approximately epsilon between two points.
	void rootTest() {
		Polynom p1 = new Polynom("2x^2 -3x - 2");
		double expected = -0.5;
		assertEquals(expected, p1.root(-1, 0, 0.000001));
	}
	@Test
	// calculate the area between two point of the polynom in epsilon steps.
	void areaTest() {
		Polynom p1 = new Polynom("2x");
		assertEquals(Math.abs(p1.area(0, 4, 0.00001) - 16) < 0.0001, true);
	}


}