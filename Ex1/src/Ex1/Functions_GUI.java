package Ex1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.util.Scanner;

import com.google.gson.Gson;





public class Functions_GUI  implements functions {

	
	
	public Functions_GUI() {
		setFunctions_list(new ArrayList<function>());
	}

	@Override
	public int size() {
		return getFunctions_list().size();
	}

	@Override
	public boolean isEmpty() {
		return getFunctions_list().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return getFunctions_list().contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return getFunctions_list().iterator();
	}

	@Override
	public Object[] toArray() {
		return getFunctions_list().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return getFunctions_list().toArray(a);
	}

	@Override
	public boolean add(function e) {
		return getFunctions_list().add(e);
	}

	@Override
	public boolean remove(Object o) {
		return getFunctions_list().remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return getFunctions_list().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return getFunctions_list().addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return getFunctions_list().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return getFunctions_list().retainAll(c);
	}

	@Override
	public void clear() {
		getFunctions_list().clear();
		
	}

	@Override
	/** 
	 * source code for gson: https://www.mkyong.com/java/gson-streaming-to-read-and-write-json/?utm_source=mkyong.com&utm_medium=Referral&utm_campaign=afterpost-related&utm_content=link1
	 * */
	public void initFromFile(String file) throws IOException {
		try {
			Scanner scanner = new Scanner(new File(file));
			function cf = new ComplexFunction(new Monom("1"));
			while (scanner.hasNextLine()) {
				this.getFunctions_list().add(cf.initFromString(scanner.nextLine()));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(this.getFunctions_list().toString());
	}

	@Override
	public void saveToFile(String file) throws IOException {
				File file_to_save = null;
				FileWriter filewriter = null;
				try {
					file_to_save = new File(file);
					filewriter = new FileWriter(file_to_save);
					Iterator<function> iter = this.iterator();
					while(iter.hasNext()) {
						filewriter.write(iter.next().toString());
						filewriter.write(System.getProperty( "line.separator" ));
					}
					filewriter.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (filewriter != null) {
							filewriter.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		
	}
	// Overrite our list to new polynoms
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		Draw d = new Draw(this.getFunctions_list());
		d.draw_functions(width, height, rx, ry, resolution);
	}

	
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(json_file);
			Params param = gson.fromJson(reader,Params.class);
			int height = param.get_height();
			int width = param.get_width();
			int resolution = param.get_resolution();
			Range rx = new Range(param.get_rx()[0],param.get_rx()[1]);
			Range ry = new Range(param.get_ry()[0],param.get_ry()[1]);
			drawFunctions(width, height, rx, ry, resolution);
		} 
		catch (FileNotFoundException e) {
			System.out.println("not found using default paramters");
			Params param = new Params();
			int height = param.get_height();
			int width = param.get_width();
			int resolution = param.get_resolution();
			Range rx = new Range(param.get_rx()[0],param.get_rx()[1]);
			Range ry = new Range(param.get_ry()[0],param.get_ry()[1]);
			drawFunctions(width, height, rx, ry, resolution);
		}
	}
	public ArrayList<function> getFunctions_list() {
		return _functions_list;
	}
	public void setFunctions_list(ArrayList<function> functions_list) {
		this._functions_list = functions_list;
	}
/** **************** private methods and data ******/
	private ArrayList<function> _functions_list;
	
}
