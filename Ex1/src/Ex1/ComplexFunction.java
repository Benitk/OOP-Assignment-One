package Ex1;

import java.util.ArrayList;


public class ComplexFunction implements complex_function {
	private class Function_Node{ 
		Operation oper;
		function func; 
		Function_Node left, right; 

		public Function_Node(function f, Operation o) { 
			this.func = f; 
			this.oper = o;
			this.left = this.right = null; 
		} 
	}
	private Function_Node Complex_root;

	
	// split the string first index is Operation , left side of comma, right side of comma
	private ArrayList<String> Peeling(String s) {
		ArrayList<String> arr = new ArrayList<String>();
		// returns the index of first occurrence of the specified character
		int index_operation = s.indexOf('(');
		// returns the last occurrence of the character 
		int index_comma = s.lastIndexOf(',');
		// function isnt a complex one
		if(index_comma == -1) {
			arr.add("");
			arr.add(s);
			return arr;
		}
		int number_parenttheses = 0;
		// this loop is to get the right comma for each parrantiss
		for(int i = 0;i < s.length(); i++) {
			if(s.charAt(i) == '(') { number_parenttheses++;}
			else if(s.charAt(i) == ')') { number_parenttheses--;}
			// got the right comma
			if(s.charAt(i) == ',' && number_parenttheses == 1 ) {
				index_comma = i;
				break;
			}
		} 
		// Operation
		arr.add(s.substring(0, index_operation));
		//left side of comma
		arr.add(s.substring(index_operation + 1, index_comma));
		// right side of cooma
		arr.add(s.substring(index_comma + 1, s.length() - 1));
		
		return arr;
	}
	private Function_Node Complexfunc_Recrusive(String func_s,Function_Node current) {
		ArrayList<String> arr = Peeling(func_s);
		// no operation declare None as current and left as 'f'
		if(arr.get(0) == "") {
			current = new Function_Node(null, Operation.None);
			//current.left = new Function_Node(f, Operation.None);
			current.left = new Function_Node(new Polynom(func_s), Operation.None);
		}
		else {
			try {
				// incase Operation dont in enum
				current = new Function_Node(null, Operation.valueOf(arr.get(0)));
			}
			catch(Exception e) {
				// Operation isnt valid
				current = new Function_Node(null, Operation.Error);
			}
			// set left 
			current.left = Complexfunc_Recrusive(arr.get(1), current.left);
			// set right
			current.right = Complexfunc_Recrusive(arr.get(2), current.right);
		}
		return current;
	}
	
	public ComplexFunction(function f) {
		if(f == null) {
			throw new RuntimeException("Error: Not Valid Function");
		}
		this.Complex_root = Complexfunc_Recrusive(f.toString(), Complex_root);
	}
	
	

	public ComplexFunction(String s, function f1, function f2) {
		if(f1 == null || f2 == null) {
			throw new RuntimeException("Error: Not Valid Function");
		}
		try {
			// incase Operation dont in enum
			this.Complex_root = new Function_Node(null, Operation.valueOf(s));
		}
		catch(Exception e) {
			// Operation isnt valid
			this.Complex_root = new Function_Node(null, Operation.Error);
		}
		this.Complex_root.left = Complexfunc_Recrusive(f1.toString(), Complex_root.left);
		this.Complex_root.right = Complexfunc_Recrusive(f2.toString(), Complex_root.right);
	}
	
	public String toString() {
		String ans = printPostorder(this.Complex_root);
		return ans;
	}
	
	String printPostorder(Function_Node current){ 
		//base case
		
		/*
		 * if(current == null) { return ""; }
		 */
        if (current.oper == Operation.None && current.func == null) {
            return printPostorder(current.left);
        }
        // current has function
        else if(current.oper == Operation.None) {
        	return current.func.toString();
        }
        // current has oper diffrent from none
        return current.oper.toString().concat("(".concat(printPostorder(current.left).concat(",".concat(printPostorder(current.right).concat(")")))));
    } 


	@Override
	//compute f(x) for Complex functions using recrusion side function
	public double f(double x) {
		double ans = fx(this.Complex_root, x);
		return ans;
	}
	
	
	private double fx(Function_Node current, double x){ 

        if (current.oper == Operation.None && current.func == null) {
            return fx(current.left, x);
        }
        // current has function
        else if(current.oper == Operation.None) {
        	return current.func.f(x);
        }
        // current has operation diffrent from none compute is fx
        switch(current.oper.toString()){
        	case "Plus":
        		return fx(current.left, x) + fx(current.right, x);
        		
        	case "Times":
        		return fx(current.left, x) * fx(current.right, x);
        		
        	case "Divid":
        		return fx(current.left, x) / fx(current.right, x);
        		
        	case "Max":
        		return Math.max(fx(current.left, x),fx(current.right, x));
        		
        	case "Min":
        		return Math.min(fx(current.left, x),fx(current.right, x));
        		
        	case "Comp":
        		return fx(current.left,fx(current.right, x));
        		
        	default:
        		throw new RuntimeException("Error: Not Valid Operation");
        }
	}
	@Override
	//creating new ComplexFunction using tmp polynom, then creating other complexroot from the string and replace the root
	public function initFromString(String s) {
		Function_Node new_root = null;
		new_root = Complexfunc_Recrusive(s, new_root);
		ComplexFunction cf = new ComplexFunction(new Polynom("1"));
		cf.Complex_root = new_root;
		function f = new ComplexFunction(cf);
		return f;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = Complex_root;
		this.Complex_root = new Function_Node(null, Operation.Plus);
		this.Complex_root.left = left_subtree;
		this.Complex_root.right = right_subtree.Complex_root;
	}

	@Override
	public void mul(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = Complex_root;
		this.Complex_root = new Function_Node(null, Operation.Times);
		this.Complex_root.left = left_subtree;
		this.Complex_root.right = right_subtree.Complex_root;
	}

	@Override
	public void div(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = Complex_root;
		this.Complex_root = new Function_Node(null, Operation.Divid);
		this.Complex_root.left = left_subtree;
		this.Complex_root.right = right_subtree.Complex_root;
	}

	@Override
	public void max(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = Complex_root;
		this.Complex_root = new Function_Node(null, Operation.Max);
		this.Complex_root.left = left_subtree;
		this.Complex_root.right = right_subtree.Complex_root;
	}

	@Override
	public void min(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = Complex_root;
		this.Complex_root = new Function_Node(null, Operation.Min);
		this.Complex_root.left = left_subtree;
		this.Complex_root.right = right_subtree.Complex_root;
	}

	@Override
	public void comp(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = Complex_root;
		this.Complex_root = new Function_Node(null, Operation.Comp);
		this.Complex_root.left = left_subtree;
		this.Complex_root.right = right_subtree.Complex_root;
	}

	@Override
	// creating temp Complex function
	public function left() {;
		ComplexFunction cf = new ComplexFunction(new Polynom("1"));
		cf.Complex_root = this.Complex_root.left;
		function f = new ComplexFunction(cf);
		return f;
	}

	@Override
	public function right() {
		ComplexFunction cf = new ComplexFunction(new Polynom("1"));
		cf.Complex_root = this.Complex_root.right;
		function f = new ComplexFunction(cf);
		return f;
	}
	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return null;
	}

	/********* private fields ********/

}
