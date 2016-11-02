import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends JFrame{
	
	// double buffering
	Image dbImage;
	Graphics dbg;
	
	static Ball b = new Ball(193, 143);
	
	static Dimension dimension = new Dimension(385, 285);
	
	public Main() {
		
        setSize(dimension);
        setResizable(false);
        setTitle("Pong");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
 
        addKeyListener(new AC());
    }
 
    public class AC extends KeyAdapter {
 
        public void keyPressed(KeyEvent e) {
        	b.p1.keyPressed(e);
        	b.p2.keyPressed(e);
        }
 
        public void keyReleased(KeyEvent e) {
        	b.p1.keyReleased(e);
        	b.p2.keyReleased(e);
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
    	dbImage = this.createImage(this.getWidth(), this.getHeight());
    	dbg = dbImage.getGraphics();
    	paintComponent(dbg);
    	g.drawImage(dbImage, 0, 0, null);
    }

	private void paintComponent(Graphics g) {
		
		b.draw(g);
		b.p1.draw(g);
		b.p2.draw(g);
		
		g.setColor(Color.red);
		g.drawString("Score: " + b.p1S, 310, 270);
		g.drawString("Score: " + b.p2S, 15, 270);
		repaint();
	}

	public static void main(String[] args) {
		
		Main core = new Main();
		
		// kreiranje i startanje niti(thread)
		
		Thread ball = new Thread(b);
		Thread p1 = new Thread(b.p1);
		Thread p2 = new Thread(b.p2);
		
		ball.start();
		p1.start();
		p2.start();
	}

}
