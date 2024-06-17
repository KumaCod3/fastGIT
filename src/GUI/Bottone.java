package GUI;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Bottone extends JPanel{
	public NewButt but;
	
	public Bottone(String x){
		super();
		but=new NewButt(x);
		setBorder(Est.bordo);
		setLayout(new GridBagLayout());
		setBorder(Est.bordo);
		setBackground(Est.sfondo);

		Dimension dd=new Dimension(160, 60);
		but.setMinimumSize(dd);
		but.setMaximumSize(dd);
		but.setPreferredSize(dd);
		
		add(but);
	}
	
	public Bottone(String x,int y){
		super();
		but=new NewButt(x);
		setBorder(Est.bordo);
		setLayout(new GridBagLayout());
		setBorder(Est.bordo);
		setOpaque(false);

		Dimension dd=new Dimension(260, 80);
		but.setMinimumSize(dd);
		but.setMaximumSize(dd);
		but.setPreferredSize(dd);
		
		add(but);
	}
}
