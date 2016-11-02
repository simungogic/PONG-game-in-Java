import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable {

	int x, y, yDirection, id;
	
	Rectangle paddle;

	public Paddle(int x, int y, int id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		
		paddle = new Rectangle(x, y, 10, 50);
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		
		switch(id)
		{
			case 1:
				if(keyCode == e.VK_W)
				{
					setYDirection(-1);
				}
				else if(keyCode == e.VK_S)
				{
					setYDirection(1);
				}
			break;
			case 2:
				if(keyCode == e.VK_UP) 
				{
					setYDirection(-1);
				}
				else if(keyCode == e.VK_DOWN)
				{
					setYDirection(1);
				}
			break;
			default:System.out.println("Igrač ne postoji!");
			break;
		}
		
	}
	
	public void setYDirection(int ydir)
	{
		yDirection = ydir;
	}
	
	public void move()
	{
		paddle.y += yDirection;
		
		if(paddle.y <= 20)
		{
			paddle.y = 20;
		}
		else if(paddle.y >= 270)
		{
			paddle.y = 270;
		}
	}
	
	public void draw(Graphics g)
	{
		switch(id)
		{
		case 1:
			g.setColor(Color.RED);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
			break;
		case 2:
			g.setColor(Color.YELLOW);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
			break;
		default: System.out.println("Igrač ne postoji!");
		break;
		}
	}
	
	public void run() {
		try
		{
			while(true)
			{
				move();
				Thread.sleep(2);
			}
			
		}catch(Exception e){System.out.println(e.getMessage());}

	}

	public void keyReleased(KeyEvent e) {
	
		int keyCode = e.getKeyCode();
		
		switch(id)
		{
			case 1:
				if(keyCode == e.VK_W)
				{
					setYDirection(0);
				}
				else if(keyCode == e.VK_S)
				{
					setYDirection(0);
				}
			break;
			case 2:
				if(keyCode == e.VK_UP) 
				{
					setYDirection(0);
				}
				else if(keyCode == e.VK_DOWN)
				{
					setYDirection(0);
				}
			break;
			default:System.out.println("Igrač ne postoji!");
			break;
		
	}

	}
}
