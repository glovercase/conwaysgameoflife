import java.awt.Color;
import java.awt.Graphics2D;



public class Life {
	
	public static final int SIZE = 50;
	
	private boolean isAlive;
    private int row, col, X, Y;
	
	
	public Life(int x, int y, boolean b){
		this.row = x;
		this.col = y;
		isAlive = b;
	}
	
	  public boolean isAlive() {
	        return isAlive;
	    }
	  
	  public void setAlive(boolean b) {
			isAlive = b;
			
		}
	    
	    public int getRow(){
	    	return row;
	    }
	    
	    public int getCol(){
	    	return col;
	    }

	
	/**
     * Called by the GUI only.
     * @param g2d
     */
    public void draw(Graphics2D g2d){
	
	X = (int) (row * ConwayWorld.GRID_SIZE + ConwayWorld.GRID_SIZE/2d)-9;
	Y = (int) (col * ConwayWorld.GRID_SIZE + ConwayWorld.GRID_SIZE/2d)-9;
	g2d.setColor(Color.RED);
	g2d.fillRect(X, Y, 19, 19);
    }

	public void repaint() {
		// TODO Auto-generated method stub
		
	}

	

}
