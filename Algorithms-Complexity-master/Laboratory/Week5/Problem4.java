/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 4 **************************

	A board M of size RxC is available (R is the number of rows and C the number of
	columns) and a chess horse is put in a starting square (posx, posy). The objective is to
	find, if possible, the way in which the horse must move around the board so that each box
	is used only once in the course (the 8x8 board always has a solution regardless of where
	the horse starts). The horse can finish in any position on the board.

	A horse has eight possible movements (assuming, of course, that it does not leave the
	board). A movement between the squares Mij and Mpq is valid only if:
	
	• (|p-i|=1) && (|q-j|=2), or if
	• (|p-i|=2) && (|q-j|=1),
	
	that is, a coordinate changes two units and the other a single unit.

	
	********************** Explanation **************************
	*
	The chess horse starts at cell (0,0), this is the first cell to be visited, numbered with a 0. On our board, 
	the chess horse will be able to do a total of 64 max moves, as it is an 8*8 board (N*N, where N = 8 is the 
	number of rows and columns).

	While moving the chess horse, we check if it has covered all the cells. If all 64 cells were covered, the 
	complete board is printed with the numbers that indicate the horse´s moves.

	The horse will have 8 possible moves at maximum. One of the possible moves is chosen and the position 
	(row and column) is updated and the next move in sequence is invoked. However, there is a possibility 
	where the knight has no more possible moves without visiting all the cells, due to already visited cells, 
	as it can visit each only once. If this occurs, false value is returned, and the previous cell is cleared 
	off because at least the last movement should have been incorrect. Like that, if the failure continues, the 
	process of clearing off cells propagates up and other possibilities are examined. Like that, we either obtain 
	the solution or print a message where no solution was found.
	
	 */



package week5;

class Problem4 { 
	
	static int N = 8; 

//	Main function, code to test the algorithm  
	public static void main(String args[]) 
	{ 
		chessHorse(); //Function to obtain the solution
	} 
	
	static boolean isValid(int i, int j, int sol[][])  // Checks if the move is valid (i,j)
	{
		return (i >= 0 && i < N && j >= 0 && j < N && sol[i][j] == -1); 
	} 

	static void printSolution(int sol[][]) 	// Function to print the N*N board solution
	{ 
		for (int i = 0; i < N; i++) 
		{ 
			for (int j = 0; j < N; j++) 
			{
				System.out.print(sol[i][j] + " "); 
			}
			System.out.println(); 
		} 
	} 

	static boolean chessHorse() // Function to find the first possible solution
	{ 
		
		int sol[][] = new int[N][N]; // The solution board N*N

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++) 
			{
				sol[i][j] = -1; 	// All cells in the solution are initialized 
			}
		}

	/* iMove[] and jMove[] define the moves of the chess horse. 
		(|p-i|=1) && (|q-j|=2),
		(|p-i|=2) && (|q-j|=1), 
		There are 8 possible moves*/
		
		int iMove[] = {2, 1, -1, -2, -2, -1, 1, 2}; 
		int jMove[] = {1, 2, 2, 1, -1, -2, -2, -1}; 

		sol[0][0] = 0; // The chess horse starts at i=0 and j==, cell is assigned with number 0, the starting point.

		
		if (!moveChessHorse(0, 0, 1, sol, iMove, jMove)) // Start from (0,0) and move the horse with the possible movements (iMove and jMove)
		{ 
			System.out.println("Solution does not exist"); // All the possibilities failed, no solution possible.
			return false; 	
		} 
		else // A solution was found, we will print it.
		{
			printSolution(sol); 
		}

		return true; 
	} 

	static boolean moveChessHorse(int i, int j, int movei, int sol[][], int iMove[], int jMove[]) // Function to move the chess horse around the board, with the use of backtracking
	{ 
		int next_i, next_j;
		
		if (movei == N*N) // 64 moves were performed so all cells were visited
		{
			return true; 	
		}
		
		for (int k = 0; k < 8; k++) // Try the possible moves from current position (i,j), 8 in total
		{ 
			next_i = i + iMove[k];	// next_i = next possible movement, in order, from iMove[]
			next_j = j + jMove[k]; 	// next_j = next possible movement, in order, from jMove[]
			
			if (isValid(next_i, next_j, sol)) 
			{ 
				sol[next_i][next_j] = movei; 
				if (moveChessHorse(next_i, next_j, movei + 1, sol, iMove, jMove)) // Possible movement added, movei increased. Later this movement may be cleared (backtracking).
				{
					return true; 
				}
				else
				{
					sol[next_i][next_j] = -1;	// backtracking 
				}
			} 
		} 

		return false; 
	} 
}  
