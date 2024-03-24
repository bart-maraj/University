/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 4 **************************
	
	Bruce Springsteen is going to finish his world tour this year giving the last concert in
	Abecelandia, a city famous for its beautiful squares and that you may know, so a truck
	loaded with instruments, sound devices, flares, stands and nuts has entered in the city and
	heads to the stadium to set up the stage and fireworks. Suddenly, one of the front wheels
	explodes and partially renders the truck's steering system useless, preventing it from
	making left turns. Due to its size and weight, the truck cannot go in reverse (so it cannot
	go backwards) or change direction on the same street (that is, it cannot make a 180º turn
	without changing streets), so now the truck can only drive in a straight line and make 90º
	turns to the right. Fortunately, the driver has a map of Abecelandia like the following,
	where point 1 is the current position of the truck, point 2 is the stadium to go to, and each
	position is marked as accessible (if empty) or non-accessible (if it is in black). It can be
	assumed that initially the truck is oriented to the left, and that the map is FxC size.
	Therefore, at each position on the map, the truck can continue moving (assuming that the
	next space is free) or turn 90º to the right (once again, assuming that position is
	accessible). Since it is not known if the rest of the wheels will hold until reaching the
	stadium, we want to minimize the number of turns so as not to overload the tires that are
	still working. Implement an algorithm with Backtracking methodology, efficient as far
	as possible, taking as data the sizes in rows F and columns C of the map, the MFxC map
	and the coordinates of points 1 and 2, which finds the path that leads from 1 to 2 with the
	fewest turns to the right.
	
 */

package examProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodeProblem4 {

	public int[][] solution;
	public static int N;
	public static int M;
	public static int DestI;
	public static int DestJ;
	public static int contTurnRight;

	//initialize the solution matrix in constructor.
	public CodeProblem4() 
	{
		solution = new int[N][M]; // We create the solution map N*M
		for (int i = 0; i < N; i++) 
		{
			for (int j = 0; j < M; j++) 
			{
				solution[i][j] = 0;	// Initialized to 0
			}
		}
	}

	public void solveMap(int[][] map, int iStart, int jStart,FileWriter fw) throws IOException 
	{
		if (moveDriver(map, iStart, jStart, "down", fw)) 	//If we can reach the destination by moving the driver
		{
			fw.write("\nThe driver turned right " + contTurnRight + " times.");
			fw.write("\nThe path taken is the following one (Start = 1, Destination = 2, Path = 3):\n");
			solution[iStart][jStart] = 1;
			print(solution, fw); 	// Prints the solution in the output file
		}
		else
		{
			fw.write("No solution was found"); // No solution was found
		}
		
	}

	public boolean moveDriver(int[][] map, int i, int j, String latestDir,FileWriter fw) throws IOException // Moves the driver to find the solution path
	{
		
		if(i==DestI && j==DestJ){	// We have reached the destination
			solution[i][j] = 2;
			return true;
		}
		if (isValid(map, i, j)) {	// The move is valid
			
			solution[i][j] = 3;		// The cell is marked as part of the solution path with a 1
			
			//The driver has 4 possible options: go right,left,up or down (depending on earlier move). Explained in report
			
			if(latestDir!="right" && latestDir!="up" && moveDriver(map, i, j-1, "left", fw)){ 	// Move left
				if(latestDir == "down")
				{
					contTurnRight++; // Driver turned 90º to the right
				}
				fw.write(" Left");
				return true;
			}
			if(latestDir!="down" && latestDir!="right" &&moveDriver(map, i-1, j, "up", fw)){ 	// Move up
				if(latestDir == "left") 
				{
					contTurnRight++; // Driver turned 90º to the right
				}
				fw.write(" Up");
				return true;
			}
			if(latestDir!="left" && latestDir!="down" &&moveDriver(map, i, j+1, "right", fw)){ 	// Move right
				if(latestDir == "up") 
				{
					contTurnRight++; // Driver turned 90º to the right
				}
				fw.write(" Right");
				return true;
			}
			if(latestDir!="up" && latestDir!="left" && moveDriver(map, i+1, j, "down", fw)){ 	// Move down
				if(latestDir == "right") 
				{
					contTurnRight++; // Driver turned 90º to the right
				}
				fw.write(" Down");
				return true;
			}
		
			// If the driver can´t move we do backtraking, marking the cell with a 0, it is not part of the path.
			solution[i][j] = 0;
			return false;
		}
		return false;
	}

	public boolean isValid(int[][] map, int i, int j) // Checks if the driver move is valid (i and j are in limits and cell is not blocked)
	{ 
		if (i >= 0 && j >= 0 && i < N  && j < M && map[i][j] != 0) 
		{
			return true;
		}
		return false;
	}
	
	public void print(int [][] solution,FileWriter fw) throws IOException
	{
		for (int i = 0; i < N; i++) 
		{
			for (int j = 0; j < M; j++) 
			{
				fw.write(" " + solution[i][j]);
			}
			fw.write("\n");
		}
	}
	
	public static void problem4(String path) throws IOException 
	{	
		File file = new File(path); 
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		FileWriter fw = new FileWriter("output_backtracking.txt"); 
	  
		contTurnRight = -1;
		
		// Get number of Rows, given in the first line
		String st; 
		st = br.readLine();
		N = Character.getNumericValue(st.charAt(0));

		
		// Get number of Columns, given in the second line
		st = br.readLine();
		M = Character.getNumericValue(st.charAt(0));

		// Create an empty map Row x Column
		int map[][] = new int[N][M]; 
		
		// Keep the N*M values of the Start
		int initRow = 0, initCol = 0;
		
		int r = 0;
		int c = 0;
		
		//Read the rest of the lines and initialize the map with the given values
		while ((st = br.readLine()) != null) 
		{
			for (int i = 0; i < st.length(); i++) 
			{
				if(st.charAt(i) == 'X') // Occupied space
				{
					map[r][c] = 0;
				}
				else if(st.charAt(i) == '1') // Origin
				{
					initRow = r;
					initCol = c;
					map[r][c] = 1;
				}
				else if(st.charAt(i) == '2') // Destination
				{
					DestI = r;
					DestJ = c;
					map[r][c] = 1;
				}
				else if(st.charAt(i) == '0') // Empty Space
				{
					map[r][c] = 1;
				}
				else 
				{ 
					c--;
				}
				c++;
			}
			c = 0;
			r++;
		}
		
		CodeProblem4 pr4 = new CodeProblem4();
		
		fw.write("Moves(from latest to earliest): ");
		
		pr4.solveMap(map, initRow, initCol, fw);
		
		fw.close(); 
	}

}