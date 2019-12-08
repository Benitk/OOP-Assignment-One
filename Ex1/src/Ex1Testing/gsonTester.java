package Ex1Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Ex1.Functions_GUI;
import Ex1.Draw;
import Ex1.Polynom;
import Ex1.Range;

public class gsonTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Functions_GUI f = new Functions_GUI();
		f.initFromFile("function_file.txt");
		f.saveToFile("check.txt");
		f.drawFunctions("GUI_params.txt");
		//f.add(new Polynom("-2x"));
		//f.add(new Polynom("-4x +1"));
		//f.add(new Polynom("x^3"));
	//	f.add(new Polynom("-3x^2 + x"));
		//f.add(new Polynom(" x"));
		//f.add(new Polynom("5+ x^2"));
		Range rx = new Range(-15,10);
		Range ry = new Range(-15,10);
		int resolution = 200;
	    f.drawFunctions(1000, 700, rx, ry, resolution);
		/*
		 * f.saveToFile("gson_test");
		 * 
		 * f.initFromFile("gson_test");
		 * System.out.println(f.getFunctions_list().toString());
		 */
	}

}
