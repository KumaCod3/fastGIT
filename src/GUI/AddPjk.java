package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import Logic.MainfastGIT;

public class AddPjk extends Finestra{
	String urlDefin="";
	public AddPjk() {
		super("Add new Project");
		JPanel contenuto=new JPanel();
		contenuto.setBorder(Est.bordo);
		contenuto.setOpaque(false);
		contenuto.setLayout(new GridLayout(2,2));
		
		JPanel nmJPanel=new JPanel();
		nmJPanel.setBorder(Est.bordo);
		nmJPanel.setOpaque(false);
		nmJPanel.setLayout(new FlowLayout());
		Etichetta nm=new Etichetta("Project name: ");
		nmJPanel.add(nm);
		contenuto.add(nmJPanel);
		
		JPanel fmJPanel=new JPanel();
		fmJPanel.setBorder(Est.bordo);
		fmJPanel.setOpaque(false);
		fmJPanel.setLayout(new FlowLayout());
		FormVuoto adName=new FormVuoto("name");
		fmJPanel.add(adName);
		contenuto.add(fmJPanel);
		
		JPanel urJPanel=new JPanel();
		urJPanel.setBorder(Est.bordo);
		urJPanel.setOpaque(false);
		urJPanel.setLayout(new FlowLayout());
		Etichetta ur=new Etichetta("");
		urJPanel.add(ur);
		contenuto.add(urJPanel);
		
		JPanel brJPanel=new JPanel();
		brJPanel.setBorder(Est.bordo);
		brJPanel.setOpaque(false);
		brJPanel.setLayout(new FlowLayout());
		Bottone browse=new Bottone("find");
		browse.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Path uPath = getInputPath();
		    	urlDefin=uPath.toString();
		    	ur.setText(urlDefin);
			}
		});
		brJPanel.add(browse);
		contenuto.add(brJPanel);
		
		JPanel panel_1a = new JPanel();
		
		panel_1a.setOpaque(false);
		
		Bottone bent=new Bottone("ADD");
		bent.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (urlDefin.length()>0 && adName.ret.length()>0) {
		    		MainfastGIT.listaURList.put(adName.ret, urlDefin);
		    		MainfastGIT.scarica();
		    		Home h=new Home();
			    	h.setVisible(true);
					setVisible(false);
					dispose();
		    	}
		    	else {
		    		System.out.println("url: "+urlDefin.length()+" name: "+adName.ret.length());
		    	}
			}
		});
		panel_1a.add(bent);
		Bottone bac=new Bottone("Back");
		bac.but.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    		Home h=new Home();
			    	h.setVisible(true);
					setVisible(false);
					dispose();
			}
		});
		panel_1a.add(bac);
		
		c.add("South", panel_1a);
		c.add("Center", contenuto);
		
		pack();
	}
	
	private Path getInputPath() {
		JFileChooser jd = new JFileChooser();
		jd.setDialogTitle("Choose input dir");
		jd.setAcceptAllFileFilterUsed(false);
		jd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal= jd.showOpenDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION) return null;
        return jd.getSelectedFile().toPath();
   }
}