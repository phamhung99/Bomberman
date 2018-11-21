package Actor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bomb extends Actor{

	protected int size, timeline;
	
	public Bomb(int x, int y, int size, int timeline){
		x=(x/45)*45;
		y=(y/45)*45;
		this.x=x;
		this.y=y;
		this.size=size;
		this.orient=0;
		this.timeline = timeline;
		this.type =Actor.BOMB;
		img= new ImageIcon(getClass().getResource("/Images/bomb.png")).getImage();
		this.width= img.getWidth(null);
		this.height= img.getHeight(null);
	}
	

	public void deadlineBomb(){
		this.timeline--;
	}

	public int getTimeline() {
		return timeline;
	}

	public int getSize() {
		return size;
	}
	
	public boolean setRun(Bomber bomber){
		Rectangle rec1 = new Rectangle(x, y, 45, 45);
		Rectangle rec2 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight());
		return rec1.intersects(rec2);
	}
	
	public void setTimeline(int timeline) {
		this.timeline = timeline;
	}


	public boolean isImpact(int xNewBomb, int yNewBomb){
		Rectangle rec1 = new Rectangle(x, y, 45, 45);
		Rectangle rec2 = new Rectangle(xNewBomb, yNewBomb, 45, 45);
		return rec1.intersects(rec2);
	}
	
	
	public int isImpactBombvsActor(Actor actor){
		if(actor.getRunBomb()==Bomber.ALLOW_RUN) {
			return 0;
		}
		Rectangle rec1 = new Rectangle(x + 15, y+10, 20, 25);
		Rectangle rec2 = new Rectangle(actor.getX() , actor.getY(), actor.getWidth() , actor.getWidth());
		if(rec1.intersects(rec2)){
			if(actor.getType()==Actor.BOSS){
				return 2;
			}
			return 1;
		};
		return 0;
	}
}
