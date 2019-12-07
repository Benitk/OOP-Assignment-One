 package Ex1;

import java.awt.Color;
import java.util.ArrayList;

public class Draw {

	public Draw(ArrayList<function> functions_list) {
		setFunctions_list(functions_list);
	}
	/**Rainbow Colors source https://stackoverflow.com/questions/4246351/creating-random-colour-in-java/4246418*/
	public void draw_functions(int w, int h, Range rx, Range ry, int res) {
		StdDraw.setCanvasSize(w, h);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max()); // x
		StdDraw.line(rx.get_min(), 0, ry.get_max(), 0);// y
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		StdDraw.setPenRadius(0.003);
		
		for(double x = rx.get_min();x<rx.get_max(); x++ ) {
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			StdDraw.line(x, ry.get_min(), x, ry.get_max());
			StdDraw.setPenColor(Color.BLACK);
			// text
			StdDraw.text(x + 0.05, -0.5, Double.toString(x));
		}
		for(double y = ry.get_min();y<ry.get_max(); y++ ) {
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			StdDraw.line(rx.get_min(), y, rx.get_max(), y);
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text( -0.5, y + 0.05, Double.toString(y));
		}
		StdDraw.setPenRadius(0.01);
		for(int i = 0; i < this.getFunctions_list().size(); i += 1) {
			int R = 200 + (int)(Math.random() * 56);
			int G = 200 + (int)(Math.random() * 56);
			int B= 200 + (int)(Math.random() * 56);
			Color randomColor = new Color(R, G, B);
			
			StdDraw.setPenColor(randomColor);
			double x_step = Math.abs(rx.get_max() - rx.get_min())/res;
			for(double x = rx.get_min(); x < rx.get_max(); x += x_step){
				double y1 = this.getFunctions_list().get(i).f(x);
				double y2 = this.getFunctions_list().get(i).f(x + x_step);
				StdDraw.line(x, y1, x + x_step, y2);;
				
			}
		}
	}	
	
	public ArrayList<function> getFunctions_list() {
		return this._function_list;
	}
	public void setFunctions_list(ArrayList<function> functions_list) {
		this._function_list = functions_list;
	}
	/**** Private methods and data ******/
	private ArrayList<function> _function_list;

}
