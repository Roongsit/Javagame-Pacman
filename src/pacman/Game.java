package pacman;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game implements KeyListener{
    Board board = new Board();
    private Timer timer_pacman,timer_ghost1,timer_ghost2,timer_ghost3,timer_ghost4;
    private char direction = 'L';
    static boolean flag = true;
    JFrame frame;
    public Game() {
        
        frame = new JFrame();
        frame.setSize(440,500); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(board,BorderLayout.CENTER);
        frame.setTitle("Pac-Man");
        frame.setVisible(true);
        frame.addKeyListener(this);
        
        timer_pacman = new Timer(40,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!board.title && board.lives > 0){
                    if(flag){
                        try {
                            Thread.sleep(2000); // delay ก่อนเริ่มเกม
                            flag=false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                board.pacman.move(direction);
                //บอกว่ากินจุดแล้วได้แต้ม
                if(board.balls[board.pacman.x/20][board.pacman.y/20]){
                    board.score++;
                }
                //แพ็คแมนโดนบอลแล้วจุดหาย
                board.balls[board.pacman.x/20][board.pacman.y/20]=false;
                board.pacman.updateState(board.states); // ทำให้กดได้
                }
            }
        });
        timer_ghost1 = new Timer(30,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!board.title && board.lives > 0){
                    if(flag){
                        try {
                            Thread.sleep(2000); // delay ก่อนเริ่มเกม
                            flag=false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                board.ghost1.move();
                if(board.ghost1.getShape().intersects(board.pacman.getShape())){
                    board.reset();
                }
                board.ghost1.updateState(board.states);
                }
            }
        });
        timer_ghost2 = new Timer(40,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!board.title && board.lives > 0){
                    if(flag){
                        try {
                            Thread.sleep(2000); // delay ก่อนเริ่มเกม
                            flag=false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                board.ghost2.move();
                if(board.ghost2.getShape().intersects(board.pacman.getShape())){
                    board.reset();
                }
                board.ghost2.updateState(board.states);
            }
            }
        });
        timer_ghost3 = new Timer(35,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!board.title && board.lives > 0){
                    if(flag){
                        try {
                            Thread.sleep(2000); // delay ก่อนเริ่มเกม
                            flag=false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                board.ghost3.move();
                if(board.ghost3.getShape().intersects(board.pacman.getShape())){
                    board.reset();

                }
                board.ghost3.updateState(board.states);
            }
            }
        });
        timer_ghost4 = new Timer(40,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!board.title && board.lives > 0){
                    if(flag){
                        try {
                            Thread.sleep(2000); // delay ก่อนเริ่มเกม
                            flag=false;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                board.ghost4.move();
                if(board.ghost4.getShape().intersects(board.pacman.getShape())){
                    board.reset();
                }
                board.ghost4.updateState(board.states);
            }
            }
        });
        timer_pacman.start();
        timer_ghost1.start();
        timer_ghost2.start();
        timer_ghost3.start();
        timer_ghost4.start();
        
    }

    public static void main(String[] args) {
        Game g = new Game();
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                direction = 'L';
                break;
            case KeyEvent.VK_RIGHT:
                direction = 'R';
                break;
            case KeyEvent.VK_DOWN:
                direction = 'D';
                break;
            case KeyEvent.VK_UP:
                direction = 'U';
                break;
            case KeyEvent.VK_ENTER:
                board.title = false;
                break;
            case KeyEvent.VK_R:
//                board.restart();
                frame.dispose();
                new Game();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        
    }
    
}
