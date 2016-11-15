import java.awt.Color;
import java.awt.Color.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
/*
 * Creates grid of tokens and places token into slots and draws these changes
 *  @author Dylan Lewis
 */


public class C4Grid {
	
	public boolean isChanged;
	
	private boolean [][] slotFilled;
	
	private final int SIDE = 7; 
		
	private double tokenRadius;
	
	private double XAxis;
	
	private double YAxis;
	
	private Token[][] tokens;
	
	private double sideLength;
	
	public C4Grid(int canvasXSize, int canvasYSize){
		this.XAxis = canvasXSize ;
		this.YAxis = canvasYSize ;
		sideLength = XAxis * YAxis;
		//divides the x and y axis into 7 segments each
		sideLength = sideLength/49;
		sideLength = Math.sqrt(sideLength);
		
		/* halves side and then puts the radius at 9/10 of the side length
		 * It does this to create a nice looking gap between the circles
		 */
		tokenRadius = ((sideLength/2)/10)* 9; 
		slotFilled = new boolean[SIDE][SIDE];
		initialiseTokens();
		isChanged = true; 
		
	}
	
	public Token showTokenAt(int column, int row){
		return tokens[column][row];
	}
	
	
	
	public double showTokenRadius(){
		
		return tokenRadius;
			
	}
	
	void changeToken(Token playerToken){
		//6 is the bottom of the game grid
		int row = 6;
		boolean tokenEntered = false;
		try{
			while(row >=0 && !tokenEntered){
				//checks if slot is filled 
				if(!slotFilled[playerToken.getColumn()][row]){
					//checks if it is on the bottom row or if the slot below it is filled
					if(row == 6 || (slotFilled[playerToken.getColumn()][row + 1])){
						
						//slot is now filled
						slotFilled[playerToken.getColumn()][row] = true;
						playerToken.setRow(row);
						this.tokens[playerToken.getColumn()][row] = playerToken;
						tokenEntered = true;
						isChanged = true;
						
					}
					else{
						row = 6;
					}
					
				}
				else{
					row--;
				}
			}
			if(row < 0){
				isChanged = false;
				System.out.println("Column is full");
			}
			
			
		}
		catch(ArrayIndexOutOfBoundsException e){
			isChanged = false;
			System.out.println("Not a valid co-ordinate please enter another one");
		}
		
	}
	
	void drawGrid(){
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(this.XAxis/2 ,this.YAxis/2, XAxis/2, YAxis/2);
		
	}
	
	
	
	void drawTokenSlots(){
				
		for(int i = 0; i < 7 ; i++){
			
			for(int j = 0; j < 7; j++){
				
				StdDraw.setPenColor(tokens[i][j].showColor());
				StdDraw.filledCircle(((sideLength/2) * ((2 * i) + 1)), 
						this.YAxis - ((sideLength/2) * (2 * j + 1)),this.tokenRadius);
			}
		}
		
	}
	void initialiseTokens(){
		this.tokens = new Token[7][7];
		for (int i= 0; i <  7; i++){
			for(int j = 0; j < 7; j++){
				
				slotFilled[i][j] = false; 
				tokens[i][j] = new Token(StdDraw.WHITE, i, j);
			}
		}
	}
	
	public boolean isSameToken(int column, int row, int column2, int row2){
		//checks if two tokens are equal
		if(slotFilled[column][row] && slotFilled[column2][row2]){
			Color comp = tokens[column][row].showColor();
			Color b = tokens[column2][row2].showColor();
			return comp.equals(b);
		}
		else{
			return false;
		}
		
	}
	
	public int showXAxis(){
		return (int) XAxis;
	}
	
	public int showYAxis(){
		
		return (int) YAxis;
	}
	
	public boolean isGridFull(){
		int count = 0;
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				if(slotFilled[i][j]){
					count++;
				}
			}
		}
		return (count == 49);
	}
	

	
	public static void main (String []args){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		if(width > height){
			width = height;
		}
		else{
			height = width;
		}
		StdDraw.setCanvasSize((int)width, (int)height);
		StdDraw.setXscale(0,(int)width);
		StdDraw.setYscale(0,(int)height);
		C4Grid grid = new C4Grid((int)width, (int)(width));
		
		
		StdDraw.setPenColor(StdDraw.WHITE);
		Token newToken = new Token(StdDraw.YELLOW, 6,0);
		grid.changeToken(newToken);
		
		grid.drawGrid();
		grid.drawTokenSlots();
		grid.changeToken(newToken);
		grid.drawTokenSlots();

	}
	

	
	
	

}
