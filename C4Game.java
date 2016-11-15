
import java.awt.Color;
import java.awt.Font;

import java.util.*;
/*
 * Runs a game of Connect 4 with an random Ai and human player or 
 * two human players.
 *  @author Dylan Lewis
 */

public class C4Game  {
	
	private C4PlayerI [] players;
	
	private C4Grid grid;
	
	private boolean isTwoPlayers;
	
	public C4Game(boolean isTwoPlayers, int lives, int canvasXSize, int canvasYSize)  {
		players = new C4PlayerI[2];
		this.isTwoPlayers = isTwoPlayers;
		try{
			players[0] = new C4HumanPlayer(lives, 1,isTwoPlayers  );
			if(isTwoPlayers){
				
				players[1] =new C4HumanPlayer(lives, 2,isTwoPlayers  );
			}
			else{
				players[1] = new C4RandomAIPlayer(lives);
			}
			grid = new C4Grid(canvasXSize, canvasYSize);
			
		}
		catch(OutOfLivesException e){
				System.out.println("Not a valid amount of lives entered");
		}
			
		
		
		
	}

	public void game(){
		Font arial_BOLD_45pt = new Font("Arial", Font.BOLD, 45);
		Font arial_italic_30pt = new Font("Arial", Font.ITALIC, 12);
		//not closed here as inputscanner is used in C4app
		// and if closed here only one game possible
		Scanner inputScanner = new Scanner(System.in);
		boolean gameOver = false;
		boolean missAGo = true;
		Random generator = new Random();
		int randCordinate = 0;
		int column = 0;
		while(!gameOver){
			try{
				StdDraw.clear();
				//resets the grid
				grid.initialiseTokens();
				grid.drawGrid();
				grid.drawTokenSlots();
				
				StdDraw.setFont(arial_italic_30pt);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(-10, grid.showYAxis() -10  ,  "" +this.players[0].showLives());
				StdDraw.text(grid.showXAxis() + 10, grid.showYAxis() -10  ,  "" +this.players[1].showLives());
				boolean lifeNotTaken = true;
				int outcome = 0;
				while(lifeNotTaken) {
					
					
					
					outcome = checkFor4(grid);
					if(outcome == 1){
						
						this.players[1].takeAwaylife();
						lifeNotTaken = false;
						
					}
					else if(outcome == -1){
						
						
						this.players[0].takeAwaylife();
						lifeNotTaken = false;
						
					}
					else if(grid.isGridFull()){
						lifeNotTaken = false;
						System.out.println("Grid is full, resetting grid, no lives lost");
					}
					
					else{	
						
						
						randCordinate = generator.nextInt(7);
						//checks if player two has gone after first turn
						if(grid.isChanged){
							System.out.println("Player 1 enter column where you want to place token.(0 - 6)");
							column = inputScanner.nextInt();
							grid.changeToken(players[0].newToken(column, 0));
							missAGo = false;
						}
						else{
							grid.isChanged = true;
						}
						
						
						outcome = checkFor4(grid);
						grid.drawTokenSlots();
						//checks if the game hasn't ended and if player 1 has gone
						if(outcome != 1  && grid.isChanged){
							if(!this.isTwoPlayers){
								grid.changeToken(players[1].newToken(randCordinate, 0));
							}
								
							else{
								System.out.println("Player2 enter column where you want to place token.(0 - 6)");
								column = inputScanner.nextInt();
								grid.changeToken(players[1].newToken(column, 0));
								
								
							}
						}
						else {
							grid.isChanged = true;
						}

					}
					
				
					
					grid.drawTokenSlots();
					
				}
	
				
			}
			catch(NullPointerException e){
				
			}
			catch(InputMismatchException e){
				inputScanner.next();
				System.out.print("Invalid input");
				
			}
			
			catch(IndexOutOfBoundsException e){
				inputScanner.next();
				
				System.out.println("Not a valid column");
			}
			
			catch (OutOfLivesException e) {
				// TODO Auto-generated catch block
				
				StdDraw.setFont(arial_BOLD_45pt);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(grid.showXAxis()/2, grid.showXAxis()/2, "Game Over");
				gameOver = true;
				
				
			}
			
		}
		
		
	}
	/*
	 * This method checks for four tokens in a row with the same colour value.
	 * It then returns 1 if the colour of the four in a row is yellow(Player 1).
	 * If however the colour is red then it returns -1. If there are no valid 4 in a row
	 * it returns 0.
	 */
	public int checkFor4(C4Grid gameGrid){
		if(gameGrid != null){
			int count = 0;
			//checks for 4 diagonally up to the right; 
			for(int i = 0; i < 7; i++){
				
				for(int j = 0; j < 7; j++){
					count = 0;
					for(int x=0;  x + i < 6 && x + j < 6 ; x++){
						if(gameGrid.isSameToken(i+ x, (j + x ), (i + x + 1), (x + 1 + j))){
							
							count ++;
							if(count == 3){
								
								
								Token newToke = gameGrid.showTokenAt((i + x), (j + x));
								Color c = newToke.showColor();
								if(c.equals(StdDraw.RED)){
									return -1;
								}
								
								return 1;
							}
							
							
						}
						else{
							count = 0;
						}
						
						
					}
					
				}
				
			}
			// checks for 4 diagonally down to the right
			count = 0;
			for(int i = 0; i < 6; i++){
				for(int j = 6; j > 0 ; j--){
					
					count = 0;
					for(int x=0;   i + x < 6  &&  (j - x) > 0  ; x++){
						if(gameGrid.isSameToken(i + x, (j - x ), (i + x + 1), ( j - x - 1))){
							
							count ++;
							if(count == 3){
								Token newToke = gameGrid.showTokenAt((i + x), (j - x));
								Color c = newToke.showColor();
								if(c.equals(StdDraw.RED)){
									return -1;
								}
								
								return 1;
							}
						}
						else{
							count = 0;
						}
						
						
					}
					
				}
				
			}
			
			
			
			//checks for 4 vertically
			count = 0;
			for(int i = 0; i < 7; i++){
				for(int j = 6; j > 0; j-- ){
					if(gameGrid.isSameToken(i, j, i, j - 1)){
						count ++;
						if(count == 3){
							Token newToke = gameGrid.showTokenAt((i), (j));
							Color c = newToke.showColor();
							if(c.equals(StdDraw.RED)){
								return -1;
							}
							
							return 1;
							
						}
					}
					else{
						count = 0;
					}
					
					
				}
			}
			//checks for 4 in a row horizontally
			count = 0;
			for(int j = 0; j < 7; j++){
				for(int i = 0; i < 6; i++ ){
					if(gameGrid.isSameToken(i, j, i + 1, j)){
						count ++;
						if(count == 3){
							Token newToke = gameGrid.showTokenAt((i ), (j));
							Color c = newToke.showColor();
							if(c.equals(StdDraw.RED)){
								return -1;
							}
							
							return 1;
							
						}
					}
					else{
						count = 0;
					}
					
					
				}
			}
			return 0;
			

			
		}
		else{
			
			return 0;
		}
		
		
	
		
		
		
	}
	
	
	
	
	
	
	

}
