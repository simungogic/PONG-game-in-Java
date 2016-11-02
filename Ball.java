import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball implements Runnable {

	int x,y,xDirection,yDirection;
	
	Rectangle ball;
	
	int p1S, p2S;
	
	Paddle p1 = new Paddle(0, 140, 1);
	Paddle p2 = new Paddle(375, 140, 2);
	
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		p1S = 0;
		p2S = 0;
		
		// random kretanje loptice
		Random r = new Random();
		int rDir = r.nextInt(2); // kreira random broj, 0 ili 1
		
		if(rDir == 0) // ako je generirana 0, rDir će biti negativan
			rDir--;
		
		setXDirection(rDir); // smjer loptice na x-osi
		
		int yrDir = r.nextInt(2); 
		
		if(yrDir == 0) 
			yrDir--;
		
		setYDirection(yrDir); // smjer loptice na y-osi
		
		// kreiramo lopticu
		ball = new Rectangle(this.x, this.y, 10, 10); // loptica je zapravo pravokutnik
		// 15 i 15 su širina i visina, a prva dva parametra položaj u KS-u
	}
	
	public void setXDirection(int xdir)
	{
		xDirection = xdir;
	}
	
	public void setYDirection(int ydir)
	{
		yDirection = ydir;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
	}
	
	public void move()
	{
		collision();
		ball.x += xDirection;
		ball.y += yDirection;

		// odbijanje loptice kada je kraj detektiran
		if(ball.x <= 0)
		{
			setXDirection(+1);
			p2S++;
		}		
		else if(ball.x >= 375)
		{
			setXDirection(-1);
			p1S++;
		}
		
		if(ball.y <= 20)
		{
			setYDirection(+1);
		}
		else if(ball.y >= 270)
		{
			setYDirection(-1);
		}
			
	}
	
	public void collision()
	{
		if(ball.intersects(p1.paddle))
		{
			setXDirection(1);
		}
		else if(ball.intersects(p2.paddle))
		{
			setXDirection(-1);
		}
	}
	
	@Override
	public void run() {
		
		try
		{
			while(true)
			{
				move();
				Thread.sleep(4);
			}
		}catch(Exception e){System.out.println(e.getMessage());}
	}

}
