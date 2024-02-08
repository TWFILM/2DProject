
package main;

import entity.Player;
import tile.TileManager;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GamePanel extends JPanel implements Runnable {
    // screen settings
    final int originalTileSize = 16; // 16 x 16
    final int seale = 3; 
    
    public final int tileSize = originalTileSize * seale; // 46 x 46
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
//    WORLD SETTINGS
    public final int maxWorldCol = 50, maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
//  FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    public Player player = new Player(this,keyH);
    
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
        
    }

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; // 0.1666667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while (gameThread != null) {
//          System.out.println("The game loop is running");

            update();
              
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException ex) {
                
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void update() {
        player.update();
       
    }
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2);
        
        player.draw(g2);
        
        g2.dispose();
    }
}
