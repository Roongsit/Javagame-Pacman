package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.*;

public class Board extends JPanel{
    Graphics g;
    int score;
    boolean gameOver;
    
    ImageIcon titleScreen = new ImageIcon(this.getClass().getResource("titleScreen.jpg"));
    ImageIcon Gameover = new ImageIcon(this.getClass().getResource("Gameover.jpg"));
    ImageIcon Win = new ImageIcon(this.getClass().getResource("Win.jpg"));
    
    ImageIcon red_ghost_right = new ImageIcon(this.getClass().getResource("ghost10.jpg"));
    ImageIcon red_ghost_left = new ImageIcon(this.getClass().getResource("ghost11.jpg"));
    ImageIcon[] G_red = {red_ghost_left,red_ghost_right}; // สลับรูปไปมา
    
    ImageIcon yellow_ghost_right = new ImageIcon(this.getClass().getResource("ghost20.jpg"));
    ImageIcon yellow_ghost_left = new ImageIcon(this.getClass().getResource("ghost21.jpg"));
    ImageIcon[] G_yellow = {yellow_ghost_left,yellow_ghost_right};
    
    ImageIcon blue_ghost_right = new ImageIcon(this.getClass().getResource("ghost30.jpg"));
    ImageIcon blue_ghost_left = new ImageIcon(this.getClass().getResource("ghost31.jpg"));
    ImageIcon[] G_blue = {blue_ghost_left,blue_ghost_right};
    
    ImageIcon pink_ghost_right = new ImageIcon(this.getClass().getResource("ghost40.jpg"));
    ImageIcon pink_ghost_left = new ImageIcon(this.getClass().getResource("ghost41.jpg"));
    ImageIcon[] G_pink = {pink_ghost_left,pink_ghost_right};
    
    //Pacman
    ImageIcon Pacman = new ImageIcon(this.getClass().getResource("pacman.jpg"));
    ImageIcon Pacman_left = new ImageIcon(this.getClass().getResource("pacmanleft.jpg"));
    ImageIcon Pacman_right = new ImageIcon(this.getClass().getResource("pacmanright.jpg"));
    ImageIcon Pacman_up = new ImageIcon(this.getClass().getResource("pacmanup.jpg"));
    ImageIcon Pacman_down = new ImageIcon(this.getClass().getResource("pacmandown.jpg"));
    ImageIcon[] Pacman_images  = {Pacman_left,Pacman_right,Pacman_up,Pacman_down};
    Pacman pacman = new  Pacman(10*Component.cellSize,15*Component.cellSize);
    
    //Ghosts
    Ghost ghost1 = new Ghost(10*Component.cellSize,8*Component.cellSize); // red
    Ghost ghost2 = new Ghost(10*Component.cellSize,9*Component.cellSize);
    Ghost ghost3 = new Ghost(11*Component.cellSize,9*Component.cellSize); // blue
    Ghost ghost4 = new Ghost(9*Component.cellSize,9*Component.cellSize);
            
    boolean title ;
    boolean balls[][];
    boolean states[][];
    int lives = 2;
        
    public Board() {
        title = true;
        balls = new boolean[Component.cellSize][Component.cellSize];
        states = new boolean[Component.cellSize][Component.cellSize];
        init();
    }
    public void init(){
        for (int i = 0; i < Component.cellSize; i++) {
            for (int j = 0; j < Component.cellSize; j++) {
                balls[i][j]=true; //กำหนดค่าก่อนจะเพิ่มลูกบอล
                states[i][j]=true; //กำหนดค่าว่าให้ขยับได้
            }
//            เว้นช่องว่างไว้ spawn ผีกับตัวเหลือง
            balls[10][8]= false;
            balls[10][9]= false;
            balls[11][9]= false;
            balls[9][9]= false;
            balls[10][15]= false;
            
        }
    }
    
    //กำหนดให้โชว์เลือดเป็นรูป Pacman
    public void drawLives(Graphics g){
        for (int i = 0; i < lives; i++) {
            g.drawImage(Pacman_left.getImage(),(Component.cellSize+5) * i+15, Component.max+10, Component.cellSize, Component.cellSize,null);
        }
    }
    //สร้างลูกบอลถ้าเจอตำแหน่งที่บล็อคสีฟ้าอยู่จะลบทิ้ง
    public void update(Graphics g, int x , int y, int width, int height){
        g.fillRect(x, y, width, height);
        for (int i = x/20; i < x/20 + width/20; i++) {
            for (int j = y/20; j < y/20 + height/20; j++) {
                balls[i][j] = false; //กำหนดค่าให้เป็น false 
                states[i-1][j-1] = false; 
            }
        }
    }
    
