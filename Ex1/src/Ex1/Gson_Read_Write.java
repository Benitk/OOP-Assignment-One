package Ex1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Gson_Read_Write {

	public Gson_Read_Write(String file, ArrayList<function> functions_list) {
		set_fileName(file);
		setFunctions_list(functions_list);
	}
	public void WriteToFile() {

        try (JsonWriter writer = new JsonWriter(new FileWriter("".concat(this.get_fileName()).concat(".json")))) {
            writer.beginObject();                   // {
            writer.setIndent("  ");
            writer.name("functions");                // "messages":
            writer.beginArray();                    // [
            writer.setIndent("   ");
            Iterator<function> iter = this.getFunctions_list().iterator();
            while(iter.hasNext()) {
            	writer.value(iter.next().toString());  // function
            	writer.setIndent("   ");
            }// "msg 3"
            writer.endArray();                      // ]
            writer.endObject();                     // }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public ArrayList<function> ReadFromFile() {
		try (JsonReader reader = new JsonReader(new FileReader("".concat(this.get_fileName()).concat(".json")))) {
			// Create function to use method initFromString
			function cf = new ComplexFunction(new Monom("1"));
			reader.beginObject();

            while (reader.hasNext()) {

                String name = reader.nextName();

                if (name.equals("functions")) {
                    // read array
                    reader.beginArray();

                    while (reader.hasNext()) {
                        this.getFunctions_list().add(cf.initFromString((reader.nextString())));
                    }

                    reader.endArray();

                } else {
                    reader.skipValue(); //avoid some unhandle events
                }
            }

            reader.endObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return this.getFunctions_list();
	}
	
	public ArrayList<function> getFunctions_list() {
		return this._function_list;
	}
	public void setFunctions_list(ArrayList<function> functions_list) {
		this._function_list = functions_list;
	}
	public String get_fileName() {
		return _fileName;
	}
	public void set_fileName(String _fileName) {
		this._fileName = _fileName;
	}
	/**** Private methods and data ******/
	private String _fileName;
	private ArrayList<function> _function_list;

}
