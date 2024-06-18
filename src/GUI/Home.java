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
			    	spingi(s.getValue(), commFormVuoto.ret);
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
	
	private void spingi (String urla, String messaggio) {
		// TODO
		 try {
			 System.out.println(urla);
			 
             Runtime rt = Runtime.getRuntime();
             
             setCurrentDirectory(urla);
             Process pr = rt.exec("git add .");
             BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
             String line=null;
             while((line=input.readLine()) != null) {
                 System.out.println(line);
             }
             
             /*
             pr =rt.exec("bla");
             input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
             while((line=input.readLine()) != null) {
            	 System.out.println(line);
             }*/
             //
             pr = rt.exec("git commit -m "+messaggio);
             input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
             while((line=input.readLine()) != null) {
            	 System.out.println(line);
             }
             //
             pr = rt.exec("git push");
             input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
             while((line=input.readLine()) != null) {
            	 System.out.println(line);
             }
             //
             

             int exitVal = pr.waitFor();
             System.out.println("Exited with error code "+exitVal);

         } catch(Exception e) {
             System.out.println(e.toString());
             e.printStackTrace();
         }
		
	}
	
	private void cancella(String k) {
		MainfastGIT.listaURList.remove(k);
		MainfastGIT.scarica();
	}
	public static boolean setCurrentDirectory(String directory_name)
    {
        boolean result = false;  // Boolean indicating whether directory was set
        File    directory;       // Desired current working directory

        directory = new File(directory_name).getAbsoluteFile();
        if (directory.exists() || directory.mkdirs())
        {
            result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
        }

        return result;
    }
}