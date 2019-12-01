package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Functions_GUI  implements functions {
	List<function> functions_list = new ArrayList<function>(); 
	
	
	@Override
	public int size() {
		return functions_list.size();
	}

	@Override
	public boolean isEmpty() {
		return functions_list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return functions_list.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return functions_list.iterator();
	}

	@Override
	public Object[] toArray() {
		return functions_list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return functions_list.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return functions_list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return functions_list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return functions_list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return functions_list.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return functions_list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return functions_list.retainAll(c);
	}

	@Override
	public void clear() {
		functions_list.clear();
		
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

}
