package GUI;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Logic.MainfastGIT;


public class Home extends Finestra{
	String segnapostoString=" ";
	public Home() {
		super("Commit n pusH");
		
		JPanel contenuto = new JPanel();
		JScrollPane con = new JScrollPane(contenuto);
		con.setMaximumSize(Est.err);
		contenuto.setMaximumSize(Est.err);
		con.setPreferredSize(Est.err);
		con.setBorder(Est.bordo);
		con.setOpaque(false);
		con.getViewport().setOpaque(false);
		con.getVerticalScrollBar().setUnitIncrement(5);
		
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(MainfastGIT.listaURList.size(),3));
		
		JPanel topJPanel=new JPanel();
		topJPanel.setBorder(Est.bordo);
		topJPanel.setOpaque(false);
		topJPanel.setLayout(new FlowLayout());
		
		JPanel priJPanel=new JPanel();
		priJPanel.setBorder(Est.bordo);
		priJPanel.setOpaque(false);
		priJPanel.setLayout(new FlowLayout());
		Etichetta commEtichetta=new Etichetta("comment:");
		priJPanel.add(commEtichetta);
		
		JPanel secJPanel=new JPanel();
		secJPanel.setBorder(Est.bordoEt);
		secJPanel.setOpaque(false);
		secJPanel.setLayout(new FlowLayout());
		FormVuoto commFormVuoto=new FormVuoto("comment");
		secJPanel.add(commFormVuoto);
		
		topJPanel.add(priJPanel);
		topJPanel.add(secJPanel);
		c.add("North", topJPanel);
		
		for (Entry<String, String> s : MainfastGIT.listaURList.entrySet()) {
			
			JPanel nmJPanel=new JPanel();
			nmJPanel.setBorder(Est.bordo);
			nmJPanel.setOpaque(false);
			nmJPanel.setLayout(new FlowLayout());
			Etichetta nomeE=new Etichetta(s.getKey());
			nmJPanel.add(nomeE);
			contenuto.add(nmJPanel);
			
			JPanel btJPanel=new JPanel();
			btJPanel.setBorder(Est.bordo);
			btJPanel.setOpaque(false);
			btJPanel.setLayout(new FlowLayout());
			Bottone spingi=new Bottone("PUSH");
			spingi.but.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tpString=spingi(s.getValue(), commFormVuoto.ret);
					Avviso okAvviso=new Avviso(tpString);
					okAvviso.setVisible(true);
				}
			});
			btJPanel.add(spingi);
			contenuto.add(btJPanel);
			
			JPanel rmJPanel=new JPanel();
			rmJPanel.setBorder(Est.bordo);
			rmJPanel.setOpaque(false);
			rmJPanel.setLayout(new FlowLayout());
			Bottone rimuovi=new Bottone("DEL");
			rimuovi.but.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	cancella(s.getKey());
			    	Home home=new Home();
			    	home.setVisible(true);
			    	dispose();
			    	
				}
			});
			rmJPanel.add(rimuovi);
			contenuto.add(rmJPanel);
		}
		c.add("Center", con);
		
		Bottone aggiungi=new Bottone("ADD PROJECT",8);
		aggiungi.but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPjk addPrjk=new AddPjk();
		    	addPrjk.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		c.add("South", aggiungi);
		pack();
	}
	
	private String spingi (String urla, String messaggio) {
		String risString="";
		try {
			setCurrentDirectory(urla);
			ProcessBuilder pb = new ProcessBuilder("C:/Program Files/Git/mingw64/libexec/git-core/git.exe","add",".");
			pb.directory(new File(urla));
			pb.redirectErrorStream(true);
			Process p = pb.start();
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line=null;
			while((line=input.readLine()) != null) {
				risString=risString+line+"\n";
			}
			//
			pb = new ProcessBuilder("C:/Program Files/Git/mingw64/libexec/git-core/git.exe","commit","-m",messaggio);
			pb.directory(new File(urla));
			pb.redirectErrorStream(true);
			p = pb.start();
			input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			line=null;
			while((line=input.readLine()) != null) {
				risString=risString+line+"\n";
			}
			//
			pb = new ProcessBuilder("C:/Program Files/Git/mingw64/libexec/git-core/git.exe","push");
			pb.directory(new File(urla));
			pb.redirectErrorStream(true);
			p = pb.start();
			input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			line=null;
			while((line=input.readLine()) != null) {
				risString=risString+line+"\n";
				}
			//
			int exitVal = p.waitFor();
			} catch(Exception e) {
				risString=e.toString();
				e.printStackTrace();
			}
		return risString;
	}
	
	private void cancella(String k) {
		MainfastGIT.listaURList.remove(k);
		MainfastGIT.scarica();
	}
	public static boolean setCurrentDirectory(String directory_name)
    {
		boolean result = false;  // Boolean indicating whether directory was set
		File directory = new File(directory_name).getAbsoluteFile();
		if (directory.exists() || directory.mkdirs()){
			result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
		}
		return result;
	}
}