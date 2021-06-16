import java.util.Scanner;
public class connect4 {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int menu = 0;
		int x = 1;
		while(x == 1) {
			System.out.println("Welcome to connect 4\n Press 1 to play\n Press 2 to quit");
			menu = input.nextInt();
			if(menu == 1) {
				//calls the function to make the array 
				String[][] board = createArray();
				//calls to print said array
				printArray(board);
				//Makes it so there is an easy turn system
				int turn = 0;
				while(x == 1) {
					//0 = players turn
					if(turn == 0) {
						//calls functions to do turn
						playersMove(board);
						System.out.println();
						printArray(board);
						turn = 1;
					}
					//1 = computers turn
					if(turn == 1) {
						//calls functions to do turn
						comsMove(board);
						System.out.println();
						printArray(board);
						turn = 0;
					}
					//this calls the check win function 
					if (checkWinner(board) != null){
						// detects what is in a row of four 
						if (checkWinner(board) == "R") {
				             System.out.println("Player won.\n");
						}
				        else if (checkWinner(board)== "Y") {
				            System.out.println("Computer won.\n");
				        }
						//resets to the menu 
						break;
					}
				}
				x = 1;
			}
			else if(menu == 2) {
				System.out.println("Quiting");
				x = 2;
			}
			else {
				System.out.println("Error invalid number pick again\n");
			}
		}
		input.close();
	}
	
	public static String[][] createArray() {
		// This method creates the array
		String[][] board = new String[6][15];
		//6 is rows 15 is columns 
		//I chose 15 since then I could scale it up since I started with tic tac toe
		for (int row =0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++){
				//if its an even column then makes a line
				if (column% 2 == 0) { 
		        	board[row][column] ="|";
		        }
		        //if its not make a space 
		        else {
		        	board[row][column] = " ";
		        }
		    }
		}
		//return the board layout
		return board;
	}
	
	public static void printArray(String[][] board) {
		//This method prints the board 
		for (int row = 0; row < board.length; row++){
			//prints column till out of columns
			for (int column = 0; column < board[row].length; column++)
			{
			//prints the board 
	        System.out.print(board[row][column]);
			}
			System.out.println();
	    }
	}
	
	public static void playersMove(String[][]board) {
		//This method will place the O for the player
		System.out.println("Chose a column to drop a peice into (0–6): ");
		Scanner input = new Scanner (System.in);
		int column = 2*input.nextInt()+1;
		for (int row =5;row>=0;row--){
	      if (board[row][column] == " "){
	        board[row][column] = "O";
	        break;
	      }
	    }
	}
	
	public static void comsMove(String[][]board) {
		//This will randomly pick a number and then place the X for the computer
		int random = (int) (Math.random()*(6));
		int column = 2*random+1;
		for (int row =5;row>=0;row--){
	      if (board[row][column] == " "){
	    	  board[row][column] = "X";
	    	  break;
	      }  
	    }
	}
	
	public static String checkWinner(String[][] board){
		//This will find any possible sets of 4 on the board
		for (int row = 0; row < 6; row++){
			//This checks if there is a connect 4 in its range
			for (int column = 0; column < 7; column+=2){
		        if ((board[row][column+1] != " ")
		        && (board[row][column+3] != " ")
		        && (board[row][column+5] != " ")
		        && (board[row][column+7] != " ")
		        && ((board[row][column+1] == board[row][column+3])
		        && (board[row][column+3] == board[row][column+5])
		        && (board[row][column+5] == board[row][column+7]))){
		        	return board[row][column+1];
		        }
			}
		}
		for (int row = 1; row < 15; row+=2){
			//This checks if there is a connect 4 in its range 
			for (int column = 0; column < 3; column++){
		        if((board[column][row] != " ")
		        && (board[column+1][row] != " ")
		        && (board[column+2][row] != " ")
		        && (board[column+3][row] != " ")
		        && ((board[column][row] == board[column+1][row])
		        && (board[column+1][row] == board[column+2][row])
		        && (board[column+2][row] == board[column+3][row]))){
		        	//This will return the value of the connect 4 
		        	return board[column][row];  
		        }  
		    }
		}
		for (int row = 0; row < 3; row++){
			//This checks if there is a connect 4 in its range
		    for (int column = 1; column < 9; column+=2){
		        if((board[row][column] != " ")
		        && (board[row+1][column+2] != " ")
		        && (board[row+2][column+4] != " ")
		        && (board[row+3][column+6] != " ")
		        && ((board[row][column] == board[row+1][column+2])
		        && (board[row+1][column+2] == board[row+2][column+4])
		        && (board[row+2][column+4] == board[row+3][column+6]))){
		        	//This will return the value of the connect 4
		        	return board[row][column];  
		        }  
		    }
		}
		for (int row = 0; row < 3; row++){
			//This checks if there is a connect 4 in its range
		    for (int column = 7; column < 15; column+=2){
		       if((board[row][column] != " ")
		       && (board[row+1][column-2] != " ")
		       && (board[row+2][column-4] != " ")
		       && (board[row+3][column-6] != " ")
		       && ((board[row][column] == board[row+1][column-2])
		       && (board[row+1][column-2] == board[row+2][column-4])
		       && (board[row+2][column-4] == board[row+3][column-6]))){
		    	   //This will return the value of the connect 4
		    	   return board[row][column];  
		       }
		   }	
		}
		//This will return nothing if there is no value that has 4 in a row or diagnal
		return null;
	}
}