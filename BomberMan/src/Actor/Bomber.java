package Actor;

import Effect.AFrameOnImage;
import Effect.Animation;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;




public class Bomber extends Actor{
	public static int ALLOW_RUN=0;
	public static int DISALLOW_RUN=1;
        public static Animation anim_Right;
        public static Animation anim_Left;
        public static Animation anim_Down;
        public static Animation anim_Up;
        private BufferedImage image;
        public static boolean move_Left = false;
        public static boolean move_Right = false;
        public static boolean move_Up = false;
        public static boolean move_Down = false;
	protected int sizeBomb,quantityBomb,status,score,heart; // quantity : số lượng
	
	
	
	public Bomber(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb) {
		this.x = x ;
		this.y = y;
		this.type = type;
		this.runBomb=DISALLOW_RUN;
		this.orient = orient;
		this.speed = speed;
		this.sizeBomb = sizebomb;
		this.quantityBomb = quantityBomb;
		this.heart = 3;
		this.score=0;
		this.status = Actor.ALIVE;
            
		this.img = new ImageIcon(getClass().getResource("/Images/down3.png")).getImage();
		width = img.getWidth(null);
		height = 45;
                initAnimation();
              
	}
        public void initAnimation(){
            try {
                image = ImageIO.read(new File("src/Images/Bomer_Sprites.png"));
            } catch (IOException ex) {
                Logger.getLogger(Bomber.class.getName()).log(Level.SEVERE, null, ex);
            }
            int w = 48;
            int h = 68;
            AFrameOnImage f;
        
        
            anim_Down = new Animation(100);
            f = new AFrameOnImage(1, 1, w, h);
            anim_Down.AddFrame(f);
            f = new AFrameOnImage(51, 1, w, h);
            anim_Down.AddFrame(f);
            f = new AFrameOnImage(101, 1, w, h);
            anim_Down.AddFrame(f);
            f = new AFrameOnImage(151, 1, w, h);
            anim_Down.AddFrame(f);
            f = new AFrameOnImage(101, 1, w, h);
            anim_Down.AddFrame(f);
            f = new AFrameOnImage(51, 1, w, h);
            anim_Down.AddFrame(f);
        
            anim_Left = new Animation(100);
            f = new AFrameOnImage(1, 71, w, h);
            anim_Left.AddFrame(f);
            f = new AFrameOnImage(51, 71, w, h);
            anim_Left.AddFrame(f);
            f = new AFrameOnImage(101, 71, w, h);
            anim_Left.AddFrame(f);
            f = new AFrameOnImage(151, 71, w, h);
            anim_Left.AddFrame(f);
            f = new AFrameOnImage(101, 71, w, h);
            anim_Left.AddFrame(f);
            f = new AFrameOnImage(51, 71, w, h);
            anim_Left.AddFrame(f);
        
            anim_Right = new Animation(100);
            f = new AFrameOnImage(1, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(51, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(101, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(151, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(101, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(51, 141, w, h);
            anim_Right.AddFrame(f);
        
            anim_Right = new Animation(100);
            f = new AFrameOnImage(1, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(51, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(101, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(151, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(101, 141, w, h);
            anim_Right.AddFrame(f);
            f = new AFrameOnImage(51, 141, w, h);
            anim_Right.AddFrame(f);
        
            anim_Up = new Animation(100);
            f = new AFrameOnImage(1, 211, w, h);
            anim_Up.AddFrame(f);
            f = new AFrameOnImage(51, 211, w, h);
            anim_Up.AddFrame(f);
            f = new AFrameOnImage(101, 211, w, h);
            anim_Up.AddFrame(f);
            f = new AFrameOnImage(151, 211, w, h);
            anim_Up.AddFrame(f);

        }
        public void Update_Anim(){
            anim_Down.Update_Me(System.currentTimeMillis());
            anim_Up.Update_Me(System.currentTimeMillis());
            anim_Left.Update_Me(System.currentTimeMillis());
            anim_Right.Update_Me(System.currentTimeMillis());
        }
        
        
        public void drawAnim(Graphics2D g2){
            if(move_Left){
                anim_Left.PaintAnims(x-2, y-30, image, g2);
            }else{
                if(move_Right){
                    anim_Right.PaintAnims(this.x-2, this.y-30, image, g2);
                }else{
                    if(move_Up){
                        anim_Up.PaintAnims(this.x-3, this.y-30, image, g2);
                    }else{
                        if(move_Down){
                            anim_Down.PaintAnims(this.x-2, this.y-30, image, g2);
                        }else{
                            g2.drawImage(img, x -2 , y-30, null);
                        }
                    }
                }
            }
            
        }

	public void setNew(int x,int y) {
		this.x = x;
		this.y = y;
		this.status = ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/down3.png")).getImage();
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getQuantityBomb() {
		return quantityBomb;
	}

	public void setQuantityBomb(int quantityBomb) {
		if(quantityBomb>5){
			return;
		}
		this.quantityBomb = quantityBomb;
	}


	public void setSizeBomb(int sizeBomb) {
		if(sizeBomb>3){
			return;
		}
		this.sizeBomb = sizeBomb;
	}

	public int getSizeBomb() {
		return sizeBomb;
	}

	public int getType() {
		return type;
	}
	
	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	@Override
	public boolean move(int count, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox) {
		if(status==DEAD){
			return false;
		}
		return super.move(count, arrBomb, arrBox);
	}
	
	@Override
	public void changeOrient(int orient) {
		if(this.status==DEAD){
			return;
		}
		super.changeOrient(orient);
		switch (orient) {
		case LEFT:
			img = new ImageIcon(getClass().getResource("/Images/left.png")).getImage();
			break;
		case RIGHT:
			img = new ImageIcon(getClass().getResource("/Images/right.png")).getImage();
			break;
		case UP:
			img = new ImageIcon(getClass().getResource("/Images/up2.png")).getImage();
			break;
		case DOWN:
			img = new ImageIcon(getClass().getResource("/Images/down3.png")).getImage();
			break;
		default:
			break;
		}
	}

	public boolean isImpactBomberVsActor(Actor actor){
		if(status==DEAD){
			return false;
		}
		Rectangle rec1 = new Rectangle(x - 2, y , width - 5, width);
		Rectangle rec2 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
		return rec1.intersects(rec2);
	}
	
}
