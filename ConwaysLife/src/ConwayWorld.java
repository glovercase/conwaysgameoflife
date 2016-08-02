import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
//import java.util.Timer;
//import java.util.TimerTask;


import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;



@SuppressWarnings("serial")
public class ConwayWorld extends JComponent{
	
	public static final int GRID_SIZE = 20;
	private static final int ANIMATION_DELAY = 20;
	private static final int LIFE_UPDATE_DELAY = 33;
	
	public static Life[][] cells;
	public static Life[][] copy;
	private Timer timer;
	private int frame;
	private int ROWS = Life.SIZE, COLS = Life.SIZE;
	boolean start;
	
	public ConwayWorld(){
		super();
		buildLife();
		this.setPreferredSize(new Dimension(600, 600));
	}
	
	public void buildLife(){
		cells = new Life[ROWS][COLS];
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				cells[i][j] = new Life(i,j, (Math.random() < 0.1));
			}
		}
	}
	
	public void start() {
		timer = new Timer();
		timer.schedule(new AnimationTask(), 0,ANIMATION_DELAY);
		start = true;	
		updateWorld();
	    
	}
	
	public void updateWorld(){
		copy = new Life[ROWS][COLS] ;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				copy[i][j] = cells[i][j];
			}
		}
			for(int i = 0; i < ROWS; i++){
				for(int j = 0; j < COLS; j++){
		
					checkRules(cells[i][j], copy[i][j]);
				}
			}
			for(int i = 0; i < ROWS; i++){
				for(int j = 0; j < COLS; j++){
					cells[i][j] = copy[i][j];
				}
			}
			this.repaint();	
	}
	
	public void reset() {
		if(timer != null)
		    timer.cancel();
		start = false;
		buildLife();	
	}
	
	public void checkRules(Life cell, Life cell2){
    	//Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    	int liveNeighbours = checkNeighbours(cell);
    	//System.out.println(liveNeighbours);
    	if(cell.isAlive()){
    		if(liveNeighbours < 2 || liveNeighbours > 3){
    			//Any live cell with more than three live neighbors dies, as if by over-population.
    			copy[cell.getRow()][cell.getCol()].setAlive(false);	
    		}
    	}else if(liveNeighbours == 3){
    		//Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    			copy[cell.getRow()][cell.getCol()].setAlive(true);
    	}
    	
    	
    	
    }
 
    private int checkNeighbours(Life cell) {
    	int neighbours = 0;
    	int cellXLeft;
    	int cellXRight;
    	int cellYTOP;
    	int cellYBOT;
    	if(cell.getRow()-1 >= 0){
    	 cellXLeft = cell.getRow()-1; //left cell
    	}else{
    	 cellXLeft = cell.getRow();
    	}
    	if(cell.getRow()+1 < Life.SIZE){
    	 cellXRight = cell.getRow()+1; //right cell
    	}else{
    	 cellXRight = cell.getRow();
    	}
    	if(cell.getCol()-1 >= 0){
    	 cellYTOP = cell.getCol()-1; //top cell
    	}else{
    	 cellYTOP = cell.getCol();
    	}
    	if(cell.getCol()+1 < Life.SIZE){
    	 cellYBOT = cell.getCol()+1; //bottom cell
    	}else{
    	 cellYBOT = cell.getCol();
    	}
    	
    	for(int x = cellXLeft; x <= cellXRight; x++){
    	    for(int y = cellYTOP; y <= cellYBOT; y++){
    	    	if(cells[x][y].isAlive() && !(x == cell.getRow() && y == cell.getCol())){
   					neighbours++;
    			}
    		}
    	}
    	//System.out.println(neighbours);
		return neighbours;
	}
	
   

	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));
		
		for(int i=0; i<=Life.SIZE; i++){
		    Line2D hline = new Line2D.Double(0, i*GRID_SIZE, 600, i*GRID_SIZE);
		    Line2D vline = new Line2D.Double(i*GRID_SIZE, 0, i*GRID_SIZE, 600);
		    g2d.draw(hline);
		    g2d.draw(vline);
		}
		
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				if(cells[i][j].isAlive()){
					cells[i][j].draw(g2d);
				}
			}
		}
		
		
	}
	
	
	
	//private double getTimeRatio(){
	//	int base = frame/LIFE_UPDATE_DELAY;
	//	return (double)frame/LIFE_UPDATE_DELAY - base;
	// }
	
	private class AnimationTask extends TimerTask{

		public AnimationTask(){
		    frame = 0;
		}
		
		@Override
		public void run() {
			if(!start){
				timer.cancel();
				timer = null;
				return;
			}
			 //logic tick
		    frame++;
		    if(frame % LIFE_UPDATE_DELAY == 0)
			updateWorld();
		    repaint();
		}

	
	}
	

}
