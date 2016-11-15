import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
/*
 * Runs game/s of connect four using C4Game
 * @Author Dylan Lewis
 */
public class C4App {

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		if (width > height) {
			width = height;
		} else {
			height = width;
		}

		Scanner inputScanner = new Scanner(System.in);
		boolean finished = false;
		while(!finished){
			
			System.out.println("If you want to play type yes if not type no");
			if(inputScanner.hasNext("yes")){
				inputScanner.next();
				double size = 0;
				boolean setup = false;
				int lives  = 0;
				boolean isTwoPlayers = false;
				while (!setup) {
					System.out.println("How big do you want your grid, small, medium, big or max");
					String sizeWanted = inputScanner.next();
					//the size of the grid and std draw canvas depend on screen size
					if (sizeWanted.equals("small")) {
						size = width / 4;

					}

					else if (sizeWanted.equals("medium")) {
						size = width / 2;

					}

					else if (sizeWanted.equals("big")) {
						size = (width * 3) / 4;

					}

					else if (sizeWanted.equals("max")) {
						size = width;

					} else {
						System.out.println("Not a valid size please enter a size");

					}
					if(size != 0){
						System.out.println("How many lives do you want");
						if(inputScanner.hasNextInt()){
							lives = inputScanner.nextInt();
							if(lives > 0){
								System.out.println("Are 1 or 2 people playing");
								if(inputScanner.hasNextInt()){
									int players = inputScanner.nextInt();
									if(players == 1){
										setup = true;
									}
									else if (players == 2) {
										isTwoPlayers = true;
										setup = true;
									}
									else{
										System.out.println("Not a valid input");
									}
								}
								else{
									System.out.println("Not a valid input");
								}
							}
							else{
								System.out.println("Zero or less than zero lives not accepted");
							}
						}
						else{
							inputScanner.next();
							System.out.println("Not a valid input");
							
						}
					}

				}

				C4Game game = new C4Game(isTwoPlayers, lives, (int) size, (int) size);
				StdDraw.setCanvasSize((int) size, (int) size);
				StdDraw.setXscale(0, (int) (size));
				StdDraw.setYscale(0, (int) size);
				game.game();
				
				
			}
			else if(inputScanner.hasNext("no")){
				finished = true;
				System.out.println("Thanks for playing");
				
			}
			else{
				inputScanner.next();
				System.out.println("That was not a valid input");
				
				
			}
			
			
		}
		
		
		inputScanner.close();
	}

}
