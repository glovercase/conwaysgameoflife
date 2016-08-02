import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
//import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ConwayLife implements ActionListener {
	
	public static ConwayLife conwayLife;
	
	public final int WIDTH = 600, HEIGHT = 500, PADDING = 20;
	
	public Renderer renderer;
	
	public Life[][] life;
	
	//private JButton buttonStart, buttonReset;
	
	public ConwayLife(){
		
		JFrame jFrame = new JFrame();
		renderer = new Renderer();
		jFrame.add(renderer);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(WIDTH, HEIGHT);
		jFrame.setResizable(true);
		jFrame.setVisible(true);
		jFrame.setTitle("Conways Game of Life");
		
		
		
		
	}


	public void drawGrid() {
		// TODO Auto-generated method stub
		  int ROWS = 30;
	        int COLS = 30;
	        life = new Life[ROWS][COLS];
	        for(int i = 0; i < ROWS; i++) {
	            for(int j = 0; j < COLS; j++) {
	                life[i][j] = new Life(i, j);
	            }
	        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		renderer.repaint();
		
	}

	public void repaint(Graphics g) {
		// TODO Auto-generated method stub
		
		int panelWidth = renderer.getWidth();
        int panelHeight = renderer.getHeight();
        double xPos = (double)(panelWidth - 2*PADDING)/life[0].length;
        double yPos = (double)(panelHeight - 2*PADDING)/life.length;
		g.setColor(Color.BLACK);
		for(int i = 0; i < life[0].length; i++){
			int x = (int) (PADDING + i*xPos);
			g.drawLine(x, PADDING, x, panelHeight-PADDING);
		}
		
	}
	
	class Life {
		 private final int row;
		    private final int col;
		    private boolean isAlive = false;
		 
		    public Life(int r, int c) {
		        row = r;
		        col = c;
		    }
		 
		    public void setAlive(boolean alive) {
		        this.isAlive = alive;
		    }
		 
		    public boolean isAlive() {
		        return isAlive;
		    }
		    
		    public int getRow(){
		    	return row;
		    }
		    
		    public int getCol(){
		    	return col;
		    }
		
	}
	
	public static void main(String[] args){
		
		conwayLife = new ConwayLife();
	}

}
