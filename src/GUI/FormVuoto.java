package GUI;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class FormVuoto extends JTextArea{
	public String ret=" ";
	String tt="";
	
	public FormVuoto(String tit){
		super(tit, 1, 15);
		ret=tit;
		tt=tit;
		setBorder(BorderFactory.createLineBorder(Est.sfondo, 15));
		getDocument().putProperty("filterNewlines", Boolean.TRUE);
		setForeground(Est.verde);
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				if (getText().equals(tit)){
					setText("");
					setForeground(Color.BLACK);
				}
			}
			public void focusLost(FocusEvent e){
				if (getText().isEmpty()){
					setText(tit);
					setForeground(Est.verde);
				}
				else ret=getText();
			}
		});
		setFont(Est.plainFont);

		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
		
	}
	
	public void clear(){
		setText(tt);
		setForeground(Est.verde);
	}
	
	public void setUnchain() {
		for (FocusListener f:getFocusListeners()) {
			removeFocusListener(f);
		}
	}

	public void setTX(int rataVal) {
		setText(rataVal+"");
	}
	
	public void enterr(Bottone b) {
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					b.requestFocusInWindow();
					b.but.doClick();
				}
			}
		});
	}
	
	public String retData() {
//		System.out.println("inizio");
		String s="";
		String err="";
		String[] datStrings=ret.split("/");
		if (datStrings.length!=3) {
			return err;
		}
		// giorno
		if (datStrings[0].length()==0||datStrings[0].length()>2) {
			return err;
		}
		if (datStrings[0].length()==1) {
//			System.out.println("day ok");
			s=s+"0"+datStrings[0]+"/";
		}
		if (datStrings[0].length()==2) {
//			System.out.println("day ok");
			s=s+datStrings[0]+"/";
		}
		// mese
		if (datStrings[1].length()==0||datStrings[0].length()>2) {
//			System.out.println("err mon");
			return err;
		}
		if (datStrings[1].length()==1) {
//			System.out.println("mon ok");
			s=s+"0"+datStrings[1]+"/";
		}
		if (datStrings[1].length()==2) {
//			System.out.println("mon ok");
			s=s+datStrings[1]+"/";
		}
		// anno
		if (datStrings[2].length()==0||datStrings[0].length()>4) {
//			System.out.println("err ann");
			return err;
		}
		if (datStrings[2].length()==1) { 
//			System.out.println("ann ok");
			s=s+"200"+datStrings[2];
		}
		if (datStrings[2].length()==2) {
			if (Integer.parseInt(datStrings[2])<30) {
//				System.out.println("ann ok");
				s=s+"20"+datStrings[2];
			}
			else {
//				System.out.println("ann ok");
				s=s+"19"+datStrings[2];
			}
		}
		if (datStrings[2].length()==3) {
//			System.out.println("ann ok");
			s=s+"2"+datStrings[2];
		}
		if (datStrings[2].length()==4) {
//			System.out.println("ann ok");
			s=s+datStrings[2];
		}
//		System.out.println("data "+s);
		return s;
	}
}
