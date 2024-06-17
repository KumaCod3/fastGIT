package GUI;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class NewButt extends JButton{
	// generali
	private Color col1=Est.scuro;
	private Color col2=Est.verde;
	private Color scri=Color.WHITE;
	
	//per OVER
	private final Timer timer;
	private float alpha=0.3f;
	private boolean mouseOver;
	
	// per PRESS
	private final Timer timerPress;
	private float alphaPress=0.3f;
	private boolean press;
	private float pressSize;
	private Point pressLoc;
	
	// rimpicciolisci per rallentare macchia click
	private float sizeSpeed=35.0f;
	
	public NewButt(String a) {
		super(a);
		
		setContentAreaFilled(false);
		setForeground(scri);
		setFont(Est.boldFont);
		
		// metto manina
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		// metto bordo
		setBorder(new EmptyBorder(10,20,10,20));
		
		// faccio il lampeggio sull OVER
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				pressSize=0;
				alphaPress=0.5f;
				press=true;
				pressLoc=me.getPoint();
				timerPress.setDelay(0);
				timerPress.start();
			}
		});
		
		timer=new Timer(50, new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if (mouseOver) {
					if (alpha<0.6f) {
						alpha=alpha+0.5f;
						repaint();
					}
					else {
						alpha=0.6f;
						timer.stop();
						repaint();
					}
				} else {
					if (alpha>0.3f) {
						alpha=alpha-0.5f;
						repaint();
					}
					else {
						alpha=0.3f;
						timer.stop();
						repaint();
					}
				}
			}
		});
		
		timerPress=new Timer(0, new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				pressSize=pressSize+sizeSpeed;
				if (alphaPress<=0) {
					press=false;
					timerPress.stop();
				}
				else {
					repaint();
				}
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		int wi=getWidth();
		int he=getHeight();
		BufferedImage img=new BufferedImage(wi,he, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2=img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gra=new GradientPaint(0,0,col1,wi,0,col2);
		g2.setPaint(gra);
		// I DUE 5 SERVONO PER ARROTONDARE GLI SPIGOLI
		g2.fillRoundRect(0,0,wi,he,80,80);
		if (press) {
			paintPress(g2);
		}
		g.drawImage(img,0,0,null);
		super.paintComponent(g);
	}
	
	//Make graph for pressed
	private void paintPress(Graphics2D g2) {
		if (pressLoc.x-(pressSize/2)<0 && pressLoc.x+(pressSize/2)>getWidth()) {
			timerPress.setDelay(20);
			alphaPress=alphaPress-0.05f;
			if (alphaPress<0) {
				alphaPress=0;
			}
		}
		g2.setColor(scri);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alphaPress));
		float x=pressLoc.x-(pressSize/2);
		float y=pressLoc.y-(pressSize/2);
		g2.fillOval((int)x, (int)y, (int)pressSize, (int)pressSize);
	}
}
