package GUI;
import java.awt.Color;

import javax.swing.JLabel;

public class Etichetta extends JLabel{
	public Etichetta(String x){
		setText(x);
		setForeground(Color.WHITE);
		setBackground(Est.scuro);
		setFont(Est.plainFont);
		setOpaque(false);
		setBorder(Est.bordo);
		
	}
}
