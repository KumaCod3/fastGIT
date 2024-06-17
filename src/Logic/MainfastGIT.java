package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainfastGIT {
	public static ArrayList<String> listaURList=new ArrayList<String>();
	
	
	public static void main(String[] args) {
		carica();
		listaURList.add("prova 123");
		scarica();
		System.out.println(listaURList);
	}

	public static void carica(){
		BufferedReader reader;
		try{
			File file = new File("percorsi");
			
			FileReader fReader = new FileReader(file);
			reader = new BufferedReader(fReader);
			String line;
			while ((line = reader.readLine()) != null){
				listaURList.add(line);
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
			
			for (String stringa:listaURList){
				writer.write(stringa+System.lineSeparator());
			}
			writer.close();

		}
		catch (Exception e){
			// error
		}
	}
}
