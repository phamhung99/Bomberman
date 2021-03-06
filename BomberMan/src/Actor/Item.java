package Actor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Item {
	public static int Item_Bomb=1;
	public static int Item_BombSize=2;
	public static int Item_Shoe=3;
        public static int Item_nextRound = 4;
        public static int Item_Heart = 5;
	private int x, y, type, width, height, timeLine;
	private Image img;
	
	public Item(int x, int y, int type, String image) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.img = new ImageIcon(getClass().getResource(image)).getImage();
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		timeLine=251;
	}
	
	public void drawItem(Graphics2D g2d){
		g2d.drawImage(img, x, y, null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
		
	public int getTimeLine() {
		return timeLine;
	}
	
	public void setTimeLine(int timeLine) {
		this.timeLine = timeLine;
	}

	public boolean isImpactItemVsBomber(Bomber bomber){
		Rectangle rec1 = new Rectangle(x+10, y+10, 20, 20);
		Rectangle rec2 = new Rectangle(bomber.getX()+ 5, bomber.getY()+ 5, bomber.getWidth() -10, bomber.getWidth() -10);
		return rec1.intersects(rec2);
	}

}
