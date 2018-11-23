package GUI;

import Actor.Actor;
import Actor.Bomber;
import Actor.Manager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sound.GameSound;




public class PlayGame extends JPanel implements Runnable,ActionListener{
	public static boolean IS_RUNNING=true;
	private MyContainer mContainer;
	private BitSet traceKey = new BitSet();
	private Manager mManager = new Manager();
	private int count=0;
	private int deadlineBomb=0;
	private int move=0;
	private int timeDead=0;
	private int timeLose=0;
	private int timeNext=0;
	private JButton btn_Menu;
	
	public PlayGame(MyContainer mContainer) {
		this.mContainer = mContainer;
		setBackground(Color.WHITE);
		setLayout(null);
		setFocusable(true);
		addKeyListener(keyAdapter);
		Thread mytheard = new Thread(this);
		mytheard.start();
		innitCompts();
	}
	
	private void innitCompts(){
		btn_Menu = new JButton();
		btn_Menu.setText("Menu");
		btn_Menu.setBounds(750, 520, 100, 30);
		btn_Menu.addActionListener(this);
		add(btn_Menu);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new java.awt.BasicStroke(2));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		mManager.draWBackground(g2d);
		mManager.drawAllItem(g2d);
		mManager.drawAllBomb(g2d);
		mManager.drawAllBox(g2d);
		mManager.drawAllMonster(g2d);
		//mMagager.getmBomber().drawActor(g2d);
                if(mManager.getmBomber().getStatus() == Actor.ALIVE)
                    mManager.getmBomber().drawAnim(g2d);
                else
                    mManager.getmBomber().drawActor(g2d);
		mManager.drawAllShawDow(g2d);
		mManager.drawInfo(g2d);
		mManager.drawBoss(g2d);
		if(mManager.getStatus()==1){
			mManager.drawDialog(g2d, 1);
		}
		if(mManager.getStatus()==2){
			mManager.drawDialog(g2d, 2);
		}
		if(mManager.getStatus()==3){
			mManager.drawDialog(g2d, 3);
		}
	}
	
	private KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			traceKey.set(e.getKeyCode());
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
                        Bomber.move_Down = false;
                        Bomber.move_Up = false;
                        Bomber.move_Right = false;
                        Bomber.move_Left = false;
			traceKey.clear(e.getKeyCode());
		}
	};

	@Override
	public void run() {
		while(IS_RUNNING ){
                   
                        mManager.getmBomber().Update_Anim();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(traceKey.get(KeyEvent.VK_LEFT) || traceKey.get(KeyEvent.VK_A )){
                                Bomber.move_Left = true;
				mManager.getmBomber().changeOrient(Bomber.LEFT);
				mManager.getmBomber().move(count,mManager.getArrBomb(),mManager.getArrBox());
				
			}
			if(traceKey.get(KeyEvent.VK_RIGHT) || traceKey.get(KeyEvent.VK_D )){
                                Bomber.move_Right = true;
				mManager.getmBomber().changeOrient(Bomber.RIGHT);
				mManager.getmBomber().move(count,mManager.getArrBomb(),mManager.getArrBox());
			}
			if(traceKey.get(KeyEvent.VK_UP) || traceKey.get(KeyEvent.VK_W )){
                                Bomber.move_Up = true;
				mManager.getmBomber().changeOrient(Bomber.UP);
				mManager.getmBomber().move(count,mManager.getArrBomb(),mManager.getArrBox());;
			}
			if(traceKey.get(KeyEvent.VK_DOWN) || traceKey.get(KeyEvent.VK_S )){
                                Bomber.move_Down = true;
				mManager.getmBomber().changeOrient(Bomber.DOWN);
				mManager.getmBomber().move(count,mManager.getArrBomb(),mManager.getArrBox());
			}
			if(traceKey.get(KeyEvent.VK_SPACE)){
					mManager.innitBomb();
					mManager.getmBomber().setRunBomb(Bomber.ALLOW_RUN);

			}
			mManager.setRunBomer();
			mManager.deadLineAllBomb();
			mManager.checkDead();
			mManager.checkImpactItem();
			mManager.checkWinAndLose();
			
			if(mManager.getStatus()==1){
				timeLose++;
				if(timeLose == 2000){
					mManager.innitManager();
					mContainer.setShowMenu();
					timeLose=0;
				}
			}
			
			if(mManager.getStatus()==2){
				timeNext++;
				if(timeNext==1000){
					mManager.innitManager();
					timeNext=0;
				}
			}
			
			if(mManager.getStatus()==3){
				timeNext++;
				if(timeNext==1000){
					mManager.innitManager();
					mContainer.setShowMenu();
					timeNext=0;
				}
			}
			
			if(mManager.getmBomber().getStatus()==Bomber.DEAD){
				timeDead++;
				if(timeDead==2000){
					mManager.setNewBomb();
					timeDead=0;
				}
                        }
			
                        // thay Ä‘á»•i hÆ°á»›ng Ä‘i cá»§a monster
			if(move==0){
				mManager.changeOrientAll();
				move=50000;
			}
			if(move>0){
				move--;
			}
			mManager.moveAllMonster(count);
                        //removeAll();
                        //revalidate();
			repaint();
			count++;
			if(count==1000000){
				count=0;
			}
		}
                
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_Menu){
			mManager.setRound();
			mManager.innitManager();
			mContainer.setShowMenu();
		}	
		
	}
}
