/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 2 **************************
	Calculate the average grade of a student in a course consisting of n subjects using the
	divide and conquer technique, with n being a power of 2. Modify the algorithm to allow
	values of n that are not a power of 2. Calculate efficiency and determine if it improves
	the that is obtained with respect to a simple iterative version.

	Delivery requirements:
	• Deliver a report in which the solution is explained, justifying why the
	implementation has been chosen.
	• Source code.
	• Input file ‘example_d&c.txt’
	• Executable that can be tested by inserting the example file, “example_d&c.txt”,
	and returns an output file with the average value obtained. The average value
	obtained in all recursive stages of the divide and conquer process must be shown.
	
	The executable must work in any Windows environment. The example file must
	be read from the same folder where the executable is located.
 */

package examProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodeProblem2 {
	public static void problem2(String path) throws IOException 
	{
    
		File file = new File(path); 
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		FileWriter fw = new FileWriter("output_d&c.txt");  // Creates the output_d&c. txt file
	  
		String st; 
		st = br.readLine();
		int subjects = Character.getNumericValue(st.charAt(0)); // Obtain the number of subjects from the file
		
		int[] grades = new int[subjects];						// Create an array to store the grades
		int i = 0;
		
		while ((st = br.readLine()) != null) 
		{
			grades[i] = Integer.parseInt(st);					// Fill the array with the given grades in the file
			i++;
		}
		
		fw.write("Final average value: " + Math.round(averageDC(grades, 0, subjects - 1, fw, subjects)) + "\n"); // The solution, which is given by averageDC(), is rounded 
		fw.close(); 	// The output file is closed
	}



	public static double averageDC(int[] grades, int l, int r, FileWriter fw, int subjects) throws IOException 
	{	
		//Empty Case
		if (l > r)
		{
		    return 0;
		}
		// Base Case: if there's just a single element it is the average of the array itself
		if (l==r) 
		{
			
		    return grades[l];
		}
		

		int mid = (l+r)/2;
		double avgLeft = 0;
		double avgRight = 0;

		avgLeft = averageDC(grades, l, mid, fw, subjects); 		// Divide and Conquer, left side recursive call
		avgRight = averageDC(grades, mid+1, r, fw, subjects);	// Divide and Conquer, right side recursive call
		
		fw.write("Average Left: " + avgLeft + "\n");
		fw.write("Average Right: " + avgRight + "\n");
		
		
		double average = (avgLeft + avgRight)/2;	
		
		int x = r - l;
		

		if((x != 0) && (x != 1) && ((x & (x - 1)) == 0))
		{
			if(l != 0 || r != (subjects-1)) //If we are not in the last recursive stage
			{
				average = (avgLeft + avgRight + Math.sqrt(r-l)*avgLeft)/(Math.sqrt(r-l) + 2); // Explanation in report
			}
		}
		
		//If we are in the last recursive stage and the number of subjects is odd
		if(r == subjects - 1 && l == 0 && subjects%2 != 0)
		{
			average = (avgLeft*(subjects/2) + avgRight*(subjects/2) + avgLeft)/subjects; // Explanation in report
		}
		
		fw.write("Calculated average: " + average + "\n\n");
		
		return average;
	}

}
