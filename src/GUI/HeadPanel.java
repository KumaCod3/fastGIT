package GUI;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class HeadPanel extends JPanel{
	HeadPanel(){
		super();
		setPreferredSize(new Dimension(40, 40));
		setOpaque(true);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Toolkit tk=Toolkit.getDefaultToolkit();
		Image imgg=tk.getImage("logPN.png");
		Image img = imgg.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH ) ;
		
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img,1);
		try{
			mt.waitForID(1);
		}
		catch (Exception ex){
		}
		g.drawImage(img,0,0,null);
		
	}
}
