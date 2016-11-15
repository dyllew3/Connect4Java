/*
 * This class was created to make it easy to see which token is in which slot
 * Stores the colour it should be. As well the x and y position on the grid
 * 
 * @author Dylan Lewis
 */


import java.awt.Color;



public class Token {
	
	private int tokenColumn ;
	private int tokenRow ;
	private Color tokenColor;
	
	public Token(Color tokenColor, int x, int y){
		try{
			this.tokenColor = tokenColor;
			this.tokenColumn = x;
			this.tokenRow = y;
			
		}
		catch (NullPointerException e){
			System.out.println("Invalid token");
			
		}
		
	}
	//returns row
	public int getRow(){
		if(tokenRow >= 0){
			return tokenRow;
		}
		else{
			return -1;
		}
		
	}
	
	public int getColumn(){
		if(tokenColumn >= 0){
			return tokenColumn;
		}
		else{
			return -1;
		}
				
	}
	//shows colour of token
	public Color showColor(){
		if(this.tokenColor != null){
			return this.tokenColor;
		}
		else{
			return null;
		}
	}
	
	
	public void setRow(int row){
		this.tokenRow = row;
		
	}

	
	

}
