package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import GUI.Home;

public class MainfastGIT {
	public static HashMap<String, String> listaURList=new HashMap<String, String>();
	
	
	public static void main(String[] args) {
		carica();
		Home home=new Home();
		home.setVisible(true);
		
		
		
		
		scarica();
	}

	public static void carica(){
		BufferedReader reader;
		try{
			File file = new File("percorsi");
			
			FileReader fReader = new FileReader(file);
			reader = new BufferedReader(fReader);
			String line;
			while ((line = reader.readLine()) != null){
				String[] tp=line.split(",");
				listaURList.put(tp[0],tp[1]);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void scarica(){
		try{
			FileWriter fWriter = new FileWriter("percorsi", false);
			BufferedWriter writer = new BufferedWriter(fWriter);
			
			for (Entry<String, String> s:listaURList.entrySet()){
				writer.write(s.getKey()+","+s.getValue()+System.lineSeparator());
			}
			writer.close();

		}
		catch (Exception e){
			// error
		}
	}
}
