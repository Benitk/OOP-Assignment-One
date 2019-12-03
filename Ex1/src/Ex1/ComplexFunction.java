package Ex1;

import java.util.ArrayList;


public class ComplexFunction implements complex_function {
	
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
			current = new Function_Node(null, Operation.None, "None");
			//current.left = new Function_Node(f, Operation.None);
			current.setLeft(new Function_Node(new Polynom(func_s), Operation.None, "None"));
		}
		else {
			try {
				// incase Operation dont in enum
				current = new Function_Node(null, String_toOper(arr.get(0)),arr.get(0));
			}
			catch(Exception e) {
				// Operation isnt valid
				current = new Function_Node(null, Operation.Error, "Error");
			}
			// set left 
			current.setLeft(Complexfunc_Recrusive(arr.get(1), current.getLeft()));
			// set right
			current.setRight(Complexfunc_Recrusive(arr.get(2), current.getRight()));
		}
		return current;
	}
	
	
	public ComplexFunction(function f) {
		if(f == null) {
			throw new RuntimeException("Error: Not Valid Function");
		}
		this.setComplex_root(Complexfunc_Recrusive(f.toString(), this.getComplex_root()));
	}
	
	

	public ComplexFunction(String s, function f1, function f2) {
		if(f1 == null || f2 == null) {
			throw new RuntimeException("Error: Not Valid Function");
		}
		try {
			// incase Operation dont in enum
			this.setComplex_root(new Function_Node(null, String_toOper(s), s));
		}
		catch(Exception e) {
			// Operation isnt valid
			this.setComplex_root(new Function_Node(null, Operation.Error, "Error"));
		}
		
		// issus probalm divid by zero ***&*
		/*
		 * if(this.getComplex_root().getOper().equals(Operation.Divid) &&
		 * f2.toString().equals("0.0x^0")) { throw new
		 * RuntimeException("Error: Can't divide by zero"); }
		 */
		this.getComplex_root().setLeft(Complexfunc_Recrusive(f1.toString(), this.getComplex_root().getLeft()));
		this.getComplex_root().setRight(Complexfunc_Recrusive(f2.toString(), this.getComplex_root().getRight()));
	}
	
	public String toString() {
		String ans = printPostorder(this.getComplex_root());
		return ans;
	}
	
	private String printPostorder(Function_Node current){ 
		//base case
		
		/*
		 * if(current == null) { return ""; }
		 */
        if (current.getOper() == Operation.None && current.getFunc() == null) {
            return printPostorder(current.getLeft());
        }
        // current has function
        else if(current.getOper() == Operation.None) {
        	return current.getFunc().toString();
        }
        // current has oper diffrent from none
        return current.get_operAsString().concat("(".concat(printPostorder(current.getLeft()).concat(",".concat(printPostorder(current.getRight()).concat(")")))));
    } 


	@Override
	//compute f(x) for Complex functions using recrusion side function
	public double f(double x) {
		double ans = fx(this.getComplex_root(), x);
		return ans;
	}
		
	private double fx(Function_Node current, double x){ 

        if (current.getOper() == Operation.None && current.getFunc() == null) {
            return fx(current.getLeft(), x);
        }
        // current has function
        else if(current.getOper() == Operation.None) {
        	return current.getFunc().f(x);
        }
        // current has operation diffrent from none compute is fx
        switch(current.getOper().toString()){
        	case "Plus":
        		return fx(current.getLeft(), x) + fx(current.getRight(), x);
        		
        	case "Times":
        		return fx(current.getLeft(), x) * fx(current.getRight(), x);
        		
        	case "Divid":
        		return fx(current.getLeft(), x) / fx(current.getRight(), x);
        		
        	case "Max":
        		return Math.max(fx(current.getLeft(), x),fx(current.getRight(), x));
        		
        	case "Min":
        		return Math.min(fx(current.getLeft(), x),fx(current.getRight(), x));
        		
        	case "Comp":
        		return fx(current.getLeft(),fx(current.getRight(), x));
        		
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
		cf.setComplex_root(new_root);
		function f = new ComplexFunction(cf);
		return f;
	}

	@Override
	public function copy() {
		function f = new ComplexFunction(this);
		return f;
	}

	@Override
	public void plus(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = this.getComplex_root();
		this.setComplex_root(new Function_Node(null, Operation.Plus, "plus"));
		this.getComplex_root().setLeft(left_subtree);
		this.getComplex_root().setRight(right_subtree.getComplex_root());
	}

	@Override
	public void mul(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = this.getComplex_root();
		this.setComplex_root(new Function_Node(null, Operation.Times, "mul"));
		this.getComplex_root().setLeft(left_subtree);
		this.getComplex_root().setRight(right_subtree.getComplex_root());
	}

	@Override
	public void div(function f1) {
		//pointer to the complex root
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = this.getComplex_root();
		this.setComplex_root(new Function_Node(null, Operation.Divid, "div"));
		this.getComplex_root().setLeft(left_subtree);
		this.getComplex_root().setRight(right_subtree.getComplex_root());
	}

	@Override
	public void max(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = this.getComplex_root();
		this.setComplex_root(new Function_Node(null, Operation.Max, "max"));
		this.getComplex_root().setLeft(left_subtree);
		this.getComplex_root().setRight(right_subtree.getComplex_root());
	}

	@Override
	public void min(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = this.getComplex_root();
		this.setComplex_root(new Function_Node(null, Operation.Min, "min"));
		this.getComplex_root().setLeft(left_subtree);
		this.getComplex_root().setRight(right_subtree.getComplex_root());
	}

	@Override
	public void comp(function f1) {
		//pointer to the complex root
		ComplexFunction right_subtree = new ComplexFunction(f1);
		Function_Node left_subtree = this.getComplex_root();
		this.setComplex_root(new Function_Node(null, Operation.Comp, "comp"));
		this.getComplex_root().setLeft(left_subtree);
		this.getComplex_root().setRight(right_subtree.getComplex_root());
	}

	@Override
	// creating temp Complex function
	public function left() {;
		ComplexFunction cf = new ComplexFunction(new Polynom("1"));
		cf.setComplex_root(this.getComplex_root().getLeft());
		function f = new ComplexFunction(cf);
		return f;
	}

	@Override
	public function right() {
		ComplexFunction cf = new ComplexFunction(new Polynom("1"));
		cf.setComplex_root(this.getComplex_root().getRight());
		function f = new ComplexFunction(cf);
		return f;
	}
	@Override
	public Operation getOp() {
		return this.getComplex_root().getOper();
	}
	public Function_Node getComplex_root() {
		return this._Complex_root;
	}
	public void setComplex_root(Function_Node fn) {
		this._Complex_root = fn;
	}


	/********* private fields ********/
	private class Function_Node{ 
		private Operation _oper;
		private String _operAsString;
		private function _func; 
		private Function_Node _left, _right; 

		public Operation getOper() {
			return _oper;
		}

		public void setOper(Operation oper) {
			this._oper = oper;
		}

		public function getFunc() {
			return _func;
		}

		public void setFunc(function func) {
			this._func = func;
		}

		public Function_Node getLeft() {
			return _left;
		}

		public void setLeft(Function_Node left) {
			this._left = left;
		}

		public Function_Node getRight() {
			return _right;
		}

		public void setRight(Function_Node right) {
			this._right = right;
		}
		public String get_operAsString() {
			return _operAsString;
		}

		public void set_operAsString(String operAsString) {
			this._operAsString = operAsString;
		} 

		public Function_Node(function f, Operation o, String s) {
			set_operAsString(s);
			setFunc(f); 
			setOper(o);
			setLeft(null);
			setRight(null); 
		}
	}
	private Operation String_toOper(String s) {
		switch(s) {
		 case "plus":
			 return Operation.Plus;
		 case "mul":
			 return Operation.Times;
		 case "div":
			 return Operation.Divid;
		 case "max":
			 return Operation.Max;
		 case "min":
			 return Operation.Min;
		 case "comp":
			 return Operation.Comp;
     	default:
    		throw new RuntimeException("Error: Not Valid Operation");
		}
	}
	private Function_Node _Complex_root;
}
