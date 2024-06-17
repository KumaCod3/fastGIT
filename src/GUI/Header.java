package GUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {
	public Header(){
		super();
		setLayout(new FlowLayout());
		setMinimumSize(Est.testa);
		setBorder(Est.borColHe);
		
		HeadPanel pan=new HeadPanel();
		pan.setOpaque(false);
		add(pan);
		JLabel tz=new JLabel();
		tz.setText("Istituto Formativo Europeo ");
		tz.setForeground(Color.WHITE);
		tz.setOpaque(false);
		add(tz);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int wi = getWidth(), he = getHeight();
        GradientPaint gp = new GradientPaint(0,0,Est.scuro,0,he,Est.sfondo);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, wi, he);
		
	}
	
}