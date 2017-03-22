# AP-BeierNIE-HuayuSUN
package Ozlympic;
import java . util.*;
public class Game{
	
	//Menu choose
	private void manu(){
		
				
		System.out.println("Olympic Game"
	            +"f"+"1.Select a game to run"
	            +"f"+"2. Predict the winner of the game"
				+"f"+"3. Star the game"
	            +"f"+"4. Display the final results of all game"
				+"f"+"5. Display the points of all athletes"
	            +"f"+"6. Exit"
				+"f"+"Enter an option:");
		}
	
	Scanner manu = new Scanner(System.in);
	int manuChoose = manu.nextInt();
    
	//Insert incorrect, choose again
	void chooseManuAgain(){
		while(manuChoose<1||manuChoose>6){
			System.out.print("out of range, please enter again1:");
			}
			}
	
	//Insert correct, start
	void chooseManu(){
		switch(manuChoose){
		 case 1: 
		       chooseGame();
		       break; 
		 case 2: 
		       predictWinner(); 
		       break; 
		 case 3: 
		       startGame(); 
		       break; 
		 case 4: 
		       displayFinalResult(); 
		       break; 
		 case 5: 
		       displayPoint(); 
		       break; 
		 case 6: 
		       Exit(); 
		       break;
		   
		}
	}
	
	//Option 1 Choose a game from three games		
	void chooseGame(){		    
	 System.out.println("Please choose a game to run"
	            +"f"+"1.Swimming"
	            +"f"+"2.Running"
	            +"f"+"Cycling");
		Scanner gameChoose = new Scanner(System.in);
		int choose = gameChoose.nextInt();
		
		String gameSelect;
		
	    switch(choose){
	    case 1:
	    	gameSelect = "swim";
	    	break;
	    case 2:
	    	gameSelect = "run";
	    	break;
	    case 3:
	    	gameSelect = "cycle";
	    	break;
	    	}
	    
	     }
	
	//Option 2 Choose a predict winner
	void predictWinner(){
		System.out.println("Please predict a winner from above:");
		Swimmer.getSwimmerList();
			
			}
	//Option 3 Game Start		
	void startGame(){
		}
	//Option 4 
	void displayFinalResult(){
		
	}
	//Option 5
	void displayPoint(){
		}
	//Option 6
	void Exit(){
		}
	
	}
