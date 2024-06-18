package GUI;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Finestra extends Frame {
	public JPanel c;

	public Finestra(String x) {
		super(x);
		addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
                dispose(); 
                System.exit(0);
            }    
        });
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLocation(Est.marginX, Est.marginY);
		setMinimumSize(Est.err);
		setMaximumSize(Est.bigErr);
		setBackground(Est.sfondo);
		Header testa = new Header();
		add(testa);
		c = new JPanel();
		c.setLayout(new BorderLayout(100, 10));
		c.setBorder(Est.borCol);
		c.setOpaque(false);
		add(c);

	}
}
