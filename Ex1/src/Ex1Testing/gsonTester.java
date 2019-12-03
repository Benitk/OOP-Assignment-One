package Ex1Testing;

import java.io.IOException;

import Ex1.Functions_GUI;
import Ex1.Polynom;

public class gsonTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Functions_GUI f = new Functions_GUI();
		  f.add(new Polynom("2x")); f.add(new Polynom("4x +1")); f.add(new
		 Polynom("0")); f.add(new Polynom("3x^2 + x")); f.add(new Polynom("5+ x^2"));
		 
		f.saveToFile("gson_test"); 
		 
		f.initFromFile("gson_test");
		System.out.println(f.isEmpty());
	}

}
