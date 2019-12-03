package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


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
		// TODO Auto-generated method stub
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
		this.getFunctions_list().clear();
		Gson_Read_Write r = new Gson_Read_Write(file, this.getFunctions_list());
		setFunctions_list(r.ReadFromFile());
	}

	@Override
	public void saveToFile(String file) throws IOException {
		Gson_Read_Write w = new Gson_Read_Write(file, this.getFunctions_list());
		w.WriteToFile();
		
	}
	// Overrite our list to new polynoms
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<function> getFunctions_list() {
		return functions_list;
	}
	public void setFunctions_list(ArrayList<function> functions_list) {
		this.functions_list = functions_list;
	}
/** **************** private methods and data ******/
	private ArrayList<function> functions_list;
	
}