    //สร้างลูกบอลและกำหนดที่ที่ลูกบอลอยู่
    public void drawBalls(Graphics g){
        g.setColor(Color.yellow); 
        for (int i = 1; i < Component.cellSize; i++) {
            for (int j = 1; j < Component.cellSize; j++) {
                if(balls[i][j]){  // วาดลูกบอลลงไปในตำแหน่ง i,j
                    g.fillOval(i*20+8, j*20+8, 4, 4); //ฟิคให้อยู่ในกรอบ
                    
                }
            }
        }
        
    }
    
    public void reset(){
        if(lives > 0){
            lives--;
            ghost1.x=10*Component.cellSize;
            ghost1.y=8*Component.cellSize;
            
            ghost2.x=10*Component.cellSize;
            ghost2.y=9*Component.cellSize;
            
            ghost3.x=11*Component.cellSize;
            ghost3.y=9*Component.cellSize;
            
            ghost4.x=9*Component.cellSize;
            ghost4.y=9*Component.cellSize;
               
            pacman.x =10*Component.cellSize;
            pacman.y = 15*Component.cellSize;
            
            Game.flag = true;
        }
        
    }

    //วาดรูปตารางที่ให้ตัว Pacman เดิน
    public void drawBoard(Graphics g){
        
        g.setColor(Color.white);
        g.drawRect(19, 19, 382, 382);
        
        //สร้างบล็อคสีฟ้า
        g.setColor(Color.blue);
        update(g,40,40,60,20);
        update(g,120,40,60,20);
        update(g,200,20,20,40);
        update(g,240,40,60,20);
        update(g,320,40,60,20);
        update(g,40,80,60,20);
        update(g,160,80,100,20);
        update(g,200,80,20,60);
        update(g,320,80,60,20);
        update(g,20,120,80,60);
        update(g,320,120,80,60);
        update(g,20,200,80,60);
        update(g,320,200,80,60);
        update(g,160,160,40,20);
        update(g,220,160,40,20);
        update(g,160,180,20,20); 
        update(g,160,200,100,20);
        update(g,240,180,20,20);
        update(g,120,120,60,20);
        update(g,120,80,20,100);
        update(g,280,80,20,100);
        update(g,240,120,60,20);
        update(g,280,200,20,60);
        update(g,120,200,20,60);
        update(g,160,240,100,20);
        update(g,200,260,20,40);
        update(g,120,280,60,20);
        update(g,240,280,60,20);
        update(g,40,280,60,20);
        update(g,80,280,20,60);
        update(g,320,280,60,20);
        update(g,320,280,20,60);
        update(g,360,320,40,20);
        update(g,20,320,40,20); 
        update(g,160,320,100,20);
        update(g,200,320,20,60);
        update(g,40,360,140,20);
        update(g,240,360,140,20);
        update(g,280,320,20,60);
        update(g,120,320,20,60);
        
        repaint();
        
        
    }
    
    @Override
    public void paint(Graphics g){  
        g.setColor(Color.black);
        g.fillRect(0,0,420,500);
        
        drawBoard(g);
        drawBalls(g);
        drawLives(g);
        
        Font f=new Font("Arial",Font.BOLD,20);
        g.setFont(f);
        g.drawString("Score: "+score, Component.max/2+50,Component.max+30);
        //สร้างผีไปไว้ำตำแหน่งที่กำหนด
        g.drawImage(G_red[ghost1.index].getImage(), ghost1.x,ghost1.y, null); 
        g.drawImage(G_yellow[ghost2.index].getImage(), ghost2.x,ghost2.y, null);
        g.drawImage(G_pink[ghost3.index].getImage(), ghost3.x,ghost3.y, null);
        g.drawImage(G_blue[ghost4.index].getImage(), ghost4.x,ghost4.y, null);
        
        g.drawImage(Pacman_images[pacman.index].getImage(), pacman.x,pacman.y,null);
             
        if(title){
            g.drawImage(titleScreen.getImage(), 0, 0, null);
        }
        if(lives ==0){
            g.drawImage(Gameover.getImage(), 0, 0, null);
        }
        if(check()){
            g.drawImage(Win.getImage(), 0, 0, null);
        }
        
    }
    
    public boolean check(){
        for (int i = 1; i < Component.cellSize; i++) {
            for (int j = 1; j < Component.cellSize; j++) {
                if(balls[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
    

